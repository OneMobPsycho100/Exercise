package com.multithread;

import com.multithread.dao.WordFrequencyDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    private static WordFrequencyDao dao = new WordFrequencyDao();
    private final static String dir = "F:\\gitrepository\\Exercise\\multithread\\src\\main\\resources\\2017\\data_18674";

    public static void main(String[] args) throws SQLException {
            // 32790
         Map<String, Integer> result = new SingleFileReader()
               .getWordFrequencyMap("F:\\gitrepository\\Exercise\\multithread\\src\\main\\resources\\2017\\data_18674");
//        insert(wordFrequencyMap);
        // Map<String, Integer> map = select("trading");
        // System.out.println(map);
        // System.out.println(WeekColumEnum.getWeekColum(1));
       // ConcurrentHashMap<String, Integer> map = new FileSearch(dir).listFile();
       // Map<String, Integer> result = new HashMap<>(map);
      //  System.out.println(result);
       dao.saveWordFrequency(result);
    }

    private static void insert(Map<String, Integer> map) throws SQLException {
        dao.saveWordFrequency(map);
    }

    private static Map<String, Integer> select(String keyWord) throws SQLException {
        return dao.getWordFrequencyByKeyWord(keyWord);
    }
}
