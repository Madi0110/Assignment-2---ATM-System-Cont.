package com.example.demo;

import java.util.Scanner;

public class BankProxy implements Proxy{
    public BankProxy(Bank bank, Client client) {
        this.bank = bank;
        this.client = client;
    }
    Client client;
    Bank bank;

    @Override
    public void withdraw(int cash) {
        this.bank.withdraw(cash);

        if (checkPin(client.getPin())) {


            if (client.getCash() >= cash) {

                client.setCash(client.getCash() - cash);

                System.out.println("Please collect your money");

            }

        } else{
            System.out.println("Incorrect PIN");
        }

    }

    @Override
    public void topup(int cash) {
        if (checkPin(client.getPin())) {

            client.setCash(client.getCash() + cash);

            System.out.println("Your Money has been successfully deposited");

            System.out.println("");
        }  else{
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
