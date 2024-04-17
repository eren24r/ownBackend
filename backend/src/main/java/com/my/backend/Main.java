package com.my.backend;

import com.my.backend.dataBase.BdConnection;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        BdConnection bdConnection = new BdConnection();

        bdConnection.getConnection();
    }
}
