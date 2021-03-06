package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Client {
    private String name;
    private String surName;
    private String card;
    private String pin;
    private Double cash;
    private Integer id;

    public Client(){}
    public Client(String name, String surName, String card,String pin,Double cash, Integer id) {
        this.name = name;
        this.surName = surName;
        this.card = card;
        this.pin = pin;
        this.cash = cash;
        this.id = id;

    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }
    public String getName() {
        return name;
    }
}
