package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class BankProxy implements Proxy{
    Connection connection = null;
    Statement statement = null;
    public BankProxy(Bank bank, Client client) {
        this.bank = bank;
        this.client = client;
    }
    Client client;
    Bank bank;



    @Override
    public void withdraw(int cash) {

        connection = bank.create_DBCon();

        if (checkPin(client.getPin())) {
            if (client.getCash() >= cash) {
                    try {
                        Double total = client.getCash() - cash;
                        String query = "update acc set cash = '" + total + "' where id = " + client.getId();
                        statement = connection.createStatement();
                        statement.executeUpdate(query);
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    System.out.println("Please collect your money");
                    client.setCash(client.getCash() - cash);
            }

        } else{
            System.out.println("Incorrect PIN");
        }
    }

    @Override
    public void topup(int cash) {
        connection = bank.create_DBCon();

        if (checkPin(client.getPin())) {
            try {
                Double total = client.getCash() + cash;
                String query = "update acc set cash = '" + total + "' where id = " + client.getId();
                statement = connection.createStatement();
                statement.executeUpdate(query);
            } catch (Exception e) {
                System.out.println(e);

            }
            client.setCash(client.getCash() + cash);
        } else {

            System.out.println("Incorrect PIN");
        }
    }






    public boolean checkPin(String pin){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the pin:");
        String pin2 = scan.next();

        if (pin.equals(pin2)){
            return true;
        } else{
            return false;
        }
    }
}
