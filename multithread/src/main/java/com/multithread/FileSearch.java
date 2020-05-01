package com.multithread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.File;
import java.util.concurrent.*;

/**
 * @Author: chenmingzhe
 * @Date: 2020/3/30 12:07
 */
public class FileSearch {
    private ConcurrentHashMap<String, Integer> map;
    private String dir;
    private CountDownLatch latch;
    private ExecutorService executorService;

    public FileSearch(String dir) {
        this.dir = dir;
        this.map = new ConcurrentHashMap<String, Integer>();
        this.latch = new CountDownLatch(3);
        this.executorService =
                new ThreadPoolExecutor(3, 10,
                        200, TimeUnit.MICROSECONDS,
                        new LinkedBlockingQueue<>(50),
                        new ThreadFactoryBuilder().setNameFormat("custom-pool-%d").build());
    }

    public ConcurrentHashMap<String, Integer> listFile() {
        long l = System.currentTimeMillis();
        File fileDir = new File(dir);
        if (fileDir.exists()) {
            File[] files = fileDir.listFiles();
            if (files == null) {
                throw new NullPointerException("file is empty");
            }
            for (File file : files) {
                this.executorService.execute(new FileReaderHandler(file, map, latch));
            }
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.executorService.shutdown();
            System.out.println("耗时：" + (System.currentTimeMillis() - l) + "-------------");
        }
        return map;
    }
}
