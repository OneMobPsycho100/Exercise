package com.multithread;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SingleFileReader {

    private boolean flag = false;
    private static Map<String, Integer> map = new HashMap<String, Integer>();

    public Map<String, Integer> getWordFrequencyMap(String dir) {
        long l = System.currentTimeMillis();
        File fileDir = new File(dir);
        if (fileDir.exists()) {
            File[] files = fileDir.listFiles();
            if (files == null) {
                throw new NullPointerException("file is empty");
            }
            for (File file : files) {
                getWordFrequencyToMap(file);
            }
        }
        System.out.println("耗时："+ (System.currentTimeMillis() - l)+"-------------");
        return map;
    }

    private void getWordFrequencyToMap(File file) {
        StringBuilder builder = new StringBuilder();
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
                builder = new StringBuilder();
                flag = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
