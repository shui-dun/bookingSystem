package com.shuidun.book.bean;

import java.sql.Timestamp;

public class Flights {
    public Flights() {
    }

    public Flights(String id, long price, long nSeat, long seatAvail, String fromCity, String toCity, Timestamp fromTime, Timestamp toTime) {
        this.id = id;
        this.price = price;
        this.nSeat = nSeat;
        this.seatAvail = seatAvail;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    private String id;
    private long price;
    private long nSeat;
    private long seatAvail;
    private String fromCity;
    private String toCity;
    private java.sql.Timestamp fromTime;
    private java.sql.Timestamp toTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }


    public long getNSeat() {
        return nSeat;
    }

    public void setNSeat(long nSeat) {
        this.nSeat = nSeat;
    }


    public long getSeatAvail() {
        return seatAvail;
    }

    public void setSeatAvail(long seatAvail) {
        this.seatAvail = seatAvail;
    }


    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }


    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }


    public java.sql.Timestamp getFromTime() {
        return fromTime;
    }

    public void setFromTime(java.sql.Timestamp fromTime) {
        this.fromTime = fromTime;
    }


    public java.sql.Timestamp getToTime() {
        return toTime;
    }

    public void setToTime(java.sql.Timestamp toTime) {
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return "航班号='" + id + '\'' +
                ", 价格=" + price +
                ", 座位数目=" + nSeat +
                ", 剩余座位数=" + seatAvail +
                ", 来自='" + fromCity + '\'' +
                ", 开往='" + toCity + '\'' +
                ", 起飞时间=" + fromTime +
                ", 到达时间=" + toTime;
    }
}