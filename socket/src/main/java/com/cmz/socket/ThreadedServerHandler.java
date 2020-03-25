package com.cmz.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @Author: chenmingzhe
 * @Date: 2020/3/25 15:57
 */
public class ThreadedServerHandler implements Runnable {

    private Socket client;
    private int clientNo;

    public ThreadedServerHandler(Socket client, int clientNo) {
        if (client!=null) {
            this.client = client;
            this.clientNo = clientNo;
            System.out.println("创建线程为[" + clientNo + "]号服务...");
        }
    }

    public void run() {
        handleRequest();
    }

    private void handleRequest() {
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
}
