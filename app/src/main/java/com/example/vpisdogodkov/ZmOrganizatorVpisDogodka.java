package com.example.vpisdogodkov;

import static java.lang.Integer.parseInt;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.sql.Date;
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

   // Vpis Izvajalca
   private EditText editTextNazivIzvajalca, editTextOpisIzvajalca;
   private ProgressBar progressBar;
   private Group groupIzvajalec;

   // Vpis Prireditve
   int sifraIzvajalca = -1;
   int sifraMesta = -1;
   Date zacetekPrireditve = null;
   Date konecPrireditve = null;
   String imeMesta = "";
   String imeIzvajalca = "";

   EditText editTextNaslovPrireditve, editTextNumberDecimal;

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
      else{ // Osnovni tok
         sifraMesta = mestoPrireditve.vrniSifro();
         imeMesta = mestoPrireditve.vrniNaziv();
         prikaziSeznamProstihTerminov(prostiTermini);
      }
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
      zacetekPrireditve = new Date(year, monthOfYear, dayOfMonth);
      konecPrireditve = new Date(year, monthOfYear, dayOfMonth);
      zahtevajVnosPodrobnostiZaIzvajalca();
   }

   public void zahtevajVnosPodrobnostiZaIzvajalca() {
      // Inicializiraj vse za vnos izvajalca
      editTextNazivIzvajalca = (EditText) findViewById(R.id.editTextNazivIzvajalca);
      editTextOpisIzvajalca = (EditText) findViewById(R.id.editTextOpisIzvajalca);
      progressBar = (ProgressBar) findViewById(R.id.progressBar);
      groupIzvajalec = (Group) findViewById(R.id.groupIzvajalec);
      Button dodajButton = (Button) findViewById(R.id.buttonDodajIzvajalca);
      dodajButton.setOnClickListener(v -> vnosPodrobnostiIzvajalca());

      recyclerView.setVisibility(View.GONE);
      groupIzvajalec.setVisibility(View.VISIBLE);
   }

   public void zahtevajVnosPodrobnostiZaPrireditev() {
      // Inicializiraj vse za vnos prireditve
      TextView textViewIzvajalec = (TextView) findViewById(R.id.textViewIzvajalec);
      TextView textViewMesto = (TextView) findViewById(R.id.textViewMesto);
      TextView textViewZacetek = (TextView) findViewById(R.id.textViewZacetek);
      TextView textViewKonec = (TextView) findViewById(R.id.textViewKonec);
      editTextNaslovPrireditve = (EditText) findViewById(R.id.editTextNaslovPrireditve);
      editTextNumberDecimal = (EditText) findViewById(R.id.editTextCenaVstopnice);
      Group groupPrireditev = (Group) findViewById(R.id.groupPrireditev);
      Button buttonDodajPrireditev = (Button) findViewById(R.id.buttonDodajPrireditev);
      buttonDodajPrireditev.setOnClickListener(v -> vnosPodrobnostiPrireditve());

      textViewIzvajalec.setText(imeIzvajalca);
      textViewMesto.setText(imeMesta);
      if (zacetekPrireditve != null)
         textViewZacetek.setText(zacetekPrireditve.toString());
      if (konecPrireditve != null)
         textViewKonec.setText(konecPrireditve.toString());

      groupIzvajalec.setVisibility(View.GONE);
      groupPrireditev.setVisibility(View.VISIBLE);
   }

   public void vnosPodrobnostiPrireditve() {
      String naslovPrireditve = editTextNaslovPrireditve.getText().toString();
      String cenaVstopnice = editTextNumberDecimal.getText().toString();

      if (naslovPrireditve.isEmpty()){
         editTextNaslovPrireditve.setError("Polje je obvezno!");
         editTextNaslovPrireditve.requestFocus();
         return;
      }

      if (cenaVstopnice.isEmpty()){
         editTextNumberDecimal.setError("Polje je obvezno!");
         editTextNumberDecimal.requestFocus();
         return;
      }

      progressBar.setVisibility(View.VISIBLE);
      Prireditev prireditev = new Prireditev(sifraIzvajalca, sifraMesta, naslovPrireditve, parseInt(cenaVstopnice), zacetekPrireditve, konecPrireditve);
      int prireditevPrimaryKey = SQLHelper.prireditev.insert(prireditev);
      progressBar.setVisibility(View.GONE);

      if (prireditevPrimaryKey == -1)
         Toast.makeText(ZmOrganizatorVpisDogodka.this, "Nekaj se je zalomilo, znova poskusi kasneje", Toast.LENGTH_LONG).show();
      else
         prikaziSporociloOUspesnemVpisuDogodka();
   }

   public void prikaziSporociloOUspesnemVpisuDogodka() {
      Toast.makeText(ZmOrganizatorVpisDogodka.this, "Prireditev uspesno dodana", Toast.LENGTH_LONG).show();
   }

   public void vnosPodrobnostiIzvajalca() {
      String naziv = editTextNazivIzvajalca.getText().toString();
      String opis = editTextOpisIzvajalca.getText().toString();

      if (naziv.isEmpty()){
         editTextNazivIzvajalca.setError("Polje je obvezno!");
         editTextNazivIzvajalca.requestFocus();
         return;
      }

      if (opis.isEmpty()){
         editTextOpisIzvajalca.setError("Polje je obvezno!");
         editTextOpisIzvajalca.requestFocus();
         return;
      }

      progressBar.setVisibility(View.VISIBLE);
      Izvajalec izvajalec = new Izvajalec(naziv, opis);
      int izvajalecPrimaryKey = SQLHelper.izvajalec.insert(izvajalec);
      progressBar.setVisibility(View.GONE);

      if (izvajalecPrimaryKey == -1){
         Toast.makeText(ZmOrganizatorVpisDogodka.this, "Nekaj se je zalomilo, znova poskusi kasneje", Toast.LENGTH_LONG).show();
         return;
      }
      sifraIzvajalca = izvajalecPrimaryKey;
      imeIzvajalca = naziv;
      Toast.makeText(ZmOrganizatorVpisDogodka.this, "Izvajalec uspesno dodan", Toast.LENGTH_LONG).show();

      zahtevajVnosPodrobnostiZaPrireditev();
   }

}