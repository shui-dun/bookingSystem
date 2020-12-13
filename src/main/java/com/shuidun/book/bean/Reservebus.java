package com.shuidun.book.bean;


public class Reservebus {
    public Reservebus() {
    }

    public Reservebus(String customerId, String busId) {
        this.customerId = customerId;
        this.busId = busId;
    }

    private String customerId;
    private String busId;


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

}
