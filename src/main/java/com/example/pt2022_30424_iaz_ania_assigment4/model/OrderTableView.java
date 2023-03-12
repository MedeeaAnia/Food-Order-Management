package com.example.pt2022_30424_iaz_ania_assigment4.model;

import java.util.Date;

public class OrderTableView {
    private String username;
    private User user;
    private Date date;
    private int price;

    public OrderTableView(User user, Date date,int price){
        this.username = user.getUsername();
        this.user= user;
        this.date = date;
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
