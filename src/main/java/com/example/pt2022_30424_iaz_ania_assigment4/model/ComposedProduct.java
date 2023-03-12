package com.example.pt2022_30424_iaz_ania_assigment4.model;

import java.io.Serial;
import java.util.ArrayList;

public class ComposedProduct extends MenuItem{
    @Serial
    private String title;
    private ArrayList<MenuItem> products = new ArrayList<>();

    public ComposedProduct(String title){
       this.title= title;
    }

    public ArrayList<MenuItem> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<MenuItem> products) {
        this.products = products;
    }

    public String getTitle() {
        return title;
    }

    public double getRating(){
        return products.stream().mapToDouble(MenuItem::computeRating).sum()/products.size();
    }

    public int getCalories(){
        return products.stream().mapToInt(MenuItem::computeCalories).sum();
    }

    public int getProtein(){
        return products.stream().mapToInt(MenuItem::computeProtein).sum();
    }
    public int getFat(){
        return products.stream().mapToInt(MenuItem::computeFat).sum();
    }
    public int getSodium(){
        return products.stream().mapToInt(MenuItem::computeSodium).sum();
    }
    public int getPrice(){
        return products.stream().mapToInt(MenuItem::computePrice).sum();
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public int computePrice() {
        return products.stream().mapToInt(MenuItem::computePrice).sum();
    }

    @Override
    public double computeRating() {
        return products.stream().mapToDouble(MenuItem::computeRating).sum()/products.size();
    }

    @Override
    public int computeProtein() {
        return products.stream().mapToInt(MenuItem::computeProtein).sum();
    }

    @Override
    public int computeFat() {
            return products.stream().mapToInt(MenuItem::computeFat).sum();
    }

    @Override
    public int computeSodium() {
        return products.stream().mapToInt(MenuItem::computeSodium).sum();
    }

    @Override
    public int computeCalories() {
        return products.stream().mapToInt(MenuItem::computeCalories).sum();
    }
}
