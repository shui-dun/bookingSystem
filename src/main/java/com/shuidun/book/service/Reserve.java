package com.shuidun.book.service;

import com.shuidun.book.bean.Flights;
import com.shuidun.book.bean.Reservebus;
import com.shuidun.book.bean.Reserveflight;
import com.shuidun.book.bean.Reservehotel;
import com.shuidun.book.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Reserve {
    public static void reverseBus(Reservebus reservebus) {
        try (Connection conn = DBConnector.getConnection()) {
            if (ReservebusDao.reverse(conn, reservebus)) {
                System.out.println("预订成功");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void reverseHotel(Reservehotel reservehotel) {
        try (Connection conn = DBConnector.getConnection()) {
            if (ReservehotelDao.reverse(conn, reservehotel)) {
                System.out.println("预订成功");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void reverseFlight(Reserveflight reserveflight) {
        try (Connection conn = DBConnector.getConnection()) {
            conn.setAutoCommit(false);
            List<Flights> flights = FlightsDao.reservedBy(conn, reserveflight.getCustomerId());
            Flights cur = FlightsDao.find(conn, reserveflight.getFlightId());
            if (isLegal(cur, flights)) {
                ReserveflightDao.reverse(conn, reserveflight);
                System.out.println("预订成功");
                conn.commit();
            } else {
                System.out.println("请检查是否与已有订单冲突");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static boolean isLegal(Flights cur, List<Flights> flights) {
        for(Flights flight : flights) {
            if (cur.getFromTime().after(flight.getToTime()) || cur.getToTime().before(flight.getFromTime())){
                continue;
            }
            return false;
        }
        return true;
    }
}
