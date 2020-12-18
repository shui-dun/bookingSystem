package com.shuidun.book.dao;

import com.shuidun.book.bean.Hotels;
import org.apache.commons.dbutils.BeanProcessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HotelsDao {

    /**
     * 添加一个宾馆
     */
    public static boolean add(Connection conn, Hotels hotel) {
        int affectedRow = -1;
        try (PreparedStatement ps = conn.prepareStatement("insert into hotels values (?,?,?,?);")) {
            ps.setString(1, hotel.getLocation());
            ps.setLong(2, hotel.getPrice());
            ps.setLong(3, hotel.getNRoom());
            ps.setLong(4, hotel.getRoomAvail());
            affectedRow = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("添加失败：" + throwables.getMessage());
        }
        return affectedRow > 0;
    }

    /**
     * 更新宾馆信息
     */
    public static boolean update(Connection conn, Hotels hotel) {
        int affectedRow = -1;
        try (PreparedStatement ps = conn.prepareStatement("update hotels set price=?,nRoom=?,roomAvail=? where location=?;")) {
            ps.setLong(1, hotel.getPrice());
            ps.setLong(2, hotel.getNRoom());
            ps.setLong(3, hotel.getRoomAvail());
            ps.setString(4, hotel.getLocation());
            affectedRow = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("更新失败：" + throwables.getMessage());
            return false;
        }
        return affectedRow > 0;
    }

    /**
     * 根据主键查找宾馆
     */
    public static Hotels find(Connection conn, String location) {
        Hotels hotel = null;
        try (PreparedStatement ps = conn.prepareStatement("select * from hotels where location=?;")
        ) {
            ps.setString(1, location);
            try (ResultSet rs = ps.executeQuery()) {
                List<Hotels> list = new BeanProcessor().toBeanList(rs, Hotels.class);
                if (list == null || list.size() == 0) {
                    return null;
                } else {
                    hotel = list.get(0);
                }
            }
        } catch (SQLException throwables) {
            System.out.println("查询出错：" + throwables.getMessage());
        }
        return hotel;
    }

    /**
     * 查找某个用户预定的所有宾馆
     */
    public static List<Hotels> reservedBy(Connection conn, String customer) {
        List<Hotels> list = null;
        try (PreparedStatement ps = conn.prepareStatement("select h.* from reservehotel r join hotels h on h.location = r.hotelId where r.customerId=?;")) {
            ps.setString(1, customer);
            try (ResultSet rs = ps.executeQuery()) {
                list = new BeanProcessor().toBeanList(rs, Hotels.class);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return list;
    }
}
