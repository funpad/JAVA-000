package java0.nio01;

import java.net.ServerSocket;
import java.net.Socket;
/**
 * wrk压测结果：Requests/sec: 29.84, Transfer/sec: 2.86KB <br/>
 * 单线程还是很影响系统的吞吐量的。
 */
public class SocketServerBySingleThread {
    public static final int PORT = 8801;

    public static void main(String[] args) throws Exception {

        try (ServerSocket ss = new ServerSocket(PORT)) {
            System.out.println("监听" + PORT + "端口...");
            while (true) {
                Socket socket = ss.accept();
                Service.sendMessage(socket);
            }
        }
    }
}
