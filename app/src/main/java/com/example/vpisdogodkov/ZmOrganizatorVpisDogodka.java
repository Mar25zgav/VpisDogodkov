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
/*      Izvajalec test = new Izvajalec("MI2", "Druga skupina na pameti");
      SQLHelper.izvajalec.insert(test);*/

      List<MestoPrireditve> test = MestoPrireditve.vrniMesta();

      for (MestoPrireditve mesto : test){
         System.out.println(mesto.vrniNaziv());
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