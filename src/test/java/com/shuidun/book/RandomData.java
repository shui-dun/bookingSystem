package com.shuidun.book;

import com.github.javafaker.Faker;
import com.shuidun.book.bean.*;
import com.shuidun.book.dao.*;
import com.shuidun.book.service.Add;
import com.shuidun.book.service.Reserve;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.TreeSet;

/**
 * 生成随机数据
 */
public class RandomData {
    private Random random = new Random(0);

    private Faker faker = new Faker(new Locale("zh-CN"), random);

    private int cityNum = 10;

    private int customerNum = 15;

    private TreeSet<String> cities = new TreeSet<>();

    private ArrayList<String> citiesList;

    private TreeSet<String> customers = new TreeSet<>();

    private ArrayList<String> customersList;

    @Test
    public void insertRandom() {
        while (cities.size() != cityNum) {
            cities.add(faker.address().cityName());
        }
        citiesList = new ArrayList<>(cities);
        while (customers.size() != customerNum) {
            customers.add(faker.code().imei());
        }
        customersList = new ArrayList<>(customers);
        try (Connection conn = DBConnector.getConnection()) {
            for (String s : citiesList) {
                City city = new City(s);
                CityDao.add(conn, city);
            }
            for (String s : customersList) {
                Customers customer = new Customers();
                customer.setId(s);
                customer.setName(faker.name().name());
                CustomersDao.add(conn, customer);
            }
            for (String s : citiesList) {
                if (random.nextBoolean()) {
                    Bus bus = new Bus();
                    bus.setLocation(s);
                    bus.setPrice(faker.number().numberBetween(10, 80));
                    bus.setNSeat(faker.number().numberBetween(10, 100));
                    bus.setSeatAvail(bus.getNSeat());
                    BusDao.add(conn, bus);
                }
            }
            for (String s : citiesList) {
                if (random.nextBoolean()) {
                    Hotels ho = new Hotels();
                    ho.setLocation(s);
                    ho.setPrice(faker.number().numberBetween(10, 80));
                    ho.setNRoom(faker.number().numberBetween(10, 100));
                    ho.setRoomAvail(ho.getNRoom());
                    HotelsDao.add(conn, ho);
                }
            }
            for (String s1 : citiesList) {
                for (String s2 : citiesList) {
                    if (s1.equals(s2))
                        continue;
                    if (random.nextBoolean()) {
                        Flights flight = new Flights();
                        flight.setId(faker.code().asin());
                        flight.setPrice(faker.number().numberBetween(10, 100));
                        flight.setNSeat(faker.number().numberBetween(10, 100));
                        flight.setSeatAvail(flight.getNSeat());
                        flight.setFromCity(citiesList.get(random.nextInt(citiesList.size())));
                        do {
                            flight.setToCity(citiesList.get(random.nextInt(citiesList.size())));
                        } while (flight.getFromCity().equals(flight.getToCity()));
                        flight.setFromTime(new Timestamp(1617851986560L + random.nextInt(10000000)));
                        flight.setToTime(new Timestamp(flight.getFromTime().getTime() + random.nextInt(10000000)));
                        FlightsDao.add(conn, flight);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Test
    public void reserve() {
        City city = new City();
        city.setName("city10");
        Add.addCity(city);
        city.setName("city11");
        Add.addCity(city);
        city.setName("city12");
        Add.addCity(city);
        city.setName("city13");
        Add.addCity(city);
        city.setName("city14");
        Add.addCity(city);
        city.setName("city15");
        Add.addCity(city);
        city.setName("city16");
        Add.addCity(city);
        Customers customer = new Customers("cus10", "name10");
        Add.addCustomer(customer);
        Hotels hotel = new Hotels("city11", 10, 10, 10);
        Add.addHotel(hotel);
        Reserve.reverseHotel(new Reservehotel(customer.getId(), hotel.getLocation()));
        Hotels hotel2 = new Hotels("city14", 10, 10, 10);
        Add.addHotel(hotel2);
        Reserve.reverseHotel(new Reservehotel(customer.getId(), hotel2.getLocation()));
        Bus bus = new Bus("city13", 10, 10, 10);
        Add.addBus(bus);
        Reserve.reverseBus(new Reservebus(customer.getId(), bus.getLocation()));
        Flights flights = new Flights("flight11", 10, 10, 10, "city10", "city12",
                new Timestamp(111111111111L),new Timestamp(111122222222L));
        Add.addFlight(flights);
        Reserve.reverseFlight(new Reserveflight(customer.getId(), flights.getId()));
        Flights flights2 = new Flights("flight12", 10, 10, 10, "city15", "city14",
                new Timestamp(111322222222L),new Timestamp(111422222222L));
        Add.addFlight(flights2);
        Reserve.reverseFlight(new Reserveflight(customer.getId(), flights2.getId()));
        Flights flights3 = new Flights("flight13", 10, 10, 10, "city14", "city16",
                new Timestamp(111522222222L),new Timestamp(111622222222L));
        Add.addFlight(flights3);
        Reserve.reverseFlight(new Reserveflight(customer.getId(), flights3.getId()));
    }
}
