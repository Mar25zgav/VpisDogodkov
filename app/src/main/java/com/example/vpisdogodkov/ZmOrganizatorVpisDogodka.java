package com.example.vpisdogodkov;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/***********************************************************************
 * Module:  ZmOrganizatorVpisDogodka.java
 * Author:  Mark and Jan
 * Purpose: Defines the Class ZmOrganizatorVpisDogodka
 ***********************************************************************/

public class ZmOrganizatorVpisDogodka extends AppCompatActivity implements View.OnClickListener {

   KVpisDogodka kVpisDogodka;
   RecyclerView recyclerView;
   Button izberiBtn;

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

      pricniZVpisomDogodka();
   }

   private void dbTest(){

/*    DODAJ IZVAJALCA
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

/*    //PRIDOBI VSE SEDEZE OD DANEGA MESTA
      int sifraMesta = 2;
      List<Sedez> sedezi = Sedez.vrniSeznamSedezev(sifraMesta);
      for (Sedez sedez : sedezi)
         System.out.println(sedez.vrniSifroSedeza());*/

/*    //PRIDOBI VSE PROTSTE TERMINE ZA DANO MESTO
      int sifraMesta = 1;
      List<Termin> prostiTermini = Termin.vrniSeznamProstihTerminovZaMesto(sifraMesta);
      for (Termin termin : prostiTermini){
         System.out.println(termin.vrniSifraTermina());
      }*/

      //PRIDOBI STEVILO SEDEZEV ZA DANO MESTO
      int sifraMesta = 2;
      System.out.println(Sedez.vrniSteviloSedezev(sifraMesta));
   }

   public void pricniZVpisomDogodka() {
      // Pridobi seznam mest
      List<MestoPrireditve> mestaPrireditve = kVpisDogodka.vrniSeznamMest();

      // Prikaži seznam mest
      prikaziSeznamMest(mestaPrireditve);
   }

   public void prikaziSeznamMest(List<MestoPrireditve> mestaPrireditve) {
      for (MestoPrireditve mestoPrireditve : mestaPrireditve)
         mestoPrireditve.setSteviloSedezev();

      recyclerView = findViewById(R.id.recyclerView);
      MestaAdapter mestaAdapter = new MestaAdapter(mestaPrireditve);
      recyclerView.setAdapter(mestaAdapter);
      recyclerView.setHasFixedSize(true);
   }

   @Override
   public void onClick(View view) {
      setContentView(R.layout.activity_vnos_podrobnosti_izvajalca);
   }

   public void izbiraMesta() {
      // TODO: implement
   }

   public void izberiTermin() {
      // TODO: implement
   }

   public void potrdiIzbiroMesta() {
      setContentView(R.layout.activity_main);
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