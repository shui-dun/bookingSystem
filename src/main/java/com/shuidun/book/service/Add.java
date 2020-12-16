package com.shuidun.book.service;

import com.shuidun.book.bean.*;
import com.shuidun.book.dao.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 向数据库中添加实体
 */
public class Add {

    /**
     * 添加城市
     */
    public static void addCity(City city) {
        try (Connection conn = DBConnector.getConnection()) {
            if (CityDao.add(conn, city)) {
                System.out.println("成功添加城市：" + city.getName());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 添加航班
     */
    public static void addFlight(Flights flight) {
        if (!Check.checkFlight(flight)) {
            return;
        }
        flight.setSeatAvail(flight.getNSeat());
        try (Connection conn = DBConnector.getConnection()) {
            if (FlightsDao.add(conn, flight)) {
                System.out.println("成功添加航班：" + flight.getId());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 添加一个大巴
     */
    public static void addBus(Bus bus) {
        if (!Check.checkBus(bus)) {
            return;
        }
        bus.setSeatAvail(bus.getNSeat());
        try (Connection conn = DBConnector.getConnection()) {
            if (BusDao.add(conn, bus)) {
                System.out.println("成功添加大巴：" + bus.getLocation());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 添加一个宾馆
     */
    public static void addHotel(Hotels hotel) {
        if (!Check.checkHotel(hotel)) {
            return;
        }
        hotel.setRoomAvail(hotel.getNRoom());
        try (Connection conn = DBConnector.getConnection()) {
            if (HotelsDao.add(conn, hotel)) {
                System.out.println("成功添加宾馆：" + hotel.getLocation());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 添加一个用户
     */
    public static void addCustomer(Customers customer) {
        try (Connection conn = DBConnector.getConnection()) {
            if (CustomersDao.add(conn, customer)) {
                System.out.println("成功添加用户：" + customer.getId());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
