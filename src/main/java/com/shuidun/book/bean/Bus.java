package com.shuidun.book.bean;


public class Bus {
    public Bus(String location, long price, long nSeat, long seatAvail) {
        this.location = location;
        this.price = price;
        this.nSeat = nSeat;
        this.seatAvail = seatAvail;
    }

    private String location;
    private long price;
    private long nSeat;
    private long seatAvail;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

}
