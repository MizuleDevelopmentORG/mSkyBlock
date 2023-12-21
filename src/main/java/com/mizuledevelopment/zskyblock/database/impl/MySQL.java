package com.mizuledevelopment.zskyblock.database.impl;

import com.mizuledevelopment.zskyblock.database.Storage;
import com.mizuledevelopment.zskyblock.zSkyBlock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL extends Storage {

    private Connection connection;

    public Connection getConnection(){
        if (connection != null) return connection;

        String url =
                "jdbc:mysql://" +
                zSkyBlock.getInstance().getConfiguration().getString("mysql.host") +
                ":" + zSkyBlock.getInstance().getConfiguration().getString("mysql.port") + "/"
                + zSkyBlock.getInstance().getConfiguration().getString("mysql.database");
        String user = zSkyBlock.getInstance().getConfiguration().getString("mysql.auth.user");
        String pass = zSkyBlock.getInstance().getConfiguration().getString("mysql.auth.password");

        try {
            return this.connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init(){

    }

    @Override
    public void load() {

    }

    @Override
    public void save() {

    }

    @Override
    public String name() {
        return "MySQL";
    }
}
