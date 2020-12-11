package com.shuidun.book.dao;

import com.shuidun.book.bean.City;
import com.shuidun.book.bean.Customers;
import com.shuidun.book.bean.Flights;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FlightsDao {

    public static boolean add(Flights flight) {
        int affectedRow = -1;
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement("insert into flights values (?,?,?,?,?,?);")) {
            ps.setString(1, flight.getId());
            ps.setLong(2, flight.getPrice());
            ps.setLong(3, flight.getNSeat());
            ps.setLong(4, flight.getSeatAvail());
            ps.setString(5, flight.getFromCity());
            ps.setString(6, flight.getToCity());
            affectedRow = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("添加失败："+throwables.getMessage());
        }
        return affectedRow == 1;
    }

    public static boolean update(String id, Long price, Long nSeat, String fromCity, String toCity) {
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps1 = conn.prepareStatement("select (nSeat - seatAvail) as s from flights where id=?;");
             PreparedStatement ps2 = conn.prepareStatement("update flights set price=?,nSeat=?,seatAvail=?,fromCity=?,toCity=? where id=?;")) {
            conn.setAutoCommit(false);
            ps1.setString(1, id);
            try (ResultSet rs = ps1.executeQuery()) {
                if (rs.next()) {
                    long seatAvail = nSeat - rs.getLong(1);
                    if (seatAvail < 0) {
                        return false;
                    }
                    ps2.setLong(1, price);
                    ps2.setLong(2, nSeat);
                    ps2.setLong(3, seatAvail);
                    ps2.setString(4, fromCity);
                    ps2.setString(5, toCity);
                    ps2.setString(6, id);
                    ps2.executeUpdate();
                    conn.commit();
                } else {
                    return false;
                }
            }
        } catch (SQLException throwables) {
            System.out.println("更新失败："+throwables.getMessage());
        }
        return true;
    }

    public static List<Flights> toCity(City city) {
        try (Connection conn = DBConnector.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from flights where toCity=?;"))
    }
}
