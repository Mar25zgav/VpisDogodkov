package com.example.vpisdogodkov;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/***********************************************************************
 * Module:  ZmOrganizatorVpisDogodka.java
 * Author:  Mark and Jan
 * Purpose: Defines the Class ZmOrganizatorVpisDogodka
 ***********************************************************************/

public class ZmOrganizatorVpisDogodka extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

   KVpisDogodka kVpisDogodka;
   RecyclerView recyclerView;

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

   public void pricniZVpisomDogodka() {
      // Pridobi seznam mest
      List<MestoPrireditve> mestaPrireditve = kVpisDogodka.vrniSeznamMest();

      // Prikaži seznam mest
      prikaziSeznamMest(mestaPrireditve);
   }

   public void prikaziSeznamMest(List<MestoPrireditve> mestaPrireditve) {
      for (MestoPrireditve mestoPrireditve : mestaPrireditve)
         mestoPrireditve.setSteviloSedezev();

      izbiraMesta(mestaPrireditve);
   }

   public void izbiraMesta(List<MestoPrireditve> mestaPrireditve) {
      recyclerView = findViewById(R.id.recyclerView);
      MestaAdapter mestaAdapter = new MestaAdapter(mestaPrireditve);

      mestaAdapter.setOnItemClickListener((itemView, position) -> potrdiIzbiroMesta(mestaPrireditve.get(position)));

      recyclerView.setAdapter(mestaAdapter);
      recyclerView.setHasFixedSize(true);
   }

   public void potrdiIzbiroMesta(MestoPrireditve mestoPrireditve) {
      List<Termin> prostiTermini = kVpisDogodka.vrniProsteTermineZaMesto(mestoPrireditve.vrniSifro());

      // Alternativni tok
      if (prostiTermini.isEmpty())
         prikaziSporociloZasedenemTerminu();
      else // Osnovni tok
         prikaziSeznamProstihTerminov(prostiTermini);
   }

   public void prikaziSporociloZasedenemTerminu() {
      Toast toast = Toast.makeText(this, "Žal ni prostih terminov.", Toast.LENGTH_SHORT);
      toast.show();
   }

   public void prikaziSeznamProstihTerminov(List<Termin> prostiTermini) {
      DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
              this,
              Calendar.getInstance().get(Calendar.YEAR),
              Calendar.getInstance().get(Calendar.MONTH),
              Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
      );

      List<Calendar> dates = new ArrayList<>();
      for (Termin termin : prostiTermini) {
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(termin.vrniDatum());
         dates.add(calendar);
      }

      Calendar[] prostiDatumi = dates.toArray(new Calendar[dates.size()]);
      datePickerDialog.setSelectableDays(prostiDatumi);

      datePickerDialog.show(getSupportFragmentManager(), "Datepickerdialog");
   }

   @Override
   public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
      izberiTermin(year, monthOfYear, dayOfMonth);
   }

   public void izberiTermin(int year, int monthOfYear, int dayOfMonth) {
      zahtevajVnosPodrobnostiZaIzvajalca();
   }

   public void zahtevajVnosPodrobnostiZaIzvajalca() {
      setContentView(R.layout.activity_vnos_podrobnosti_izvajalca);
   }

   public void zahtevajVnosPodrobnostiZaPrireditev() {
      // TODO: implement
   }

   public void vnosPodrobnostiPrireditve() {
      // TODO: implement
   }

   public void prikaziSporociloOUspesnemVpisuDogodka() {
      // TODO: implement
   }

   public void vnosPodrobnostiIzvajalca() {
      // TODO: implement
   }

}