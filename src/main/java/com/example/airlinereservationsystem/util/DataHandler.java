package com.example.airlinereservationsystem.util;

import java.util.Random;

public class DataHandler {

    public String generateRandomString(String type) {
        String SALTCHARS = "";
        int size  = 0;
        if(type.equals("alpha")){
            SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            size = 6;
        }
        else{
            SALTCHARS = "0123456789";
            size = 20;
        }
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < size) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

}
