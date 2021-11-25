package com.nexclipper.demo.bean;

public class Order {
    public String type;
    public double amount;
    public Order(double amount,String type){
        this.amount = amount;
        this.type = type;
    }
}
