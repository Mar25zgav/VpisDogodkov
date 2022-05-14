package com.example.vpisdogodkov;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLHelper {
    private static final String ip = "snf-58164.vm.okeanos-global.grnet.gr";// this is the host ip that your data base exists on you can use 10.0.2.2 for local host                                                    found on your pc. use if config for windows to find the ip if the database exists on                                                    your pc
    private static final String port = "1433";// the port sql server runs on
    private static final String Classes = "net.sourceforge.jtds.jdbc.Driver";// the driver that is required for this connection use                                                                           "org.postgresql.Driver" for connecting to postgresql
    private static final String database = "RIS_DB";// the data base name
    private static final String username = "SA";// the user name
    private static final String password = "Geslo123";// the password
    private static final String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private static Connection connection = null;

    private static ArrayList<HashMap<String, String>> general_select(String query){
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(url, username,password);
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();


            while (rs.next()) {
                HashMap<String, String> row = new HashMap<String, String>();
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = rs.getString(i);
                    row.put(rsmd.getColumnName(i), columnValue);
                }
                result.add(row);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void general_insert(String table, String columns, String values){
        String query = "INSERT INTO " + table + " (" + columns + ") VALUES (" + values + ")";
        System.out.println(query);
        try {
            connection = DriverManager.getConnection(url, username,password);
            Statement stmt=connection.createStatement();
            stmt.executeQuery(query);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static class izvajalec {
        public static void insert(com.example.vpisdogodkov.Izvajalec izvajalec){
            try {
                connection = DriverManager.getConnection(url, username,password);
                PreparedStatement stmt=connection.prepareStatement("INSERT INTO Izvajalec VALUES(?,?)");
                stmt.setString(1,izvajalec.vrniNaziv());//1 specifies the first parameter in the query
                stmt.setString(2,izvajalec.vrniOpis());

                int i=stmt.executeUpdate();
                System.out.println(i+" records inserted");
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static class mestoPrireditve {
        public static List<MestoPrireditve> getAll(){
            List<MestoPrireditve> mesta =new ArrayList<>();
            try {
                connection = DriverManager.getConnection(url, username,password);
                PreparedStatement stmt=connection.prepareStatement("SELECT * FROM MestoPrireditve");
                ResultSet rs=stmt.executeQuery();

                while (rs.next()) {
                    MestoPrireditve tempMesto = new MestoPrireditve(rs.getInt(1), rs.getString(2), rs.getString(3));
                    mesta.add(tempMesto);
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return mesta;
        }
    }
}
