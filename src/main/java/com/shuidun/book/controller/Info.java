package com.shuidun.book.controller;

import com.shuidun.book.bean.City;
import com.shuidun.book.dao.CityDao;

import java.util.List;

public class Info {

    public static void showCities() {
        List<City> cities = CityDao.findAll();
        for (City city : cities) {
            System.out.println(city.getName());
        }
    }

    public static void infoAbout(City travelTo) {
        System.out.printf("开往%s的航班如下：\n", travelTo.getName());

    }
}
