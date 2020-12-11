package com.shuidun.book.bean;


public class Flights {
    public Flights(String id, long price, long nSeat, long seatAvail, String fromCity, String toCity) {
        this.id = id;
        this.price = price;
        this.nSeat = nSeat;
        this.seatAvail = seatAvail;
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    private String id;
    private long price;
    private long nSeat;
    private long seatAvail;
    private String fromCity;
    private String toCity;


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

}
