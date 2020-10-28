package java0.nio01;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * wrk压测结果：Requests/sec: 1600, Transfer/sec: 137.92KB <br/>
 * 多线程还是相比单线程极大的提升了系统的吞吐量，但是没有使用线程池技术，会频繁的创建的销毁线程。
 */
public class SocketServerByMultiThread {
    public static final int PORT = 8802;

    public static void main(String[] args) throws Exception {

        try (ServerSocket ss = new ServerSocket(PORT)) {
            System.out.println("监听" + PORT + "端口...");
            while (true) {
                Socket socket = ss.accept();
                new Thread(() -> {
                    Service.sendMessage(socket);
                }).start();
            }
        }
    }
}
