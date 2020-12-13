package com.shuidun.book.dao;

import com.shuidun.book.bean.Bus;
import com.shuidun.book.bean.City;
import com.shuidun.book.bean.Flights;
import org.apache.commons.dbutils.BeanProcessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BusDao {
    public static boolean add(Connection conn, Bus bus) {
        int affectedRow = -1;
        try (PreparedStatement ps = conn.prepareStatement("insert into bus values (?,?,?,?);")) {
            ps.setString(1, bus.getLocation());
            ps.setLong(2, bus.getPrice());
            ps.setLong(3, bus.getNSeat());
            ps.setLong(4, bus.getSeatAvail());
            affectedRow = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("添加失败：" + throwables.getMessage());
        }
        return affectedRow > 0;
    }

    public static boolean update(Connection conn, Bus bus) {
        int affectedRow = -1;
        try (PreparedStatement ps = conn.prepareStatement("update bus set price=?,nSeat=?,seatAvail=? where location=?;")) {
            ps.setLong(1, bus.getPrice());
            ps.setLong(2, bus.getNSeat());
            ps.setLong(3, bus.getSeatAvail());
            ps.setString(4, bus.getLocation());
            affectedRow = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("更新失败：" + throwables.getMessage());
        }
        return affectedRow > 0;
    }

    public static Bus find(Connection conn, String location) {
        Bus bus = null;
        try (PreparedStatement ps = conn.prepareStatement("select * from bus where location=?;")
        ) {
            ps.setString(1, location);
            try (ResultSet rs = ps.executeQuery()){
                List<Bus> list = new BeanProcessor().toBeanList(rs, Bus.class);
                if (list == null || list.size() == 0) {
                    return null;
                } else {
                    bus = list.get(0);
                }
            }
        } catch (SQLException throwables) {
            System.out.println("查询出错：" + throwables.getMessage());
        }
        return bus;
    }

    public static List<Bus> reservedBy(Connection conn, String customer) {
        List<Bus> list = null;
        try (PreparedStatement ps = conn.prepareStatement("select b.* from reservebus r join bus b on b.location = r.busId where r.customerId=?;")) {
            ps.setString(1, customer);
            try (ResultSet rs = ps.executeQuery()) {
                list = new BeanProcessor().toBeanList(rs, Bus.class);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return list;
    }
}
