package com.shuidun.book.service;

import com.shuidun.book.bean.*;
import com.shuidun.book.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * 从数据中获取信息
 */
public class Info {
    /**
     * 显示所有航班
     */
    public static void showCities() {
        try (Connection conn = DBConnector.getConnection()) {
            List<City> cities = CityDao.findAll(conn);
            for (City city : cities) {
                System.out.println(city.getName());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 查看去往某城市的航班、大巴、宾馆信息
     */
    public static void toCity(City travelTo) {
        try (Connection conn = DBConnector.getConnection()) {
            System.out.printf("开往%s的航班如下：\n", travelTo.getName());
            List<Flights> flights = FlightsDao.toCity(conn, travelTo);
            if (flights == null || flights.size() == 0) {
                System.out.println("未找到相关航班");
            } else {
                for (Flights flight : flights) {
                    System.out.println(flight);
                }
            }
            System.out.printf("%s的大巴如下：\n", travelTo.getName());
            Bus bus = BusDao.find(conn, travelTo.getName());
            if (bus == null) {
                System.out.println("未找到相关大巴");
            } else {
                System.out.println(bus);
            }
            System.out.printf("%s的旅馆如下：\n", travelTo.getName());
            Hotels hotel = HotelsDao.find(conn, travelTo.getName());
            if (hotel == null) {
                System.out.println("未找到相关宾馆");
            } else {
                System.out.println(hotel);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 检查用户路线完整性
     */
    public static void route(Customers customer) {
        System.out.println(customer);
        List<Flights> flights = null;
        List<Bus> buses = null;
        List<Hotels> hotels = null;
        try (Connection conn = DBConnector.getConnection()) {
            flights = FlightsDao.reservedBy(conn, customer.getId());
            buses = BusDao.reservedBy(conn, customer.getId());
            hotels = HotelsDao.reservedBy(conn, customer.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Map<String, TravelNode> map = new HashMap<>();
        ArrayList<TravelNode> head = new ArrayList<>();
        List<TravelNode> alone = new ArrayList<>();
        if (flights != null) {
            for (Flights flight : flights) {
                TravelNode from = get(map, flight.getFromCity());
                from.to = flight;
                TravelNode to = get(map, flight.getToCity());
                to.from = flight;
            }
        }
        if (buses != null) {
            for (Bus bus : buses) {
                TravelNode node = get(map, bus.getLocation());
                node.bus = bus;
            }
        }
        if (hotels != null) {
            for (Hotels hotel : hotels) {
                TravelNode node = get(map, hotel.getLocation());
                node.hotel = hotel;
            }
        }
        for (Map.Entry<String, TravelNode> entry : map.entrySet()) {
            if (entry.getValue().from == null && entry.getValue().to == null) {
                alone.add(entry.getValue());
            } else if (entry.getValue().from == null) {
                head.add(entry.getValue());
            }
        }
        Collections.sort(head);
        for (TravelNode node : alone) {
            System.out.println(node);
            System.out.println();
        }
        for (int i = 0; i < head.size(); i++) {
            TravelNode h = head.get(i);
            TravelNode node = h;
            for (; ; node = map.get(node.to.getToCity())) {
                System.out.println(node);
                if (node.to == null){
                    break;
                }else {
                    System.out.println("    *");
                }
            }
            if (i != head.size() - 1) {
                System.out.printf("\n* 你可能需要预订从%s到%s的航班\n\n", node.city, head.get(i + 1).city);
            }
        }
    }

    private static TravelNode get(Map<String, TravelNode> map, String s) {
        TravelNode node = map.get(s);
        if (node != null) {
            return node;
        }
        node = new TravelNode();
        node.city = s;
        map.put(s, node);
        return node;
    }

    /**
     * 用户旅游路线上的一个节点
     */
    private static class TravelNode implements Comparable<TravelNode> {
        private String city;
        private Flights from;
        private Flights to;
        private Hotels hotel;
        private Bus bus;

        @Override
        public int compareTo(TravelNode o) {
            return to.getFromTime().compareTo(o.to.getFromTime());
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("*** ").append(city).append('\n');
            if (from != null)
                sb.append("    来时搭乘航班：").append(from).append('\n');
            if (to != null)
                sb.append("    离开搭乘航班：").append(to).append('\n');
            if (from == null && to == null)
                sb.append("    这是一个孤立点，是否要预订去往该地的航班？\n");
            if (bus != null)
                sb.append("    预定了大巴：").append(bus).append('\n');
            else
                sb.append("    你没有预订大巴，是否要预订大巴？\n");
            if (hotel != null)
                sb.append("    预定了宾馆：").append(hotel);
            else
                sb.append("    你没有预订宾馆，是否要预订宾馆？");
            return sb.toString();
        }
    }
}
