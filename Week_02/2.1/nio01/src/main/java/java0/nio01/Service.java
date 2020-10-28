package java0.nio01;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

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
