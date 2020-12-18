package com.shuidun.book.service;

import com.shuidun.book.bean.*;
import com.shuidun.book.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 预订服务
 */
public class Reserve {
    /**
     * 预订大巴
     */
    public static void reverseBus(Reservebus reservebus) {
        try (Connection conn = DBConnector.getConnection()) {
            conn.setAutoCommit(false);
            Bus bus = BusDao.find(conn, reservebus.getBusId());
            if (bus == null) {
                System.out.println("不存在该大巴");
                return;
            }
            if (bus.getSeatAvail() == 0) {
                System.out.println("已满载，预定失败");
                return;
            }
            if (ReservebusDao.reverse(conn, reservebus)) {
                bus.setSeatAvail(bus.getSeatAvail() - 1);
                if (BusDao.update(conn, bus)) {
                    System.out.println("预订成功");
                    conn.commit();
                } else {
                    System.out.println("预订失败");
                    conn.rollback();
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 预订宾馆
     */
    public static void reverseHotel(Reservehotel reservehotel) {
        try (Connection conn = DBConnector.getConnection()) {
            conn.setAutoCommit(false);
            Hotels hotel = HotelsDao.find(conn, reservehotel.getHotelId());
            if (hotel == null) {
                System.out.println("不存在该宾馆");
                return;
            }
            if (hotel.getRoomAvail() == 0) {
                System.out.println("没有空闲房间，预定失败");
                return;
            }
            if (ReservehotelDao.reverse(conn, reservehotel)) {
                hotel.setRoomAvail(hotel.getRoomAvail() - 1);
                if (HotelsDao.update(conn, hotel)) {
                    System.out.println("预订成功");
                    conn.commit();
                } else {
                    System.out.println("预订失败");
                    conn.rollback();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 预订航班
     */
    public static void reverseFlight(Reserveflight reserveflight) {
        try (Connection conn = DBConnector.getConnection()) {
            conn.setAutoCommit(false);
            List<Flights> flights = FlightsDao.reservedBy(conn, reserveflight.getCustomerId());
            Flights cur = FlightsDao.find(conn, reserveflight.getFlightId());
            if (cur == null) {
                System.out.println("不存在该航班");
                return;
            }
            if (cur.getSeatAvail() == 0) {
                System.out.println("已满载，预定失败");
                return;
            }
            if (isLegal(cur, flights)) {
                if (ReserveflightDao.reverse(conn, reserveflight)) {
                    cur.setSeatAvail(cur.getSeatAvail() - 1);
                    if (FlightsDao.update(conn, cur)) {
                        System.out.println("预订成功");
                        conn.commit();
                    } else {
                        System.out.println("预订失败");
                        conn.rollback();
                    }
                }
            } else {
                System.out.println("请检查是否与已有订单冲突");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 检查用户当前要订的航班与之前的航班是否有冲突
     */
    private static boolean isLegal(Flights cur, List<Flights> flights) {
        for (Flights flight : flights) {
            if (cur.getFromTime().after(flight.getToTime()) || cur.getToTime().before(flight.getFromTime())) {
                continue;
            }
            return false;
        }
        return true;
    }
}
