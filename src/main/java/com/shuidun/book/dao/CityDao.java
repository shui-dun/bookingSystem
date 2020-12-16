package com.shuidun.book.dao;

import com.shuidun.book.bean.Bus;
import com.shuidun.book.bean.City;
import org.apache.commons.dbutils.BeanProcessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDao {

    /**
     * 添加一个城市
     */
    public static boolean add(Connection conn, City city) {
        int affectedRow = -1;
        try (PreparedStatement ps = conn.prepareStatement("insert into city values (?);")) {
            ps.setString(1, city.getName());
            affectedRow = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("添加失败：" + throwables.getMessage());
        }
        return affectedRow > 0;
    }

    /**
     * 查找所有城市
     */
    public static List<City> findAll(Connection conn) {
        List<City> list = null;
        try (PreparedStatement ps = conn.prepareStatement("select * from city;")) {
            try (ResultSet rs = ps.executeQuery()) {
                list = new BeanProcessor().toBeanList(rs, City.class);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
