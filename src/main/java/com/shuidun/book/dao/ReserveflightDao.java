package com.shuidun.book.dao;

import com.shuidun.book.bean.Reserveflight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReserveflightDao {
    public static boolean reverse(Reserveflight reserveflight) {
        int affectedRow = -1;
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement("insert into reserveflight values (?,?);")) {
            ps.setString(1, reserveflight.getCustomerId());
            ps.setString(2, reserveflight.getFlightId());
            affectedRow = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("预订失败：" + throwables.getMessage());
        }
        return affectedRow == 1;
    }
}
