package com.shuidun.book.bean;


public class Reserveflight {
    public Reserveflight(String customerId, String flightId) {
        this.customerId = customerId;
        this.flightId = flightId;
    }

    private String customerId;
    private String flightId;


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

}
