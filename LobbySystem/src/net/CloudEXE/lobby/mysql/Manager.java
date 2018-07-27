package net.CloudEXE.lobby.mysql;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Manager {

    public static int getCoins(String PlayerName) {
        try {
            PreparedStatement State = Client.c.prepareStatement("SELECT * FROM Coins WHERE name=?;");
            State.setString(1, PlayerName);

            ResultSet Result = State.executeQuery();
            Result.next();

            int i = Result.getInt("c");

            Result.close();
            State.close();

            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static String getStatus(Player hiter) {

        try {
            PreparedStatement State = Client.c.prepareStatement("SELECT * FROM Status WHERE name=?");
            State.setString(1, hiter.getName());

            ResultSet Result = State.executeQuery();
            Result.next();

            String s = Result.getString("Status");

            Result.close();
            State.close();

            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "NON";

    }

    public static void addCoins(String PlayerName, Integer Coins) {

        int c = getCoins(PlayerName);
        c = c + Coins;

        Client.Update("UPDATE Coins SET c='" + c + "' WHERE name='" + PlayerName + "'");
    }

    public static void setCoins(String PlayerName, Integer Coins) {
        Client.Update("UPDATE Coins SET c='" + Coins + "' WHERE name='" + PlayerName + "'");
    }

    public static void removeCoins(String PlayerName, Integer Coins) {

        int c = getCoins(PlayerName);
        c = c - Coins;


        Client.Update("UPDATE Coins SET c='" + c + "' WHERE name='" + PlayerName + "'");
    }

    public static void setBannedBy(String PlayerName, String Status) {
        Client.Update("UPDATE Status SET status='" + Status + "' WHERE name='" + PlayerName + "'");
    }

}
