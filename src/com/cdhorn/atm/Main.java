package com.cdhorn.atm;


import com.cdhorn.atm.helpers.DatabaseManager;
import com.cdhorn.atm.model.Account;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        String name;

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bankaccounts.db")) {
            DatabaseManager dbm = new DatabaseManager(connection);
            dbm.dropBankAccountsTable();

            System.out.println("==============================================================================");
            System.out.println("Welcome to Global Bank ATM. Please let me know your name:");
            Scanner scanner = new Scanner(System.in);
            name = scanner.next();
//            dbm.createBankAccountsTable();

            atmMachine(dbm, name);
//
//            Statement statement = dbm.getStatement();
//
//            Account christinaAccount = new Account("Christina", 150, statement);
//            christinaAccount.save();
//
//            Account samiAccount = new Account("Sami", 250.50, statement);
//            samiAccount.save();
//
//            Account seraAccount = new Account("Sera", 250.50, statement);
//            seraAccount.save();
//
//            List<Account> results = Account.findAll(dbm);
//            for (Account account : results) {
//                System.out.println(account);
//            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.printf("Database connection problem!");
        }
    }

    public static void atmMachine(DatabaseManager dbm, String name) throws SQLException {
        System.out.println("Please choose one of the following from our menu options:");
        System.out.println("1) Open an account");
        System.out.println("2) See your account balance");
        System.out.println("3) Make a withdrawal");
        System.out.println("4) Make a deposit");
        System.out.println("5) Finish transactions");
        System.out.println("==============================================================================");
        Scanner scanner = new Scanner(System.in);

        int choice = scanner.nextInt();

        switch(choice){

            case 1:
                System.out.println("Please enter the amount of your opening deposit.");
                double balance = scanner.nextDouble();
                new Account(name, balance, dbm.getStatement()).save();
                System.out.println("Thank you. Your account has been opened and you have a balance of $" + balance);
                break;
            case 2:
                Account.getBalance(dbm, name);
                break;
            case 3:
                System.out.println("How much would you like to withdraw?");
                double withdraw = scanner.nextDouble();
                Account.withdrawFunds(dbm, name, withdraw);
                break;
            case 4:
                System.out.println("How much would you like to deposit?");
                double deposit = scanner.nextDouble();
                Account.depositFunds(dbm, name, deposit);
                break;
            case 5:
                System.out.println("Thank you for banking with Global Bank " + name + ", have a nice day!");
                break;
            default:
                System.out.println("Sorry, invalid input");
        }

        atmMachine(dbm, name);
    }

}
