package com.cmz.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: chenmingzhe
 * @Date: 2020/3/25 14:58
 */
public class SingleThreadServer {

    private int port;

    public SingleThreadServer(int port) {
        this.port = port;
    }

    public void startServer() {
        ServerSocket server = null;
        int i = 0;

        try {
            server = new ServerSocket(this.port);
            System.out.println("服务端启动成功...");
            while (true) {
                Socket client = server.accept();
                handleRequest(client, i++);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void handleRequest(Socket client, int clientNo) {
        PrintStream os = null;
        BufferedReader in = null;
        try {
            os = new PrintStream(client.getOutputStream());
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if ("quit".equalsIgnoreCase(inputLine)) {
                    System.out.println("关闭客户端[" + clientNo + "]...");
                    os.close();
                    in.close();
                    client.close();
                } else {
                    System.out.println("收到客户端[" + clientNo + "]的消息：" + inputLine);
                    os.println("回复客户端的消息：" + inputLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SingleThreadServer(8081).startServer();
    }
}

