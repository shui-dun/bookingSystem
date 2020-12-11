package com.shuidun.book.controller;

import com.shuidun.book.bean.Customers;
import com.shuidun.book.dao.BusDao;
import com.shuidun.book.dao.CustomersDao;
import com.shuidun.book.dao.FlightsDao;
import com.shuidun.book.dao.HotelsDao;

public class Update {
    public static void updateBus(String location, Long price, Long nSeat) {
        if (BusDao.update(location, price, nSeat)) {
            System.out.println("更新成功");
        } else {
            System.out.println("更新失败，可能是不存在该大巴");
        }
    }

    public static void updateCustomers(Customers customer) {
        if (CustomersDao.update(customer)) {
            System.out.println("更新成功");
        } else {
            System.out.println("更新失败，可能是不存在该用户");
        }
    }

    public static void updateFlights(String id, Long price, Long nSeat, String fromCity, String toCity) {
        if (FlightsDao.update(id, price, nSeat, fromCity, toCity)) {
            System.out.println("更新成功");
        } else {
            System.out.println("更新失败，可能是不存在该航班");
        }
    }

    public static void updateHotels(String location, Long price, Long nRoom) {
        if (HotelsDao.update(location, price, nRoom)) {
            System.out.println("更新成功");
        } else {
            System.out.println("更新失败，可能是不存在该宾馆");
        }
    }
}
