package com.example.pt2022_30424_iaz_ania_assigment4.bussinesLayer;

import com.example.pt2022_30424_iaz_ania_assigment4.model.MenuItem;
import com.example.pt2022_30424_iaz_ania_assigment4.model.User;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IDeliveryServiceProcessing {

    public void loadProducts(String path) throws IOException;
    public void addProduct(MenuItem menuItem);
    public void modifyProduct(MenuItem modifyMenuItem,MenuItem initialItem);
    public void removeProduct( MenuItem removeMenu);
    public void addClient(User user);
    public List<MenuItem> searchingProduct(String keyWord, double minR, double maxR,
                                           int minC, int maxC, int minP, int maxP, int minF, int maxF,
                                           int minS, int maxS, int minPr, int maxPr);
    public void createOrder(List<MenuItem> boughtItems, User user, Date date);
    public void createMenuItem(String title, ArrayList<MenuItem> menuItems);
    public void reportTimeInterval(int minT, int maxT);
    public void reportClients(int number,int amount);
    public void reportProducts(int number);
    public void reportDay(LocalDate date);
}
