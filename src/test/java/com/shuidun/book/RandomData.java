package com.shuidun.book;

import com.github.javafaker.Faker;
import com.shuidun.book.dao.DBConnector;
import org.junit.Test;

import java.sql.Connection;
import java.util.*;

public class RandomData {
    private Faker faker = new Faker();

    private int maxLen = 35;

    private int cityNum = 50;

    private TreeSet<String> cities = new TreeSet<>();

    private int flightNum = 300;

    private TreeSet<String> flights = new TreeSet<>();

    private int customerNum = 2000;

    private class Customer implements Comparable<Customer> {
        String id;
        String name;

        public Customer(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int compareTo(Customer o) {
            return id.compareTo(o.id);
        }
    }

    private TreeSet<Customer> customers = new TreeSet<>();

    @Test
    public void insertRandom() {
        Connection conn = DBConnector.getConnection();
        while (cities.size() != cityNum) {
            String city = faker.address().cityName();
            if (city.length() < maxLen) {
                cities.add(city);
            }
        }
        while (flights.size() != flightNum) {
            String flight = faker.code().imei();
            if (flight.length() < maxLen) {
                flights.add(flight);
            }
        }
        while (customers.size() != customerNum) {
            String code = faker.code().imei();
            String name = faker.name().name();
            Customer customer = new Customer(code, name);
            if (code.length() < maxLen && name.length() < maxLen) {
                customers.add(customer);
            }
        }

    }
}
