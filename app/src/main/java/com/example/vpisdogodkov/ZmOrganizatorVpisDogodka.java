package com.example.vpisdogodkov;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.List;

/***********************************************************************
 * Module:  ZmOrganizatorVpisDogodka.java
 * Author:  Mark and Jan
 * Purpose: Defines the Class ZmOrganizatorVpisDogodka
 ***********************************************************************/

public class ZmOrganizatorVpisDogodka extends AppCompatActivity {

   public KVpisDogodka kVpisDogodka;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      // Dovoljenja za dostop do baze
      ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
      StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
      StrictMode.setThreadPolicy(policy);

      // Inicializiraj kontroler za Vpis Dogodka
      kVpisDogodka = new KVpisDogodka();
      dbTest();
      pricniZVpisomDogodka();
   }

   private void dbTest(){

/*    DODAJ IZCAJALCA
      Izvajalec testIzvajalec = new Izvajalec("Tabu", "Tretja...");
      SQLHelper.izvajalec.insert(testIzvajalec);*/

/*    PRIDOBI VSA MESTA
      List<MestoPrireditve> testMesto = MestoPrireditve.vrniMesta();
      for (MestoPrireditve mesto : testMesto){
         System.out.println(mesto.vrniNaziv());
      }*/

/*    DODAJ PRIREDITEV
      long millis=System.currentTimeMillis();
      java.sql.Date date=new java.sql.Date(millis);
      Prireditev testPriredtev = new Prireditev(2, 3, "nek naslov", 33, date, date);
      Prireditev.dodajPrireditev(testPriredtev);*/

/*    PRIDOBI VSE SEDEZE OD DANEGA MESTA
      int sifraMesta = 2;
      List<Sedez> sedezi = Sedez.vrniSeznamSedezev(sifraMesta);
      for (Sedez sedez : sedezi)
         System.out.println(sedez.vrniSifroSedeza());*/

      int sifraMesta = 1;
      List<Termin> prostiTermini = Termin.vrniSeznamTerminovZaMestoPrireditve(sifraMesta);
      for (Termin termin : prostiTermini){
         System.out.println(termin.vrniSifraTermina());
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