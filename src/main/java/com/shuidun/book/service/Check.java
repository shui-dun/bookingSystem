package com.shuidun.book.service;

import com.shuidun.book.bean.Bus;
import com.shuidun.book.bean.Flights;
import com.shuidun.book.bean.Hotels;

public class Check {
    public static boolean checkFlight(Flights flight) {
        if (flight.getPrice() <= 0) {
            System.out.println("航班价格必须为正数");
            return false;
        }
        if (flight.getNSeat() <= 0) {
            System.out.println("航班座位数必须为正数");
            return false;
        }
        if (flight.getFromCity().equals(flight.getToCity())) {
            System.out.println("航班源城市和目的城市不能相同");
            return false;
        }
        if (flight.getFromTime().after(flight.getToTime())) {
            System.out.println("航班到达时间要先于起飞时间");
            return false;
        }
        return true;
    }

    public static boolean checkBus(Bus bus) {
        if (bus.getPrice() <= 0) {
            System.out.println("大巴价格必须为正数");
            return false;
        }
        if (bus.getNSeat() <= 0) {
            System.out.println("大巴座位数必须为正数");
            return false;
        }
        return true;
    }

    public static boolean checkHotel(Hotels hotel) {
        if (hotel.getPrice() <= 0) {
            System.out.println("宾馆价格必须为正数");
            return false;
        }
        if (hotel.getNRoom() <= 0) {
            System.out.println("宾馆房间数必须为正数");
            return false;
        }
        return true;
    }
}
