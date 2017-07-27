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
    public static List<Account> findByName(DatabaseManager dbm, String name) throws SQLException {
        ResultSet rs = dbm.findByName(name);
        Statement tempStatement = dbm.getStatement();

        String nameRequest = rs.getString("name");

        double balance = rs.getInt("balance");
        List<Account> tempCollection = new ArrayList<>();

        Account tempStat = new Account(nameRequest, balance, tempStatement);
        tempCollection.add(tempStat);
        return tempCollection;

    }

    public static double getBalance(DatabaseManager dbm, String name) throws SQLException {
        ResultSet rs = dbm.findByName(name);
        double balance = rs.getDouble("balance");
        System.out.println("Your current balance is $" + balance);
        return balance;
    }

    public static double depositFunds(DatabaseManager dbm, String name, double funds) throws SQLException {
        ResultSet rs = dbm.findByName(name);
        String nameRequest = rs.getString("name");
        double balance = rs.getDouble("balance");

        Statement tempStatement = dbm.getStatement();

        double newBalance = balance + funds;
        List<Account> tempCollection = new ArrayList<>();

        Account tempStat = new Account(nameRequest, newBalance, tempStatement);
        tempCollection.add(tempStat);
        System.out.println("Your new balance is $" + newBalance);
        return newBalance;
    }

    public static double withdrawFunds(DatabaseManager dbm, String name, double funds) throws SQLException {
        ResultSet rs = dbm.findByName(name);
        String nameRequest = rs.getNString("name");
        double balance = rs.getDouble("balance");

        Statement tempStatement = dbm.getStatement();

        if (balance - funds < 0) {
            System.out.println("Sorry, you don't have enough in your account for that transaction.");
        } else {
            double newBalance = balance - funds;
            List<Account> tempCollection = new ArrayList<>();
            Account tempStat = new Account(nameRequest, newBalance, tempStatement);
            tempCollection.add(tempStat);
            System.out.println("Your new balance is $" + newBalance);
        }
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}