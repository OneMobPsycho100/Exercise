package com.multithread.achieve;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/9 21:03
 */
public class TestRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("通过Runnable接口实现多线程");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread01 = new Thread(() -> {
            System.out.println("thread01");
        });
        Thread thread02 = new Thread(() -> {
            System.out.println("thread02");
        });
        thread01.start();
        thread01.join();
        thread02.start();
        thread02.join();
    }
}
