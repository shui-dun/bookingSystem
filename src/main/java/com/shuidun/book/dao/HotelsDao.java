package com.shuidun.book.dao;

import com.shuidun.book.bean.Flights;
import com.shuidun.book.bean.Hotels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelsDao {
    public static boolean add(Hotels hotel) {
        int affectedRow = -1;
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement("insert into hotels values (?,?,?,?);")) {
            ps.setString(1, hotel.getLocation());
            ps.setLong(2, hotel.getPrice());
            ps.setLong(3, hotel.getNRoom());
            ps.setLong(4, hotel.getRoomAvail());
            affectedRow = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("添加失败：" + throwables.getMessage());
        }
        return affectedRow == 1;
    }

    public static boolean update(String location, Long price, Long nRoom) {
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps1 = conn.prepareStatement("select (nRoom - roomAvail) as s from hotels where location=?;");
             PreparedStatement ps2 = conn.prepareStatement("update hotels set price=?,nRoom=?,roomAvail=? where location=?;")) {
            conn.setAutoCommit(false);
            ps1.setString(1, location);
            try (ResultSet rs = ps1.executeQuery()) {
                if (rs.next()) {
                    long roomAvail = nRoom - rs.getLong(1);
                    if (roomAvail < 0) {
                        return false;
                    }
                    ps2.setLong(1, price);
                    ps2.setLong(2, nRoom);
                    ps2.setLong(3, roomAvail);
                    ps2.setString(4, location);
                    ps2.executeUpdate();
                    conn.commit();
                } else {
                    return false;
                }
            }
        } catch (SQLException throwables) {
            System.out.println("更新失败：" + throwables.getMessage());
        }
        return true;
    }
}
