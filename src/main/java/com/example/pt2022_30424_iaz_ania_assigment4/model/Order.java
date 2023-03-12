package com.example.pt2022_30424_iaz_ania_assigment4.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {
    @Serial
    private User user;
    private Date date;
    private int price;


    public Order(User user, Date date){
        this.user= user;
        this.date = date;
    }
    public Order(User user, Date date,int price){
        this.user= user;
        this.date = date;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Order(User user) {
        this.user = user;
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


    @Override
    public int hashCode() {
        return Objects.hashCode(this.date);
    }
}
