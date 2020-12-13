package com.shuidun.book.dao;

import com.shuidun.book.bean.Reservebus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservebusDao {
    public static boolean reverse(Connection conn, Reservebus reservebus) {
        int affectedRow = -1;
        try (PreparedStatement ps = conn.prepareStatement("insert into reservebus values (?,?);")) {
            ps.setString(1, reservebus.getCustomerId());
            ps.setString(2, reservebus.getBusId());
            affectedRow = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("预订失败：" + throwables.getMessage());
        }
        return affectedRow > 0;
    }
}
