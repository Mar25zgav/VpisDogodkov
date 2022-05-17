package com.example.vpisdogodkov;

import static java.lang.Integer.parseInt;

import android.Manifest;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
   private Group groupIzvajalec, groupPrireditev;

   // Vpis Prireditve
   int sifraIzvajalca = -1;
   int sifraMesta = -1;
   Calendar zacetekPrireditve = null;
   Calendar konecPrireditve = null;
   String imeMesta = "";
   String imeIzvajalca = "";
   Button btnZacetek, btnKonec;
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
      zacetekPrireditve = Calendar.getInstance();
      zacetekPrireditve.set(year, monthOfYear, dayOfMonth);
      konecPrireditve = Calendar.getInstance();
      konecPrireditve.set(year, monthOfYear, dayOfMonth);

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

   public void zahtevajVnosPodrobnostiZaPrireditev() {
      // Inicializiraj vse za vnos prireditve
      TextView textViewIzvajalec = (TextView) findViewById(R.id.textViewIzvajalec);
      TextView textViewMesto = (TextView) findViewById(R.id.textViewMesto);
      editTextNaslovPrireditve = (EditText) findViewById(R.id.editTextNaslovPrireditve);
      editTextNumberDecimal = (EditText) findViewById(R.id.editTextCenaVstopnice);
      groupPrireditev = (Group) findViewById(R.id.groupPrireditev);
      Button btnDodajPrireditev = (Button) findViewById(R.id.btnDodajPrireditev);
      btnDodajPrireditev.setOnClickListener(v -> vnosPodrobnostiPrireditve());

      btnZacetek = (Button) findViewById(R.id.zacetekBtn);
      btnZacetek.setOnClickListener(v -> showZacetekTimePicker());
      btnKonec = (Button) findViewById(R.id.konecBtn);
      btnKonec.setOnClickListener(v -> showKonecTimePicker());

      textViewMesto.setText("Mesto: " + imeMesta);
      textViewIzvajalec.setText("Izvajalec: " + imeIzvajalca);

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
      else{
         SQLHelper.termin.oznaciKotZaseden(sifraMesta, zacetekPrireditve.getTime());
         prikaziSporociloOUspesnemVpisuDogodka();
      }
   }

   private void showZacetekTimePicker() {
      TimePickerDialog.OnTimeSetListener onTimeSetListener = (timePicker, selectedHour, selectedMinute) -> {
         zacetekPrireditve.set(Calendar.HOUR_OF_DAY, selectedHour);
         zacetekPrireditve.set(Calendar.MINUTE, selectedMinute);
         btnZacetek.setText(String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute));
      };

      TimePickerDialog timePickerDialog = new TimePickerDialog(
              this,
              AlertDialog.THEME_HOLO_LIGHT,
              onTimeSetListener,
              zacetekPrireditve.get(Calendar.HOUR_OF_DAY),
              zacetekPrireditve.get(Calendar.MINUTE),
              true);

      timePickerDialog.setTitle("Izberi uro začetka");
      timePickerDialog.show();
   }

   private void showKonecTimePicker() {
      TimePickerDialog.OnTimeSetListener onTimeSetListener = (timePicker, selectedHour, selectedMinute) -> {
         konecPrireditve.set(Calendar.HOUR_OF_DAY, selectedHour);
         konecPrireditve.set(Calendar.MINUTE, selectedMinute);
         btnKonec.setText(String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute));
      };

      TimePickerDialog timePickerDialog = new TimePickerDialog(
              this,
              AlertDialog.THEME_HOLO_LIGHT,
              onTimeSetListener,
              konecPrireditve.get(Calendar.HOUR_OF_DAY),
              konecPrireditve.get(Calendar.MINUTE),
              true);

      timePickerDialog.setTitle("Izberi uro konca");
      timePickerDialog.show();
   }

   public void prikaziSporociloOUspesnemVpisuDogodka() {
      Toast.makeText(ZmOrganizatorVpisDogodka.this, "Prireditev uspesno dodana", Toast.LENGTH_LONG).show();

      groupPrireditev.setVisibility(View.GONE);
      recyclerView.setVisibility(View.VISIBLE);
   }

}