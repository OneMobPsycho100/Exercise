package com.multithread.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String DB_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=GMT%2B8&characterEncoding=UTF-8";

    private static final String DB_USER = "root";

    private static final String DB_PWD = "";

    /**
     * MySQL连接
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // 加载数据库驱动
            Class.forName(DB_DRIVER);
            // 创建连接
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}
