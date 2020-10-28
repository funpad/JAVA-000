package java0.nio01;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 线程数设置为40的时候，压测到 1200req/s，
 * 推测是线程池比较小，造成了等待，所以给设置到1000个。
 * 再次压测能到 1600req/s 多，跟不使用线程池的时候差不多。
 * 疑惑：为什么线程池没有提高性能？
 */
public class SocketServerByThreadPool {
    public static final int PORT = 8803;

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(1000);
        /*ExecutorService pool = new ThreadPoolExecutor(
                600,
                1000,
                3L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                new ThreadPoolExecutor.AbortPolicy());*/

        try (ServerSocket ss = new ServerSocket(PORT)) {
            System.out.println("监听" + PORT + "端口...");
            while (true) {
                Socket socket = ss.accept();
                pool.execute(() -> Service.sendMessage(socket));
            }
        } finally {
            pool.shutdownNow();
        }
    }
}
