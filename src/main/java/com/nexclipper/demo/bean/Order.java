package com.nexclipper.demo.bean;

public class Order {
    public String type;
    public double amount;
    public int counter;
    public Order(double amount,String type, int counter){
        this.amount = amount;
        this.type = type;
        this.counter = counter;
    }
}
