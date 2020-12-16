package com.shuidun.book.dao;

import com.shuidun.book.bean.Reserveflight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReserveflightDao {
    /**
     * 预订航班
     */
    public static boolean reverse(Connection conn, Reserveflight reserveflight) {
        int affectedRow = -1;
        try (PreparedStatement ps = conn.prepareStatement("insert into reserveflight values (?,?);")) {
            ps.setString(1, reserveflight.getCustomerId());
            ps.setString(2, reserveflight.getFlightId());
            affectedRow = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("预订失败：" + throwables.getMessage());
        }
        return affectedRow > 0;
    }
}
