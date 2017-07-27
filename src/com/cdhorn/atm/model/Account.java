package com.cdhorn.atm.model;


import com.cdhorn.atm.helpers.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private double balance;
    private Statement statement;
    private int id;
    private double amount;

    public double getAmount() {
        return amount;
    }

    public Account(double amount, double balance, Statement statement) {

        this.balance = balance;
        this.amount = amount;
        this.statement = statement;
    }

    public Account(double amount, double balance, Statement statement, int id) {
        this( balance, amount, statement);
        this.id = id;
    }

    public void save() throws SQLException {
         String formattedSql = String.format("INSERT INTO bankaccounts (amount, balance) VALUES (%s, %s)",
                 amount, balance);
        statement.executeUpdate(formattedSql);
    }

    public static List<Account> findAll(DatabaseManager dbm) throws SQLException {
        ResultSet rs = dbm.findAll("bankaccounts");
         List<Account> tempCollection = new ArrayList<>();
         Statement tempStatement = dbm.getStatement();

            while (rs.next()) {
            double balance = rs.getDouble("balance");
                double amount = rs.getDouble("amount");

            Account tempAccount = new Account(amount, balance, tempStatement);
            tempCollection.add(tempAccount);
             }
         return tempCollection;
    }

    public static double getBalance(DatabaseManager dbm) throws SQLException {
        ResultSet rs = dbm.findAll("bankaccounts");
        double balance = rs.getDouble("balance");
        System.out.println("Your current balance is $" + balance);
        return balance;
    }

    public static double depositFunds(DatabaseManager dbm, double amount) throws
            SQLException {
        ResultSet rs = dbm.findAll("bankaccounts");
        double balance = rs.getDouble("balance");

        Statement tempStatement = dbm.getStatement();

        double newBalance = balance + amount;

        List<Account> tempCollection = new ArrayList<>();

        Account tempStat = new Account(amount, newBalance, tempStatement);
        tempStat.save();
        tempCollection.add(tempStat);

        System.out.println("Your new balance is $" + newBalance);
        return newBalance;
    }

    public static double withdrawFunds(DatabaseManager dbm, double funds) throws SQLException {
        ResultSet rs = dbm.findAll("bankaccounts");
        double balance = rs.getDouble("balance");

        Statement tempStatement = dbm.getStatement();

        if (balance - funds < 0) {
            System.out.println("Sorry, you don't have enough in your account for that transaction.");
            return balance;
        } else {
            double newBalance = balance - funds;
            List<Account> tempCollection = new ArrayList<>();
            Account tempStat = new Account(funds, newBalance, tempStatement);
            tempStat.save();
            tempCollection.add(tempStat);
            System.out.println("Your new balance is $" + newBalance);
            return newBalance;
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                ", balance=" + balance +
                '}';
    }
}