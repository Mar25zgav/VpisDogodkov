package com.example.vpisdogodkov;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;

public class VnosPodrobnostiPrireditve extends AppCompatActivity {
    int sifraIzvajalca = -1;
    // SIFRAMESTA JE 1 SAMO ZA TEST!!!!!
    // SPREMENI NA -1
    int sifraMesta = 1;
    Date zacetekPrireditve = null;
    Date konecPrireditve = null;


    EditText editTextNaslovPrireditve, editTextNumberDecimal;
    ProgressBar progressBarPrireditev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vnos_podrobnosti_prireditve);
        setTitle("Dodaj Prireditev");

        // Dovoljenja za dostop do baze
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Bundle b = getIntent().getExtras();
        sifraIzvajalca = -1; // or other values
        String nazivIzvajalca = "";
        if(b != null){
            sifraIzvajalca = b.getInt("sifraIzvajalca");
            nazivIzvajalca = b.getString("nazivIzvajalca");
        }

        TextView textViewIzvajalec = (TextView) findViewById(R.id.textViewIzvajalec);
        TextView textViewMesto = (TextView) findViewById(R.id.textViewMesto);
        TextView textViewZacetek = (TextView) findViewById(R.id.textViewZacetek);
        TextView textViewKonec = (TextView) findViewById(R.id.textViewKonec);
        editTextNaslovPrireditve = (EditText) findViewById(R.id.editTextNaslovPrireditve);
        editTextNumberDecimal = (EditText) findViewById(R.id.editTextNumberDecimal);
        progressBarPrireditev = (ProgressBar) findViewById(R.id.progressBarPrireditev);

        textViewIzvajalec.setText(nazivIzvajalca);

        Button buttonDodajPrireditev = (Button) findViewById(R.id.buttonDodajPrireditev);
        buttonDodajPrireditev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dodajPrireditev();
            }
        });
    }

    public void dodajPrireditev(){
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

        progressBarPrireditev.setVisibility(View.VISIBLE);
        Prireditev prireditev = new Prireditev(sifraIzvajalca, sifraMesta, naslovPrireditve, parseInt(cenaVstopnice), zacetekPrireditve, konecPrireditve);
        int prireditevPrimaryKey = SQLHelper.prireditev.insert(prireditev);
        progressBarPrireditev.setVisibility(View.GONE);

        if (prireditevPrimaryKey == -1){
            Toast.makeText(VnosPodrobnostiPrireditve.this, "Nekaj se je zalomilo, znova poskusi kasneje", Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(VnosPodrobnostiPrireditve.this, "Prireditev uspesno dodana", Toast.LENGTH_LONG).show();
    }
}