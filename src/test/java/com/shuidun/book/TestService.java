package com.shuidun.book;

import com.shuidun.book.bean.*;
import com.shuidun.book.service.Add;
import com.shuidun.book.service.Info;
import com.shuidun.book.service.Reserve;
import com.shuidun.book.service.Update;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Scanner;

public class TestService {

    @Test
    public void testAddCity() {
        Add.addCity(new City("city1"));
        Add.addCity(new City("city1"));
        Add.addCity(new City("city2"));
    }

    @Test
    public void testAddFlight() {
        Flights flight = new Flights();
        flight.setId("flight");
        flight.setPrice(-1000);
        flight.setFromCity("city1");
        flight.setToCity("city2");
        flight.setFromTime(new Timestamp(100));
        flight.setToTime(new Timestamp(111));
        flight.setNSeat(100);
        Add.addFlight(flight);

        flight.setId("flight");
        flight.setPrice(1000);
        flight.setFromCity("city1");
        flight.setToCity("city1");
        flight.setFromTime(new Timestamp(100));
        flight.setToTime(new Timestamp(111));
        flight.setNSeat(100);
        Add.addFlight(flight);

        flight.setId("flight");
        flight.setPrice(1000);
        flight.setFromCity("city1");
        flight.setToCity("city2");
        flight.setFromTime(new Timestamp(100));
        flight.setToTime(new Timestamp(88));
        flight.setNSeat(100);
        Add.addFlight(flight);

        flight.setId("flight");
        flight.setPrice(1000);
        flight.setFromCity("city1");
        flight.setToCity("city2");
        flight.setFromTime(new Timestamp(100));
        flight.setToTime(new Timestamp(110));
        flight.setNSeat(0);
        Add.addFlight(flight);

        flight.setId("flight");
        flight.setPrice(1000);
        flight.setFromCity("city1");
        flight.setToCity("city2");
        flight.setFromTime(new Timestamp(100));
        flight.setToTime(new Timestamp(110));
        flight.setNSeat(100);
        Add.addFlight(flight);

        flight.setId("flight");
        flight.setPrice(1000);
        flight.setFromCity("city1");
        flight.setToCity("city2");
        flight.setFromTime(new Timestamp(100));
        flight.setToTime(new Timestamp(110));
        flight.setNSeat(100);
        Add.addFlight(flight);
    }

    @Test
    public void testAddBus() {
        Bus bus = new Bus();
        bus.setLocation("notExist");
        bus.setPrice(1000);
        bus.setNSeat(100);
        Add.addBus(bus);

        bus.setLocation("city1");
        bus.setPrice(0);
        bus.setNSeat(100);
        Add.addBus(bus);

        bus.setLocation("city1");
        bus.setPrice(1000);
        bus.setNSeat(-2);
        Add.addBus(bus);

        bus.setLocation("city1");
        bus.setPrice(1);
        bus.setNSeat(1);
        Add.addBus(bus);

        bus.setLocation("city1");
        bus.setPrice(1);
        bus.setNSeat(1);
        Add.addBus(bus);
    }

    @Test
    public void testAddHotel() {
        Hotels hotel = new Hotels();
        hotel.setLocation("nonExist");
        hotel.setPrice(100);
        hotel.setNRoom(100);
        Add.addHotel(hotel);

        hotel.setLocation("city1");
        hotel.setPrice(-1);
        hotel.setNRoom(100);
        Add.addHotel(hotel);

        hotel.setLocation("city1");
        hotel.setPrice(100);
        hotel.setNRoom(-19);
        Add.addHotel(hotel);

        hotel.setLocation("city1");
        hotel.setPrice(100);
        hotel.setNRoom(100);
        Add.addHotel(hotel);

        hotel.setLocation("city1");
        hotel.setPrice(100);
        hotel.setNRoom(100);
        Add.addHotel(hotel);
    }

    @Test
    public void testAddCustomer() {
        Customers customer = new Customers();
        customer.setId("customer1");
        customer.setName("customer1name");
        Add.addCustomer(customer);

        customer.setId("customer1");
        customer.setName("customer2name");
        Add.addCustomer(customer);
    }

    @Test
    public void testReverseBus() {
        Reservebus reservebus = new Reservebus();
        reservebus.setCustomerId("nonExist");
        reservebus.setBusId("city1");
        Reserve.reverseBus(reservebus);

        reservebus.setCustomerId("customer1");
        reservebus.setBusId("non");
        Reserve.reverseBus(reservebus);

        reservebus.setCustomerId("customer1");
        reservebus.setBusId("city1");
        Reserve.reverseBus(reservebus);

        reservebus.setCustomerId("customer1");
        reservebus.setBusId("city1");
        Reserve.reverseBus(reservebus);

        reservebus.setCustomerId("customer1");
        reservebus.setBusId("city2");
        Reserve.reverseBus(reservebus);
    }

    @Test
    public void testReverseHotel() {
        Reservehotel reservehotel = new Reservehotel();
        reservehotel.setCustomerId("non");
        reservehotel.setHotelId("city1");
        Reserve.reverseHotel(reservehotel);

        reservehotel.setCustomerId("customer1");
        reservehotel.setHotelId("non");
        Reserve.reverseHotel(reservehotel);

        reservehotel.setCustomerId("customer1");
        reservehotel.setHotelId("city1");
        Reserve.reverseHotel(reservehotel);

        reservehotel.setCustomerId("customer1");
        reservehotel.setHotelId("city1");
        Reserve.reverseHotel(reservehotel);

        reservehotel.setCustomerId("customer2");
        reservehotel.setHotelId("city1");
        Reserve.reverseHotel(reservehotel);

        reservehotel.setCustomerId("customer2");
        reservehotel.setHotelId("city2");
        Reserve.reverseHotel(reservehotel);
    }

    @Test
    public void testReverseFlight() {
        Reserveflight reserveflight = new Reserveflight();
        reserveflight.setCustomerId("n");
        reserveflight.setFlightId("flight");
        Reserve.reverseFlight(reserveflight);
        System.out.println("***");
        reserveflight.setCustomerId("customer1");
        reserveflight.setFlightId("n");
        Reserve.reverseFlight(reserveflight);
        System.out.println("***");
        reserveflight.setCustomerId("customer1");
        reserveflight.setFlightId("flight");
        Reserve.reverseFlight(reserveflight);
        System.out.println("***");
        reserveflight.setCustomerId("customer1");
        reserveflight.setFlightId("flight1");
        Reserve.reverseFlight(reserveflight);
        System.out.println("***");
        reserveflight.setCustomerId("customer1");
        reserveflight.setFlightId("flight2");
        Reserve.reverseFlight(reserveflight);
    }

    @Test
    public void testUpdateFlight() {
        Flights flight = new Flights();
        flight.setId("n");
        flight.setPrice(-1);
        flight.setFromCity("city1");
        flight.setToCity("city2");
        flight.setFromTime(new Timestamp(100));
        flight.setToTime(new Timestamp(111));
        flight.setNSeat(100);
        Update.updateFlights(flight);

        flight.setId("flight");
        flight.setPrice(-1000);
        flight.setFromCity("city1");
        flight.setToCity("city2");
        flight.setFromTime(new Timestamp(100));
        flight.setToTime(new Timestamp(111));
        flight.setNSeat(100);
        Update.updateFlights(flight);

        flight.setId("flight");
        flight.setPrice(1000);
        flight.setFromCity("city1");
        flight.setToCity("city1");
        flight.setFromTime(new Timestamp(100));
        flight.setToTime(new Timestamp(111));
        flight.setNSeat(100);
        Update.updateFlights(flight);

        flight.setId("flight");
        flight.setPrice(1000);
        flight.setFromCity("city1");
        flight.setToCity("city2");
        flight.setFromTime(new Timestamp(100));
        flight.setToTime(new Timestamp(88));
        flight.setNSeat(100);
        Update.updateFlights(flight);

        flight.setId("flight");
        flight.setPrice(1000);
        flight.setFromCity("city1");
        flight.setToCity("city2");
        flight.setFromTime(new Timestamp(100));
        flight.setToTime(new Timestamp(110));
        flight.setNSeat(0);
        Update.updateFlights(flight);

        flight.setId("flight");
        flight.setPrice(1000);
        flight.setFromCity("city1");
        flight.setToCity("city2");
        flight.setFromTime(new Timestamp(100));
        flight.setToTime(new Timestamp(110));
        flight.setNSeat(1);
        Update.updateFlights(flight);

        flight.setId("flight");
        flight.setPrice(1000);
        flight.setFromCity("city1");
        flight.setToCity("city2");
        flight.setFromTime(new Timestamp(100));
        flight.setToTime(new Timestamp(110));
        flight.setNSeat(100);
        Update.updateFlights(flight);
    }

    @Test
    public void testUpdateBus() {
        Bus bus = new Bus();

        bus.setLocation("notExist");
        bus.setPrice(1000);
        bus.setNSeat(100);
        Update.updateBus(bus);

        bus.setLocation("city1");
        bus.setPrice(0);
        bus.setNSeat(100);
        Update.updateBus(bus);

        bus.setLocation("city1");
        bus.setPrice(1000);
        bus.setNSeat(-2);
        Update.updateBus(bus);

        bus.setLocation("city1");
        bus.setPrice(1);
        bus.setNSeat(1);
        Update.updateBus(bus);

        bus.setLocation("city1");
        bus.setPrice(1);
        bus.setNSeat(4);
        Update.updateBus(bus);
    }

    @Test
    public void testUpdateHotel() {
        Hotels hotel = new Hotels();
        hotel.setLocation("nonExist");
        hotel.setPrice(100);
        hotel.setNRoom(100);
        Update.updateHotels(hotel);

        hotel.setLocation("city1");
        hotel.setPrice(-1);
        hotel.setNRoom(100);
        Update.updateHotels(hotel);

        hotel.setLocation("city1");
        hotel.setPrice(100);
        hotel.setNRoom(-19);
        Update.updateHotels(hotel);

        hotel.setLocation("city1");
        hotel.setPrice(100);
        hotel.setNRoom(1);
        Update.updateHotels(hotel);

        hotel.setLocation("city1");
        hotel.setPrice(100);
        hotel.setNRoom(100);
        Update.updateHotels(hotel);
    }

    @Test
    public void testUpdateCustomer() {
        Customers customer = new Customers();
        customer.setId("n");
        customer.setName("name1");
        Update.updateCustomers(customer);

        customer.setId("customer1");
        customer.setName("name2");
        Update.updateCustomers(customer);
    }

    @Test
    public void testShowCities() {
        Info.showCities();
    }

    @Test
    public void testToCity() {
        City city = new City("临沂");
        Info.toCity(city);

        city.setName("n");
        Info.toCity(city);

        city.setName("city2");
        Info.toCity(city);
    }

    @Test
    public void testRoute() {
        Info.route(new Customers("cus10", "name10"));
    }
}
