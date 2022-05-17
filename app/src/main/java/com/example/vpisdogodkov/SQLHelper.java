package com.example.vpisdogodkov;

import android.text.PrecomputedText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
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

    public static class izvajalec {
        public static int insert(com.example.vpisdogodkov.Izvajalec izvajalec){
            int primaryKey = -1;
            try {
                connection = DriverManager.getConnection(url, username,password);
                PreparedStatement stmt=connection.prepareStatement("INSERT INTO Izvajalec VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1,izvajalec.vrniNaziv());//1 specifies the first parameter in the query
                stmt.setString(2,izvajalec.vrniOpis());

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    primaryKey = rs.getInt(1);
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return primaryKey;
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

    public static class prireditev {
        public static int insert(Prireditev prireditev){
            int primaryKey = -1;
            try {
                connection = DriverManager.getConnection(url, username,password);
                PreparedStatement stmt=connection.prepareStatement("INSERT INTO Prireditev VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1,prireditev.vrniSifroIzvajalca());
                stmt.setInt(2,prireditev.vrniSifroMesta());
                stmt.setString(3,prireditev.vrniNaslov());
                stmt.setDouble(4,prireditev.vrniCenoVstopnice());
                stmt.setTimestamp(5, new java.sql.Timestamp(prireditev.vrniZacetek().getTime().getTime()));
                stmt.setTimestamp(6, new java.sql.Timestamp(prireditev.vrniKonec().getTime().getTime()));

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    primaryKey = rs.getInt(1);
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return primaryKey;
        }
    }

    public static class sedez {
        public static List<Sedez> vrniSedezeIzMesta(int sifraMesta){
            List<Sedez> sedezi =new ArrayList<>();
            try {
                connection = DriverManager.getConnection(url, username,password);
                PreparedStatement stmt=connection.prepareStatement("SELECT * FROM Sedez WHERE sifraMesta = ?");
                stmt.setInt(1,sifraMesta);
                ResultSet rs=stmt.executeQuery();

                while (rs.next()) {
                    Sedez tempSedez = new Sedez(rs.getInt(1), rs.getInt(2));
                    sedezi.add(tempSedez);
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return sedezi;
        }

        public static int vrniSteviloSedezev(int sifraMesta){
            int steviloSedezev = -1;
            try {
                connection = DriverManager.getConnection(url, username,password);
                PreparedStatement stmt=connection.prepareStatement("SELECT COUNT(sifraSedeza) FROM Sedez WHERE sifraMesta = ?");
                stmt.setInt(1,sifraMesta);
                ResultSet rs=stmt.executeQuery();
                if (rs.next())
                    steviloSedezev = rs.getInt(1);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return steviloSedezev;
        }
    }

    public static class termin {
        public static List<Termin> vrniProsteTerminaZaMesto(int sifraMesta){
            List<Termin> prostiTermini =new ArrayList<>();
            try {
                connection = DriverManager.getConnection(url, username,password);
                PreparedStatement stmt=connection.prepareStatement("SELECT * FROM Termin WHERE sifraMesta = ? AND Zaseden = 0");
                stmt.setInt(1,sifraMesta);
                ResultSet rs=stmt.executeQuery();

                while (rs.next()) {
                    Termin tempTermin = new Termin(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getTime(4), rs.getByte(5));
                    prostiTermini.add(tempTermin);
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return prostiTermini;
        }
    }
}
