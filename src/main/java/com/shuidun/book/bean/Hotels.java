package com.shuidun.book.bean;


public class Hotels {
    public Hotels(String location, long price, long nRoom, long roomAvail) {
        this.location = location;
        this.price = price;
        this.nRoom = nRoom;
        this.roomAvail = roomAvail;
    }

    private String location;
    private long price;
    private long nRoom;
    private long roomAvail;


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


    public long getNRoom() {
        return nRoom;
    }

    public void setNRoom(long nRoom) {
        this.nRoom = nRoom;
    }


    public long getRoomAvail() {
        return roomAvail;
    }

    public void setRoomAvail(long roomAvail) {
        this.roomAvail = roomAvail;
    }

}
