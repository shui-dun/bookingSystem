package com.shuidun.book.service;

import com.shuidun.book.bean.Bus;
import com.shuidun.book.bean.Customers;
import com.shuidun.book.bean.Flights;
import com.shuidun.book.bean.Hotels;
import com.shuidun.book.dao.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 更新数据库数据
 */
public class Update {

    /**
     * 更新大巴信息
     */
    public static void updateBus(Bus bus) {
        if (!Check.checkBus(bus)) {
            return;
        }
        try (Connection conn = DBConnector.getConnection()) {
            conn.setAutoCommit(false);
            Bus origin = BusDao.find(conn, bus.getLocation());
            if (origin == null) {
                System.out.println("没有找到该大巴，更新失败");
                return;
            }
            long seatOccupied = origin.getNSeat() - origin.getSeatAvail();
            bus.setSeatAvail(bus.getNSeat() - seatOccupied);
            if (bus.getSeatAvail() < 0) {
                System.out.println("新的座位数小于预订人数，更新失败");
                return;
            }
            if (BusDao.update(conn, bus)) {
                System.out.println("更新成功");
                conn.commit();
            } else {
                System.out.println("更新失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 更新用户信息
     */
    public static void updateCustomers(Customers customer) {
        try (Connection conn = DBConnector.getConnection()) {
            if (CustomersDao.update(conn, customer)) {
                System.out.println("更新成功");
            } else {
                System.out.println("更新失败，可能是不存在该用户");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 更新航班信息
     */
    public static void updateFlights(Flights flight) {
        if (!Check.checkFlight(flight)) {
            return;
        }
        try (Connection conn = DBConnector.getConnection()) {
            conn.setAutoCommit(false);
            Flights origin = FlightsDao.find(conn, flight.getId());
            if (origin == null) {
                System.out.println("没有找到该航班，更新失败");
                return;
            }
            long seatOccupied = origin.getNSeat() - origin.getSeatAvail();
            flight.setSeatAvail(flight.getNSeat() - seatOccupied);
            if (flight.getSeatAvail() < 0) {
                System.out.println("新的座位数小于预订人数，更新失败");
                return;
            }
            if (FlightsDao.update(conn, flight)) {
                System.out.println("更新成功");
                conn.commit();
            } else {
                System.out.println("更新失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 更新宾馆信息
     */
    public static void updateHotels(Hotels hotel) {
        if (!Check.checkHotel(hotel)) {
            return;
        }
        try (Connection conn = DBConnector.getConnection()) {
            conn.setAutoCommit(false);
            Hotels origin = HotelsDao.find(conn, hotel.getLocation());
            if (origin == null) {
                System.out.println("没有找到该宾馆，更新失败");
                return;
            }
            long roomOccupied = origin.getNRoom() - origin.getRoomAvail();
            hotel.setRoomAvail(hotel.getNRoom() - roomOccupied);
            if (hotel.getRoomAvail() < 0) {
                System.out.println("新的房间数小于预订人数，更新失败");
                return;
            }
            if (HotelsDao.update(conn, hotel)) {
                System.out.println("更新成功");
                conn.commit();
            } else {
                System.out.println("更新失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
