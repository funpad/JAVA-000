# **wrk** - 一个HTTP基准测试工具

>测试程序在当前目录：gateway-server-0.0.1-SNAPSHOT.jar

## 产生大量的负载的秘密
>wrk是一种现代HTTP基准测试工具，当在单个多核CPU上运行时，能够产生大量负载。它结合了多线程设计和可扩展的事件通知系统，例如epoll和kqueue。 可选的LuaJIT脚本可以执行HTTP请求生成，响应处理和自定义报告。详细信息可在Github project中(https://github.com/wg/wrk)的SCRIPTING中找到，几个示例位于scripts/中。

## 工作原理
>wrk 用了IO多路复用(IO multiplexing)的技术，也称这种IO方式为事件驱动IO(Event Driven IO)。select/epoll的好处在于单个process就可以同时处理多个网络连接的IO。它的基本原理就是select/epoll这个系统函数会不断的轮询所负责的所有socket，当某个socket有数据到达了，就通知用户进程read数据，将数据从kernel拷贝到user进程。

### 优势在于吞吐更大而不是速度更快
如果处理的连接数不是很高的话，使用select/epoll的web server不一定比使用multi-threading + blocking IO的web server性能更好，可能延迟还更大。select/epoll的优势并不是对于单个连接能处理得更快，而是在于能处理更多的连接。在多路复用模型中，对于每一个socket，一般都设置成为non-blocking。

## wrk 基本使用
>使用2个线程，保持40个连接，测试30秒
```shell
$ wrk -t2 -c50 -d30s http://localhost:8088/api/hello
```
```console
Running 30s test @ http://localhost:8088/api/hello
  2 threads and 50 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     5.98ms   18.82ms 275.65ms   93.56%
    Req/Sec    17.90k     8.93k   32.39k    57.74%
  1061236 requests in 30.02s, 126.70MB read
Requests/sec:  35354.90
Transfer/sec:      4.22MB
```

## wrk 命令行选项

```
-c, --connections: total number of HTTP connections to keep open with
                   each thread handling N = connections/threads

-d, --duration:    duration of the test, e.g. 2s, 2m, 2h

-t, --threads:     total number of threads to use

-s, --script:      LuaJIT script, see SCRIPTING

-H, --header:      HTTP header to add to request, e.g. "User-Agent: wrk"

    --latency:     print detailed latency statistics

    --timeout:     record a timeout if a response is not received within
                   this amount of time.
```

## 其它基准测试工具
- ab (Apache Benchmark)
- sb (SuperBenchmarker) https://github.com/aliostad/SuperBenchmarker
- jmeter (Apache Jmeter) 压测时一定要使用CLI，不要使用GUI。
- siege http://www.joedog.org/ | brew install siege
