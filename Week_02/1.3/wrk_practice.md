# 用wrk测试本地的基于 Akka 的服务

## 测试

```shell
➜  ~ wrk -t3 -c50 -d30s http://localhost:8080/users
Running 30s test @ http://localhost:8080/users
  3 threads and 50 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     0.88ms    3.03ms 113.57ms   97.34%
    Req/Sec    29.18k     3.36k   40.42k    71.33%
  2618951 requests in 30.08s, 364.65MB read
Requests/sec:  87070.66
Transfer/sec:     12.12MB

➜  ~ wrk -t4 -c100 -d30s http://localhost:8080/users
Running 30s test @ http://localhost:8080/users
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.50ms    4.13ms 158.29ms   97.08%
    Req/Sec    23.58k     2.86k   31.20k    72.66%
  2815522 requests in 30.03s, 392.02MB read
Requests/sec:  93768.65
Transfer/sec:     13.06MB
```

## 小结
总体用下来发现这个wrk工具还是挺好用的，我想这得益于它使用了IO多路复用技术，仅仅使用少量的线程即可产生大量的负载。