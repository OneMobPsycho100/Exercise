package com.cmz.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Author: chenmingzhe
 * @Date: 2020/3/25 15:42
 */
public class MultiThreadedServer {

    private int port;

    public MultiThreadedServer(int port) {
        this.port = port;
    }

    public void startServer(){
        ServerSocket server = null;
        Executor executor = Executors.newFixedThreadPool(2);
        int i = 0;
        try {
            server = new ServerSocket(this.port);
            System.out.println("服务启动成功...");
            while (true) {
                Socket client = server.accept();
                executor.execute(new ThreadedServerHandler(client,i++));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MultiThreadedServer(8081).startServer();
    }
}
