package com.shuidun.book.controller;

import com.shuidun.book.bean.Reservebus;
import com.shuidun.book.bean.Reserveflight;
import com.shuidun.book.bean.Reservehotel;
import com.shuidun.book.dao.ReservebusDao;
import com.shuidun.book.dao.ReserveflightDao;
import com.shuidun.book.dao.ReservehotelDao;

public class Reserve {
    public static void reverseBus(Reservebus reservebus) {
        if (ReservebusDao.reverse(reservebus)) {
            System.out.println("预订成功");
        }
    }

    public static void reverseHotel(Reservehotel reservehotel) {
        if (ReservehotelDao.reverse(reservehotel)) {
            System.out.println("预订成功");
        }
    }

    public static void reverseFlight(Reserveflight reserveflight) {
        if (ReserveflightDao.reverse(reserveflight)) {
            System.out.println("预订成功");
        }
    }

}
