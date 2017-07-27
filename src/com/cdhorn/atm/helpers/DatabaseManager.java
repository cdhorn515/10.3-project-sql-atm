package com.cdhorn.atm.helpers;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    Statement statement;

    public DatabaseManager(Connection connection) throws SQLException {
        this.statement = connection.createStatement();
    }

    public Statement getStatement() {
        return statement;
    }

    public void dropBankAccountsTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS bankaccounts");
    }

    public void createBankAccountsTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE bankaccounts (id INTEGER PRIMARY KEY, amount DOUBLE, " +
                "balance DOUBLE)");
    }

    public ResultSet findAll(String table) throws SQLException {
        String sqlQuery = String.format("SELECT * FROM bankaccounts ORDER BY id DESC");
        ResultSet rs = statement.executeQuery(sqlQuery);
        return rs;
    }
//    public ResultSet findByName(String name) throws SQLException {
//        String formattedSql = String.format("SELECT * FROM 'bankaccounts' WHERE name = '%s'", name);
//        ResultSet rs = statement.executeQuery(formattedSql);
//        return rs;
//    }


}

