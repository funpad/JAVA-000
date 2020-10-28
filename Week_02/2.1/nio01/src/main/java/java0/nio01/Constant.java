package java0.nio01;

public class Constant {
    private static final String BODY = "Hello Socket!";
    public static final String CONTENT = "HTTP/1.1 200 OK\n" +
            "Content-Length:" + BODY.getBytes().length + "\n" +
            "Content-Type:text/html;charset=utf-8\n\n" +
            BODY;
}
