package com.my.backend.dataBase;

import com.my.backend.api.ApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.postgresql.Driver;

@Component
public class BdConnection {

    final Properties info = getInfo();

    private final String URL = info.getProperty("url");
    private final String USER = info.getProperty("user");
    private final String PASSWORD = info.getProperty("password");
    private Connection conn;
    public final Logger LOG = LoggerFactory.getLogger(BdConnection.class);

    public Connection getConnection () throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            LOG.info("Connected!");
            return conn;
        }catch (Exception e){
            LOG.info(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Properties getInfo() {
        final Properties info = new Properties();

        try {
            info.load(this.getClass().getResourceAsStream("/db.cfg"));
        } catch (IOException ignored) {
            System.out.println("DataBase configure file error!");
        }

        return info;
    }
}
