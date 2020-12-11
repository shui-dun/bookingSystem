package com.shuidun.book.bean;


public class Reservehotel {
    public Reservehotel(String customerId, String hotelId) {
        this.customerId = customerId;
        this.hotelId = hotelId;
    }

    private String customerId;
    private String hotelId;


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

}
