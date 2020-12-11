package com.shuidun.book.dao;

import com.shuidun.book.bean.Bus;
import com.shuidun.book.bean.City;
import com.shuidun.book.bean.Customers;
import com.shuidun.book.bean.Hotels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomersDao {
    public static boolean add(Customers customer) {
        int affectedRow = -1;
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement("insert into customers values (?,?);")) {
            ps.setString(1, customer.getId());
            ps.setString(2, customer.getName());
            affectedRow = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("添加失败：" + throwables.getMessage());
        }
        return affectedRow == 1;
    }

    public static boolean update(Customers customer) {
        int affectedRow = -1;
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement("update customers set name=? where id=?;")) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getId());
            affectedRow = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("更新失败：" + throwables.getMessage());
        }
        return affectedRow == 1;
    }
}
