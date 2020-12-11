package com.shuidun.book.dao;

import com.shuidun.book.bean.Reserveflight;
import com.shuidun.book.bean.Reservehotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservehotelDao {
    public static boolean reverse(Reservehotel reservehotel) {
        int affectedRow = -1;
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement("insert into reservehotel values (?,?);")) {
            ps.setString(1, reservehotel.getCustomerId());
            ps.setString(2, reservehotel.getHotelId());
            affectedRow = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("预订失败：" + throwables.getMessage());
        }
        return affectedRow == 1;
    }
}
