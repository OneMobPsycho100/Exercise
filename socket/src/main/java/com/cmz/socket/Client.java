package com.cmz.socket;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import sun.net.util.IPAddressUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author: chenmingzhe
 * @Date: 2020/3/25 15:17
 */
public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            socket = new Socket("127.0.0.1",8081);
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("已连接服务器...");
            System.out.println("请输入消息.../quit退出");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while ((input=br.readLine())!=null) {
                out.println(input);
                System.out.println(in.readLine());
                if ("quit".equalsIgnoreCase(input)) {
                    System.out.println("关闭客户端...");
                    out.close();
                    in.close();
                    br.close();
                    socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
