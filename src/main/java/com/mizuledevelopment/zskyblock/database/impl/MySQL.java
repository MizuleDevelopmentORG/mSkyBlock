package com.mizuledevelopment.zskyblock.database.impl;

import com.mizuledevelopment.zskyblock.database.Storage;
import com.mizuledevelopment.zskyblock.island.Island;
import com.mizuledevelopment.zskyblock.profile.Profile;
import com.mizuledevelopment.zskyblock.utils.wrapper.impl.IslandWrapper;
import com.mizuledevelopment.zskyblock.utils.wrapper.impl.ProfileWrapper;
import com.mizuledevelopment.zskyblock.zSkyBlock;
import org.bukkit.Bukkit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        try {
            Statement statement = getConnection().createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS profiles(player varchar(36) primary key, island varchar(36), reclaimed varchar(8))");
            statement.execute("CREATE TABLE IF NOT EXISTS islands(name varchar(36) primary key, leader varchar(36), loc1 varchar(36), loc2 varchar(36), size int, points int)");
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void load() {
        String islandQuery = "SELECT * FROM islands ORDER BY name";
        String profileQuery = "SELECT * FROM profiles ORDER BY player";

        IslandWrapper wrapper = new IslandWrapper(null, null, null, null, 0, 0);

        try (PreparedStatement islandStatement = getConnection().prepareStatement(islandQuery,
                ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
             ResultSet result = islandStatement.executeQuery()) {
            while (result.next()) {
                String name = result.getString("name");
                String leader = result.getString("leader");
                String location1 = result.getString("loc1");
                String location2 = result.getString("loc2");
                int size = result.getInt("size");
                int points = result.getInt("points");

                wrapper.setName(name);
                wrapper.setLeader(leader);
                wrapper.setCuboid(new ArrayList<>(List.of(location1, location2)));
                wrapper.setSize(size);
                wrapper.setPoints(points);
                wrapper.setMembers(new ArrayList<>());

                try (PreparedStatement profileStatement = getConnection().prepareStatement(profileQuery,
                        ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                     ResultSet rs = profileStatement.executeQuery();) {
                    while (rs.next()) {
                        String profileUUID = rs.getString("player");
                        String island = rs.getString("island");
                        String reclaimed = rs.getString("reclaimed");

                        if (island.equalsIgnoreCase(wrapper.getName())) {
                            wrapper.addMember(profileUUID);
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        zSkyBlock.getInstance().getIslandManager().getIslands().add(wrapper.wrap());
    }

    @Override
    public void save() {

    }

    @Override
    public void loadPlayer(UUID uuid) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM profiles WHERE player = ?");
            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Bukkit.getConsoleSender().sendMessage("BOOL " + resultSet.getString("reclaimed"));
                zSkyBlock.getInstance().getProfileManager().getProfiles()
                        .add(new ProfileWrapper(resultSet.getString("player"),
                                resultSet.getString("island"),
                                false).wrap());
            } else {
                zSkyBlock.getInstance().getProfileManager().getProfiles()
                        .add(new Profile(uuid, null, false));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String name() {
        return "MySQL";
    }
}
