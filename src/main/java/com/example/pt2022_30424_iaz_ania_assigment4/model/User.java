package com.example.pt2022_30424_iaz_ania_assigment4.model;

import java.io.Serial;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

public class User implements Serializable {
    @Serial
    private String username;
    private String password;
    private Role myRole;

    public User(String username, String password, Role myRole) {
        this.username = username;
        setPassword(password);
        this.myRole = myRole;
    }
    public User(){

    }

    public Role getMyRole() {
        return myRole;
    }

    public void setMyRole(Role myRole) {
        this.myRole = myRole;
    }

    public void setPassword(String password) {
        try {
            MessageDigest messageDigest =  MessageDigest.getInstance("SHA-256");
            this.password = Arrays.toString(messageDigest.digest(password.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public Boolean checkPassword(String passwordToCheck){
        try {
            MessageDigest messageDigest =  MessageDigest.getInstance("SHA-256");
            passwordToCheck = Arrays.toString(messageDigest.digest(passwordToCheck.getBytes(StandardCharsets.UTF_8)));
            if(passwordToCheck.equals(password))
                return true;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.username);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)){
            return false;
        }
        return getUsername().equals(((User)obj).getUsername());
    }
}
