package com.example.sastu_insta.utils;

public class StringManipulation {

    public static String expandusername(String username){
        return username.replace("."," ");
    }
    public static String condenseusername(String username){
        return username.replace(" ",".");
    }
}
