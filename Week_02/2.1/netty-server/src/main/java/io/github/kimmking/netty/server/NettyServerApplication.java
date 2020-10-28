package io.github.kimmking.netty.server;


public class NettyServerApplication {

    public static void main(String[] args) {
        HttpServer server = new HttpServer(false,8808);
        try {
            System.out.println("Starting");
            server.run();
        }catch (Exception ex){
            System.out.println("来姨妈了：" + ex.getMessage());
        }
    }
}
