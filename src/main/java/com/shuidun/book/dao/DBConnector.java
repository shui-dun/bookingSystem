package com.shuidun.book.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {

    /**
     * 创建数据库连接
     * @return 数据库连接对象
     */
    public static Connection getConnection() {
        Connection connection = null;
        try (InputStream in = DBConnector.class.getResourceAsStream("../../../../config.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            String url = properties.getProperty("dbUrl");
            String user = properties.getProperty("dbUser");
            String passwd = properties.getProperty("dbPasswd");
            connection = DriverManager.getConnection(url, user, passwd);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
