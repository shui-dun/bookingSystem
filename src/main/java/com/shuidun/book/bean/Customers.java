package com.shuidun.book.bean;


public class Customers {
    public Customers() {

    }

    public Customers(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private String id;
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "客户{" +
                "id='" + id + '\'' +
                ", 姓名='" + name + '\'' +
                '}';
    }
}
