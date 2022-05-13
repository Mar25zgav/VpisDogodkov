package com.example.vpisdogodkov;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/***********************************************************************
 * Module:  ZmOrganizatorVpisDogodka.java
 * Author:  Mark and Jan
 * Purpose: Defines the Class ZmOrganizatorVpisDogodka
 ***********************************************************************/

public class ZmOrganizatorVpisDogodka extends AppCompatActivity {
   private static String ip = "snf-58164.vm.okeanos-global.grnet.gr";// this is the host ip that your data base exists on you can use 10.0.2.2 for local host                                                    found on your pc. use if config for windows to find the ip if the database exists on                                                    your pc
   private static String port = "1433";// the port sql server runs on
   private static String Classes = "net.sourceforge.jtds.jdbc.Driver";// the driver that is required for this connection use                                                                           "org.postgresql.Driver" for connecting to postgresql
   private static String database = "TestDB";// the data base name
   private static String username = "SA";// the user name
   private static String password = "Geslo123";// the password
   private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

   private Connection connection = null;

   public KVpisDogodka kVpisDogodka;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      // Inicializiraj kontroler za Vpis Dogodka
      kVpisDogodka = new KVpisDogodka();
      dbTest();
      pricniZVpisomDogodka();
   }

   private void dbTest(){
      ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

      StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
      StrictMode.setThreadPolicy(policy);
      try {
         Class.forName(Classes);
         connection = DriverManager.getConnection(url, username,password);
         Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
         Statement stmt=connection.createStatement();
         ResultSet rs=stmt.executeQuery("SELECT * FROM Inventory");
         while(rs.next())
            System.out.println(rs.getInt(1)+" | "+rs.getString(2)+" | "+rs.getInt(3));
         connection.close();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
         Toast.makeText(this, "Class fail", Toast.LENGTH_SHORT).show();
      } catch (SQLException e) {
         e.printStackTrace();
         Toast.makeText(this, "Connected no", Toast.LENGTH_SHORT).show();
      }
   }

   public void pricniZVpisomDogodka() {
      // Pridobi seznam mest
      List<MestoPrireditve> mestaPrireditve = kVpisDogodka.vrniSeznamMest();

      // Prikaži seznam mest
      prikaziSeznamMest(mestaPrireditve);
   }

   public void prikaziSeznamMest(List<MestoPrireditve> mestaPrireditve) {
      // TODO: implement
   }

   public void izbiraMesta() {
      // TODO: implement
   }

   public void izberiTermin() {
      // TODO: implement
   }

   public void potrdiIzbiroMesta() {
      // TODO: implement
   }

   public void zahtevajVnosPodrobnostiZaPrireditev() {
      // TODO: implement
   }

   public void vnosPodrobnostiPrireditve() {
      // TODO: implement
   }

   public void prikaziPodrobnostiMesta() {
      // TODO: implement
   }

   public void prikaziSeznamProstihTerminov() {
      // TODO: implement
   }

   public void zahtevajVnosPodrobnostiZaIzvajalca() {
      // TODO: implement
   }

   public void vnosPodrobnostiIzvajalca() {
      // TODO: implement
   }

   public void prikaziSporociloOUspesnemVpisuDogodka() {
      // TODO: implement
   }

   public void prikaziSporociloZasedenemTerminu() {
      // TODO: implement
   }

}