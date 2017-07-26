package com.cdhorn.atm;


import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bankaccounts.db")) {

            Statement statement = connection.createStatement();

//            statement.executeUpdate("INSERT INTO bankaccounts (name, balance) VALUES ('Christina', 100.50)");
            ResultSet rs = statement.executeQuery("SELECT * FROM bankaccounts");

            while (rs.next()) {
                String name = rs.getString("name");
                double balance = rs.getDouble("balance");

                System.out.printf("%s %s", name, balance);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.printf("Database connection problem!");
        }
    }
}
