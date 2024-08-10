package com.example.profisraelproject;


import com.google.gson.Gson;

public class Master {
    private String city;
    private String category;
    private String service;
    private String masterName;
    private String phoneNumber;

    public Master(String city, String category, String service, String masterName, String phoneNumber) {
        this.city = city;
        this.category = category;
        this.service = service;
        this.masterName = masterName;
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public String getCategory() {
        return category;
    }

    public String getService() {
        return service;
    }

    public String getMasterName() {
        return masterName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static Master fromJson(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Master.class);
    }
}