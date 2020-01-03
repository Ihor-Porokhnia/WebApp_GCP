package Databases;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;


public class SQLiteClass {
    public static Connection conn;
    public static Statement stat;
    public static ResultSet rs;

    public static void Conn() throws ClassNotFoundException, SQLException, NamingException {
        //Class.forName("com.mysql.jdbc.Driver");
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://inst1.bugoga.ga:3306/SimpleDatabase";
        String username = "remmie";
        String password = "nemA_666";
        Class.forName(driver);
        conn = DriverManager.getConnection(url, username, password);
        //System.out.println("Here");
    }

    public static void addName(String name) throws ClassNotFoundException, SQLException {
        try {
           // System.out.println("Here1");
            Conn();
           // System.out.println("Here2");
            stat = conn.createStatement();
            //System.out.println("Here3");
            //System.out.println(conn.getClientInfo());
            PreparedStatement statement = conn.prepareStatement("INSERT INTO SimpleDatabase.NAMES (name) VALUES (?)");
            statement.setString(1, name);
            statement.execute();
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            stat.close();
            CloseDB();
        }
    }

    public static ArrayList<String> getAllNames() throws ClassNotFoundException, SQLException, NamingException
    {
        ArrayList<String> names = new ArrayList<String>();

        Conn();
        stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("select name from SimpleDatabase.NAMES");
        
        while (rs.next()) {
            names.add(rs.getString("name"));
        }

        rs.close();
        stat.close();
        CloseDB();

        return names;
    }

    public static void CloseDB() throws ClassNotFoundException, SQLException {
        conn.close();
    }
}
