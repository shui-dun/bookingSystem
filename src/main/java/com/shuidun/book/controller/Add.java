package com.shuidun.book.controller;

import com.shuidun.book.bean.*;
import com.shuidun.book.dao.*;

public class Add {
    public static void addCity(City city) {
        if (CityDao.add(city)) {
            System.out.println("成功添加城市：" + city.getName());
        }
    }

    public static void addFlight(Flights flight) {
        if (FlightsDao.add(flight)) {
            System.out.println("成功添加航班：" + flight.getId());
        }
    }

    public static void addBus(Bus bus) {
        if (BusDao.add(bus)) {
            System.out.println("成功添加大巴：" + bus.getLocation());
        }
    }

    public static void addHotel(Hotels hotel) {
        if (HotelsDao.add(hotel)) {
            System.out.println("成功添加宾馆：" + hotel.getLocation());
        }
    }

    public static void addCustomer(Customers customer) {
        if (CustomersDao.add(customer)) {
            System.out.println("成功添加用户：" + customer.getId());
        }
    }
}
