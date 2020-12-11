package com.shuidun.book.dao;

import com.shuidun.book.bean.Bus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BusDao {
    public static boolean add(Bus bus) {
        int affectedRow = -1;
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement("insert into bus values (?,?,?,?);")) {
            ps.setString(1, bus.getLocation());
            ps.setLong(2, bus.getPrice());
            ps.setLong(3, bus.getNSeat());
            ps.setLong(4, bus.getSeatAvail());
            affectedRow = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("添加失败：" + throwables.getMessage());
        }
        return affectedRow == 1;
    }

    public static boolean update(String location, Long price, Long nSeat) {
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps1 = conn.prepareStatement("select (nSeat - seatAvail) as s from bus where location=?;");
             PreparedStatement ps2 = conn.prepareStatement("update bus set price=?,nSeat=?,seatAvail=? where location=?;")) {
            conn.setAutoCommit(false);
            ps1.setString(1, location);
            try (ResultSet rs = ps1.executeQuery()) {
                if (rs.next()) {
                    long seatAvail = nSeat - rs.getLong(1);
                    if (seatAvail < 0) {
                        return false;
                    }
                    ps2.setLong(1, price);
                    ps2.setLong(2, nSeat);
                    ps2.setLong(3, seatAvail);
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
