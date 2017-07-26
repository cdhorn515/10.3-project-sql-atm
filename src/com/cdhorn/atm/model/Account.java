package com.cdhorn.atm.model;


import com.cdhorn.atm.helpers.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private String name;
    private double balance;
    private Statement statement;

    public Account(String name, double balance, Statement statement) {
        this.name = name;
        this.balance = balance;
        this.statement = statement;
    }

    public void save() throws SQLException {
         String formattedSql = String.format("INSERT INTO bankaccounts (name, balance) VALUES ('%s', %s)", name, balance);
        statement.executeUpdate(formattedSql);
    }

    public static List<Account> findAll(DatabaseManager dbm) throws SQLException {
        ResultSet rs = dbm.findAll("bankaccounts");
         List<Account> tempCollection = new ArrayList<>();
         Statement tempStatement = dbm.getStatement();

            while (rs.next()) {
            String name = rs.getString("name");
            double balance = rs.getDouble("balance");
            Account tempAccount = new Account(name, balance, tempStatement);
            tempCollection.add(tempAccount);
             }
         return tempCollection;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}