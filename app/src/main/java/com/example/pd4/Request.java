package com.example.pd4;

import java.util.List;

public class Request {

    private String name;
    private String address;
    private String total;
    private String number;
    private List<Order> items;

    public Request(){
    }

    public Request(String name, String address, String total, String number, List<Order> items) {
        this.name = name;
        this.address = address;
        this.total = total;
        this.number = number;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getItems() {
        return items;
    }

    public void setItems(List<Order> items) {
        this.items = items;
    }
}
