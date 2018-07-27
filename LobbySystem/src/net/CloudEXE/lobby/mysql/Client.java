package net.CloudEXE.lobby.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Client{

    public static Connection c;

    public static void connect(){

        try{
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mcsystem?autoReconnect=true", "root", "Marantzlos");
            System.out.println("[MySQL] Connected.");
        } catch(SQLException e){
            System.out.println("[MySQL] Fehler");
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        if(c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void Update(String qry) {
        try {
            Statement stmt = c.createStatement();
            stmt.executeUpdate(qry);
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            disconnect();
            connect();
        }
    }

    public static boolean dataContainsUserCoins(String PlayerName) {
        try {
            PreparedStatement State = c.prepareStatement("SELECT * FROM Coins WHERE name=?");
            State.setString(1, PlayerName);

            ResultSet Result = State.executeQuery();

            boolean Contains = Result.next();

            State.close();
            Result.close();

            return Contains;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createUserCoins(String PlayerName) {
        try {
            PreparedStatement State = c.prepareStatement("INSERT INTO Coins values(?, 150)");
            State.setString(1, PlayerName);

            State.execute();
            State.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean dataContainsStatus(String PlayerName) {
        try {
            PreparedStatement State = c.prepareStatement("SELECT * FROM Status WHERE name=?");
            State.setString(1, PlayerName);

            ResultSet Result = State.executeQuery();

            boolean Contains = Result.next();

            State.close();
            Result.close();

            return Contains;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createUserStatus(String PlayerName, String Status) {
        try {
            PreparedStatement State = c.prepareStatement("INSERT INTO Status values(?, ?,?)");
            State.setString(1, PlayerName);
            State.setString(2, Status);
            State.setString(3,"");

            State.execute();
            State.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
