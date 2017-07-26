package com.cdhorn.atm.helpers;


import java.sql.Connection;
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
        statement.executeUpdate("CREATE TABLE bankaccounts (id INTEGER PRIMARY KEY, name STRING, balance DOUBLE, " +
                "amountIn DOUBLE, amountOut DOUBLE)");
    }
}
