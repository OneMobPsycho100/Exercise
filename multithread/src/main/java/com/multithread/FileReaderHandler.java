package com.multithread;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: chenmingzhe
 *
 *
 * @Date: 2020/3/30 11:28
 */
public class FileReaderHandler implements Runnable {
    private File file;
    private ConcurrentHashMap<String, Integer> map;
    private CountDownLatch latch;
    private boolean flag = false;

    public FileReaderHandler(File file, ConcurrentHashMap<String, Integer> map, CountDownLatch latch) {
        this.file = file;
        this.map = map;
        this.latch = latch;
    }

    @Override
    public void run() {
        getWordFrequencyToMap(file);
        latch.countDown();

    }

    private void getWordFrequencyToMap(File file) {
        StringBuffer builder = new StringBuffer();
        String str;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            while ((str = br.readLine()) != null) {
                if (flag && str.startsWith(" ") || str.startsWith("DE")) {
                    builder.append(str);
                    if (str.startsWith("DE")) {
                        flag = true;
                    }
                    continue;
                }
                String[] keyWords = builder.toString()
                        .replaceAll("-", "")
                        .toLowerCase()
                        .split("[^a-z0-9]");
                for (String keyWord : keyWords) {
                    if ("".equals(keyWord)) {
                        continue;
                    }
                    if (map.containsKey(keyWord)) {
                        map.put(keyWord, map.get(keyWord) + 1);
                    } else {
                        map.put(keyWord, 1);
                    }
                }
                builder = new StringBuffer();
                flag = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
