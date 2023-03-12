package com.example.pt2022_30424_iaz_ania_assigment4.dataLayer;

import com.example.pt2022_30424_iaz_ania_assigment4.model.Order;
import com.example.pt2022_30424_iaz_ania_assigment4.model.User;
import com.example.pt2022_30424_iaz_ania_assigment4.model.MenuItem;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SerializeData {

    /**
     * @return a list of menuItems ->read from a serialized file
     */
    public static List<MenuItem> readDataMenuItem(){
        List<MenuItem> readFromFile = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream("C:\\Users\\Medeea\\Desktop\\ANUL2(SEM2)\\Projects PT\\PT2022_30424_IAZ_ANIA_assigment4\\menuItem.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            readFromFile = (List<MenuItem>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();

        }
        return readFromFile;
    }

    /**
     * @param writeFile -> list of menu items that I want the function to write it in a file
     */
    public static void writeDataMenuItem(List<MenuItem> writeFile){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("C:\\Users\\Medeea\\Desktop\\ANUL2(SEM2)\\Projects PT\\PT2022_30424_IAZ_ANIA_assigment4\\menuItem.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(writeFile);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    /**
     * @return a list of users that have an account for the app
     */
    public static List<User> readDataUser(){
        List<User> readFromFile = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream("C:\\Users\\Medeea\\Desktop\\ANUL2(SEM2)\\Projects PT\\PT2022_30424_IAZ_ANIA_assigment4\\user.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            readFromFile = (List<User>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return readFromFile;
    }

    /**
     * @param writeFile list of users-> save it in a file
     */
    public static void writeUser(List<User> writeFile){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("C:\\Users\\Medeea\\Desktop\\ANUL2(SEM2)\\Projects PT\\PT2022_30424_IAZ_ANIA_assigment4\\user.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(writeFile);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    /**
     * @param toWrite information about orders and the items bought stored in a file
     */
    public static void writeHashMap(HashMap<Order, ArrayList<MenuItem>> toWrite){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("C:\\Users\\Medeea\\Desktop\\ANUL2(SEM2)\\Projects PT\\PT2022_30424_IAZ_ANIA_assigment4\\orders.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(toWrite);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * reads data about orders from file and returns a list
     * @return
     */
    public static HashMap<Order, ArrayList<MenuItem>> readDataOrders(){
        Map<Order, ArrayList<MenuItem>> readFromFile = new HashMap<>();
        try {
            FileInputStream fileIn = new FileInputStream("C:\\Users\\Medeea\\Desktop\\ANUL2(SEM2)\\Projects PT\\PT2022_30424_IAZ_ANIA_assigment4\\orders.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            readFromFile = (Map<Order, ArrayList<MenuItem>>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return (HashMap<Order, ArrayList<MenuItem>>) readFromFile;
    }

}
