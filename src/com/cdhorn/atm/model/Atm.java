//package com.cdhorn.atm.model;
//
//
//import com.cdhorn.atm.helpers.DatabaseManager;
//
//import java.sql.SQLException;
//import java.util.Scanner;
//
//public class Atm {
//
//
//    public Atm() {
//    }
//
//    public void atmTransactions(DatabaseManager dbm, String name) throws SQLException {
//        System.out.println("Please choose one of the following from our menu options:");
//        System.out.println("1) Open an account");
//        System.out.println("2) See your account balance");
//        System.out.println("3) Make a withdrawal");
//        System.out.println("4) Make a deposit");
//        System.out.println("5) Finish transactions");
//        System.out.println("==============================================================================");
//        Scanner scanner = new Scanner(System.in);
//
//        int choice = scanner.nextInt();
//
//        switch (choice) {
//
//            case 1:
////                System.out.println("What name should be on the account?");
////                String name = scanner.next();
//                System.out.println("Please enter the amount of your opening deposit.");
//                double balance = scanner.nextDouble();
//                new Account(name, balance, dbm.getStatement()).save();
//                System.out.println("Thank you. Your account has been opened and you have a balance of $" + balance);
//                break;
//            case 2:
//
////                System.out.println("What is the name on the account?");
////                String name2 = scanner.next();
//                Account.getBalance(dbm, name);
//                break;
//            case 3:
//
////                System.out.println("What is the name on the account?");
////
////                String name3 = scanner.next();
//                System.out.println("How much would you like to withdraw?");
//                double withdraw = scanner.nextDouble();
//
//                Account.withdrawFunds(dbm, name, withdraw);
//                break;
//            case 4:
////                System.out.println("What is the name on the account?");
////                String name4 = scanner.next();
//                System.out.println("How much would you like to deposit?");
//                double deposit = scanner.nextDouble();
//                Account.depositFunds(dbm, name, deposit);
//                break;
//            case 5:
//                System.out.println("Thank you for banking with Global Bank " + name + ", have a nice day!");
//                break;
//            default:
//                System.out.println("Sorry, invalid input");
//        }
//    }
//
////    atmMachine(dbm);
//
//}
