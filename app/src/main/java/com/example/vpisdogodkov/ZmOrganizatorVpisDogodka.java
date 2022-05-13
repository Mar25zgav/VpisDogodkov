package com.example.vpisdogodkov;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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
      pricniZVpisomDogodka();
   }

   public void pricniZVpisomDogodka() {
      // Inicializiraj kontroler za Vpis Dogodka
      kVpisDogodka = new KVpisDogodka();
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