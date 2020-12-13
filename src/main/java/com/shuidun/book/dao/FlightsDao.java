package com.shuidun.book.dao;

import com.shuidun.book.bean.City;
import com.shuidun.book.bean.Customers;
import com.shuidun.book.bean.Flights;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FlightsDao {

    public static boolean add(Connection conn, Flights flight) {
        int affectedRow = -1;
        try (PreparedStatement ps = conn.prepareStatement("insert into flights values (?,?,?,?,?,?,?,?);")) {
            ps.setString(1, flight.getId());
            ps.setLong(2, flight.getPrice());
            ps.setLong(3, flight.getNSeat());
            ps.setLong(4, flight.getSeatAvail());
            ps.setString(5, flight.getFromCity());
            ps.setString(6, flight.getToCity());
            ps.setTimestamp(7, flight.getFromTime());
            ps.setTimestamp(8, flight.getToTime());
            affectedRow = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("添加失败：" + throwables.getMessage());
        }
        return affectedRow > 0;
    }


    public static boolean update(Connection conn, Flights flight) {
        try (PreparedStatement ps = conn.prepareStatement("update flights set price=?,nSeat=?,seatAvail=?,fromCity=?,toCity=?,fromTime=?,toTime=? where id=?;")) {
            ps.setLong(1, flight.getPrice());
            ps.setLong(2, flight.getNSeat());
            ps.setLong(3, flight.getSeatAvail());
            ps.setString(4, flight.getFromCity());
            ps.setString(5, flight.getToCity());
            ps.setTimestamp(6, flight.getFromTime());
            ps.setTimestamp(7, flight.getToTime());
            ps.setString(8, flight.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("更新失败：" + throwables.getMessage());
        }
        return true;
    }

    public static List<Flights> toCity(Connection conn, City city) {
        List<Flights> list = null;
        try (PreparedStatement ps = conn.prepareStatement("select * from flights where toCity=?;")
        ) {
            ps.setString(1, city.getName());
            try (ResultSet rs = ps.executeQuery()) {
                list = new BeanProcessor().toBeanList(rs, Flights.class);
            }
        } catch (SQLException throwables) {
            System.out.println("查询出错：" + throwables.getMessage());
        }
        return list;
    }

    public static Flights find(Connection conn, String id) {
        Flights flight = null;
        try (PreparedStatement ps = conn.prepareStatement("select * from flights where id=?;")) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                List<Flights> list = new BeanProcessor().toBeanList(rs, Flights.class);
                if (list == null || list.size() == 0) {
                    return null;
                } else {
                    flight = list.get(0);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flight;
    }

    public static List<Flights> reservedBy(Connection conn, String customer) {
        List<Flights> list = null;
        try (PreparedStatement ps = conn.prepareStatement("select f.* from reserveFlight r join flights f on f.id = r.flightId where r.customerId=?;")) {
            ps.setString(1, customer);
            try (ResultSet rs = ps.executeQuery()) {
                list = new BeanProcessor().toBeanList(rs, Flights.class);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return list;
    }
}
