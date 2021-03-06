package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.List;

@Component
public class Bank implements Proxy {
    @Autowired
    private List<Client> clients;
    private Connection connection;
    private Statement statement;

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }


    @Override
    public void withdraw(int cash) {
    }

    @Override
    public void topup(int cash) {
    }

    @PostConstruct
    public void init() throws SQLException {
        Connection connection = this.create_DBCon();
        ResultSet set = null;
        String query = "SELECT * FROM acc";
        statement = connection.createStatement();
        set = statement.executeQuery(query);
        while (set.next()){
            Client client = new Client(set.getString(1), set.getString(2),set.getString(3),set.getString(4), set.getDouble(5),set.getInt(6));
            this.getClients().add(client);
        }
    }

    public Connection create_DBCon() {

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/accounts", "postgres", "madi2001");
            if (connection != null) {
                System.out.println("Connection successfull !!!");
            }
            else {
                System.out.println("Connection failed !!!");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return connection;
    }
}
