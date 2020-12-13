package com.shuidun.book;

import com.shuidun.book.bean.*;
import com.shuidun.book.service.Add;
import com.shuidun.book.service.Info;
import com.shuidun.book.service.Reserve;
import com.shuidun.book.service.Update;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static Scanner input = new Scanner(System.in);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static String choice;
    private static City city = new City();
    private static Flights flight = new Flights();
    private static Bus bus = new Bus();
    private static Hotels hotel = new Hotels();
    private static Customers customer = new Customers();
    private static Reservebus reservebus = new Reservebus();
    private static Reserveflight reserveflight = new Reserveflight();
    private static Reservehotel reservehotel = new Reservehotel();

    public static void showInfo() {
        System.out.println("欢迎进入订票管理系统，请选择操作：");
        System.out.println("1:添加信息");
        System.out.println("    1.1:添加城市");
        System.out.println("    1.2:添加航班");
        System.out.println("    1.3:添加大巴");
        System.out.println("    1.4:添加宾馆");
        System.out.println("    1.5:添加用户");
        System.out.println("2:更新信息");
        System.out.println("    2.1:更新大巴信息");
        System.out.println("    2.2:更新用户信息");
        System.out.println("    2.3:更新航班信息");
        System.out.println("    2.4:更新宾馆信息");
        System.out.println("3:查看信息");
        System.out.println("    3.1:查看所有城市信息");
        System.out.println("    3.2:查看去往某个城市航班、大巴、宾馆信息");
        System.out.println("    3.3:查看用户旅游路线");
        System.out.println("4:预订服务");
        System.out.println("    4.1:预订大巴");
        System.out.println("    4.2:预订酒店");
        System.out.println("    4.3:预订航班");
        System.out.println("请选择要执行的操作：");
    }

    public static void inputCity() {
        System.out.print("城市名：");
        city.setName(input.nextLine());
    }

    public static boolean inputFlight() {
        System.out.print("ID：");
        flight.setId(input.nextLine());
        System.out.print("价格：");
        flight.setPrice(input.nextLong());
        System.out.print("座位数：");
        flight.setNSeat(input.nextLong());
        flight.setSeatAvail(flight.getNSeat());
        System.out.print("来自城市：");
        flight.setFromCity(input.nextLine());
        System.out.print("开往城市：");
        flight.setToCity(input.nextLine());
        System.out.print("起飞时间（形如yyyy-MM-dd HH:mm）：");
        try {
            flight.setFromTime(new Timestamp(sdf.parse(input.nextLine()).getTime()));
        } catch (ParseException e) {
            System.out.println("格式错误");
            return false;
        }
        System.out.print("到达时间（形如yyyy-MM-dd HH:mm）：");
        try {
            flight.setFromTime(new Timestamp(sdf.parse(input.nextLine()).getTime()));
        } catch (ParseException e) {
            System.out.println("格式错误");
            return false;
        }
        return true;
    }

    public static void inputBus() {
        System.out.print("位于：");
        bus.setLocation(input.nextLine());
        System.out.print("价格：");
        bus.setPrice(input.nextLong());
        System.out.print("座位数：");
        bus.setNSeat(input.nextLong());
        bus.setSeatAvail(bus.getNSeat());
    }

    public static void inputHotel() {
        System.out.print("位于：");
        hotel.setLocation(input.nextLine());
        System.out.print("价格：");
        hotel.setPrice(input.nextLong());
        System.out.print("房间数：");
        hotel.setNRoom(input.nextLong());
        hotel.setRoomAvail(hotel.getNRoom());
    }

    public static void inputCustomer() {
        System.out.print("ID：");
        customer.setId(input.nextLine());
        System.out.print("姓名：");
        customer.setName(input.nextLine());
    }

    public static void inputReservebus() {
        System.out.print("大巴位于：");
        bus.setLocation(input.nextLine());
        System.out.print("用户ID：");
        customer.setId(input.nextLine());
    }

    public static void inputReserveHotel() {
        System.out.print("用户ID：");
        customer.setId(input.nextLine());
        System.out.print("宾馆位于：");
        hotel.setLocation(input.nextLine());
    }

    public static void inputReserveFlight() {
        System.out.print("用户ID：");
        customer.setId(input.nextLine());
        System.out.print("航班ID：");
        flight.setId(input.nextLine());
    }

    public static void main(String[] args) {

        while (true) {
            showInfo();
            choice = input.nextLine();
            switch (choice) {
                case "1.1":
                    inputCity();
                    Add.addCity(city);
                    break;
                case "1.2":
                    while (true) {
                        if (inputFlight()) {
                            break;
                        }
                    }
                    Add.addFlight(flight);
                    break;
                case "1.3":
                    inputBus();
                    Add.addBus(bus);
                    break;
                case "1.4":
                    inputHotel();
                    Add.addHotel(hotel);
                    break;
                case "1.5":
                    inputCustomer();
                    Add.addCustomer(customer);
                    break;
                case "2.1":
                    inputBus();
                    Update.updateBus(bus);
                    break;
                case "2.2":
                    inputCustomer();
                    Update.updateCustomers(customer);
                    break;
                case "2.3":
                    while (true) {
                        if (inputFlight()) {
                            break;
                        }
                    }
                    Update.updateFlights(flight);
                    break;
                case "2.4":
                    inputHotel();
                    Update.updateHotels(hotel);
                    break;
                case "3.1":
                    Info.showCities();
                    break;
                case "3.2":
                    inputCity();
                    Info.toCity(city);
                    break;
                case "3.3":
                    inputCustomer();
                    Info.route(customer);
                    break;
                case "4.1":
                    inputReservebus();
                    Reserve.reverseBus(reservebus);
                    break;
                case "4.2":
                    inputReserveHotel();
                    Reserve.reverseHotel(reservehotel);
                    break;
                case "4.3":
                    inputReserveFlight();
                    Reserve.reverseFlight(reserveflight);
                    break;
            }
        }
    }
}
