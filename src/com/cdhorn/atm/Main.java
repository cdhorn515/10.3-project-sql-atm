package com.cdhorn.atm;


import com.cdhorn.atm.helpers.DatabaseManager;
import com.cdhorn.atm.model.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bankaccounts.db")) {
            DatabaseManager dbm = new DatabaseManager(connection);
            dbm.dropBankAccountsTable();
            dbm.createBankAccountsTable();

            Statement statement = dbm.getStatement();

            Account christinaAccount = new Account("Christina", 150, statement);
            christinaAccount.save();

            Account samiAccount = new Account("Sami", 250.50, statement);
            samiAccount.save();

            Account seraAccount = new Account("Sera", 250.50, statement);
            seraAccount.save();

            List<Account> results = Account.findAll(dbm);
            for (Account account : results) {
                System.out.println(account);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.printf("Database connection problem!");
        }
    }
}
