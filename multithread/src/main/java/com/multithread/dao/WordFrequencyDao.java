package com.multithread.dao;

import com.multithread.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class WordFrequencyDao {

    /**
     * 将解析的词频插入数据库
     * @param map
     * @throws SQLException
     */
    public void saveWordFrequency(Map<String, Integer> map) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO word_frequency(keyword,count) VALUES(?,?)";
        PreparedStatement psmt = connection.prepareStatement(sql);
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            psmt.setString(1, entry.getKey());
            psmt.setInt(2, entry.getValue());
            psmt.execute();
        }

    }

    /**
     * 根据关键字获取词频率
     * @param keyWord
     * @return
     * @throws SQLException
     */
    public Map<String, Integer> getWordFrequencyByKeyWord(String keyWord) throws SQLException {
        Connection connection = DbUtil.getConnection();
        Map<String, Integer> result = new HashMap<>();
        String sql = "SELECT keyword,count from word_frequency where keyword=?";
        PreparedStatement psmt = connection.prepareStatement(sql);
        psmt.setString(1, keyWord);
        psmt.execute();
        ResultSet rs = psmt.getResultSet();
        while (rs.next()) {
            rs.getString("keyword");
            result.put(rs.getString("keyword"), rs.getInt("count"));
        }
        return result;
    }
}
