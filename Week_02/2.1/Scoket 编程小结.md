# Socket 编程

实现Socket服务一种办法是通过Java中的java.net包中的类来实现；
一种是通过Netty来实现。

>重构的代码目录：./nio01/src/main/java/java0/nio01/

## 原生实现

### 公共方法
在此方法中，我们会模拟一个Transaction花费的时间为30ms。

```java
public class Service {
    public static void sendMessage(Socket socket) {
        try {
            // 假设业务需要30ms处理完成
            Thread.sleep(30);

            try (BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()))) {
                bw.write(Constant.CONTENT);
                bw.flush();
            } finally {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
### 单线程实现
```java
try (ServerSocket ss = new ServerSocket(PORT)) {
    System.out.println("监听" + PORT + "端口...");
    while (true) {
        Socket socket = ss.accept();
        Service.sendMessage(socket);
    }
}
```
wrk压测结果：
```shell
$ wrk -c60 -d30s http://localhost:8801
Running 30s test @ http://localhost:8801
  2 threads and 60 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.66s   212.96ms   1.72s    95.44%
    Req/Sec    14.67      5.57    30.00     57.07%
  899 requests in 30.04s, 86.04KB read
  Socket errors: connect 0, read 918, write 8, timeout 0
Requests/sec:     29.92
Transfer/sec:      2.86KB
```
**QPS** 30
由此可见单线程还是很影响系统的吞吐量的。

### 多线程实现
```java
try (ServerSocket ss = new ServerSocket(PORT)) {
    System.out.println("监听" + PORT + "端口...");
    while (true) {
        Socket socket = ss.accept();
        new Thread(() -> {
            Service.sendMessage(socket);
        }).start();
    }
}
```
wrk压测结果：
```shell
$ wrk -c80 -d30s http://localhost:8802
Running 30s test @ http://localhost:8802
  2 threads and 80 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    33.22ms    3.94ms 128.47ms   98.65%
    Req/Sec   810.41    103.81     1.29k    89.46%
  48504 requests in 30.07s, 3.93MB read
  Socket errors: connect 0, read 47417, write 1101, timeout 0
Requests/sec:   1612.86
Transfer/sec:    133.88KB
```
**QPS** 1612
多线程还是相比单线程极大的提升了系统的吞吐量，但是没有使用线程池技术，会频繁的创建的销毁线程。

### 池化的多线程实现
```java
//ExecutorService executor = Executors.newFixedThreadPool(1000);
ExecutorService pool = new ThreadPoolExecutor(
        600,
        1000,
        3L, TimeUnit.SECONDS,
        new LinkedBlockingQueue<Runnable>(1024),
        new ThreadPoolExecutor.AbortPolicy());

try (ServerSocket ss = new ServerSocket(PORT)) {
    System.out.println("监听" + PORT + "端口...");
    while (true) {
        Socket socket = ss.accept();
        pool.execute(() -> Service.sendMessage(socket));
    }
} finally {
    pool.shutdownNow();
}
```
wrk压测结果：
```shell
$ wrk -c80 -d30s http://localhost:8803
Running 30s test @ http://localhost:8803
  2 threads and 120 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    32.82ms    3.35ms  96.95ms   98.57%
    Req/Sec   818.66    211.48     1.86k    91.64%
  48959 requests in 30.07s, 3.97MB read
  Socket errors: connect 0, read 47791, write 1225, timeout 0
Requests/sec:   1628.35
Transfer/sec:    135.17KB
```
**QPS** 1628
线程数设置为40的时候，压测到 1200req/s， 推测是线程池比较小，造成了等待，所以给设置到1000个。 
再次压测能到 1620req/s，跟不使用线程池的时候差不多。 

思考：为什么线程池没有提高性能？


## 通过Netty实现
代码实现略。

wrk压测结果：
```shell
$ wrk -c40 -d30s http://localhost:8808/test
Running 30s test @ http://localhost:8808/test
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   468.09us    1.88ms  87.37ms   99.59%
    Req/Sec    50.63k     4.38k   96.36k    85.86%
  3027501 requests in 30.10s, 314.71MB read
Requests/sec: 100579.45
Transfer/sec:     10.46MB
```
**QPS** 100579
性能是单线程的3352倍，是多线程（包括池化的多线程）的62倍。
Netty的优秀性能完爆上述原生实现。

## 总结
- 第一种实现就是阻塞方式，它永远只能等待上一个客户端连接的操作完成后，才开始继续处理下一个客户端的连接。
- 第二种和第三种通过多线程的方式抢占CPU来完成客户端连接的操作，由于多线程存在竞争关系，所以线程切换上下文的开销也不算小。
- 第4种Netty的方式，它的底层使用了IO多路复用、零拷贝，只需要很少的线程即可同时处理上万的客户端连接，从而降低了资源的消耗，提升了效率。