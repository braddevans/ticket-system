package tk.skybread.ticketsystem.database;

import tk.skybread.ticketsystem.tabs.LoggerTab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

public class DatabaseImpl implements Database {
    private static DatabaseImpl instance;
    public static String host     = "";
    public static String port     = "";
    public static String database = "";
    public static String username = "";
    public static String password = "";
    public static Connection con;

    // connect
    @Override
    public static void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database /*+"?autoReconnect=true"*/, username, password);
                LoggerTab.Logger(Level.WARNING,"connection to database opened");
            } catch (SQLException e) {
                LoggerTab.Logger(Level.WARNING,"Database Connection: " + e);
            }
        }
    }

    // disconnect
    public static void disconnect() {
        if (isConnected()) {
            try {
                con.close();
                LoggerTab.Logger(Level.WARNING,"connection to database closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // isConnected
    public static boolean isConnected() {
        return (con == null ? false : true);
    }

    // getConnection
    public static Connection getConnection() {
        return con;
    }

    public static DatabaseImpl getInstance() {
        return DatabaseImpl.instance;
    }

    public static void init(){
        CreateTable();
    }

    public static void CreateTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `" + database +
                "`.`Users` ( " +
                "`UserID` INT(7) NOT NULL AUTO_INCREMENT , " +
                "`Username` VARCHAR(32) NOT NULL , " +
                "`password` VARCHAR(32) NOT NULL , " +
                "PRIMARY KEY (`UserID`)) " +
                "ENGINE = InnoDB;";
        try {
            DatabaseImpl.connect();
            PreparedStatement stmt = DatabaseImpl.getConnection().prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //old code from previous project
    /*

    public static void ReadEffectdata() {
        String Player = "";
        String Server = "";
        String Effect = "";
        int Time = 0;
        int ID = 0;
        String Created = "";

        try {
            DatabaseImpl.connect();
            PreparedStatement ps = DatabaseImpl.getConnection().prepareStatement("SELECT ID,Effect,Time,Created,Server,Player FROM ActiveEffects");
            ResultSet rs = ps.executeQuery();
            if (rs.next() == true) {
                ID = rs.getInt("ID");
                Player = rs.getString("Player");
                Server = rs.getString("Server");
                Effect = rs.getString("Effect");
                Time = rs.getInt("Time");
                Created = rs.getTimestamp("Created").toString();
                int calculatedinseconds = ChatUtils.Formatter(Created,Time,ID);
                LoggerUtil.Logger("Calculated time in seconds: " + calculatedinseconds);
                //System.out.println("Calculated time in seconds: " + calculatedinseconds);
                EffectsUtil.setTime(calculatedinseconds * 1000);
                EffectsUtil.AddEffect(Player,Effect,Server,ID);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void InsertIntoActiveEffectsTable(String Player, String Server, String Effect, int Time){
        try {
            DatabaseImpl.connect();
            PreparedStatement ps = DatabaseImpl.getConnection().prepareStatement("INSERT IGNORE INTO ActiveEffects (Player,Server,Effect,Time) VALUES (?,?,?,?)");
            ps.setString(1, Player);
            ps.setString(2, Server);
            ps.setString(3, Effect);
            ps.setInt   (4, Time  );
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static int Readuserdata(String username) {
        int tokens = 0;
        try {
            DatabaseImpl.connect();
            PreparedStatement ps = DatabaseImpl.getConnection().prepareStatement("SELECT Tokens FROM UserTokens where Username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next() == true) {
                tokens = rs.getInt("Tokens");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tokens;
    }

    public static void InsertIntoTable(String sender, int Tokens){
        int tokens = DatabaseImpl.Readuserdata(sender) + Tokens;
        //System.out.println("Token update new amount:" + tokens);
        try {
            DatabaseImpl.connect();
            PreparedStatement ps = DatabaseImpl.getConnection().prepareStatement("UPDATE `UserTokens` SET `Tokens` = ? WHERE `UserTokens`.`Username` = ?;");
            ps.setInt(1, tokens);
            ps.setString(2, sender);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void removetokenfromuser(String sender){
        int Tokens = 1;
        int tokens = DatabaseImpl.Readuserdata(sender) - Tokens;
        //System.out.println("Token update new amount:" + tokens);
        try {
            DatabaseImpl.connect();
            PreparedStatement ps = DatabaseImpl.getConnection().prepareStatement("UPDATE `UserTokens` SET `Tokens` = ? WHERE `UserTokens`.`Username` = ?;");
            ps.setInt(1, tokens);
            ps.setString(2, sender);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void Removefromtable(int ID) {
        try {
            DatabaseImpl.connect();
            PreparedStatement statement = DatabaseImpl.getConnection().prepareStatement("delete from ActiveEffects where ID='" + ID + "';");
            statement.executeUpdate();
        } catch (SQLException e){
            LoggerUtil.Logger("Sql Exception removing effect: " + e);
        }
    }

    public static void SetIntoTable(String sender, int Tokens){
        //System.out.println("Set User token Ammount to:" + Tokens);
        try {
            DatabaseImpl.connect();
            PreparedStatement ps = DatabaseImpl.getConnection().prepareStatement("UPDATE `UserTokens` SET `Tokens` = ? WHERE `UserTokens`.`Username` = ?;");
            ps.setInt(1, Tokens);
            ps.setString(2, sender);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    */

}
