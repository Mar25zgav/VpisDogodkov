package com.example.vpisdogodkov;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class VnosPodrobnostiIzvajalca extends AppCompatActivity {
    private EditText editTextNazivIzvajalca, editTextOpisIzvajalca;
    private Button dodajButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vnos_podrobnosti_izvajalca);
        setTitle("Dodaj Izvajalca");

        // Dovoljenja za dostop do baze
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        editTextNazivIzvajalca = (EditText) findViewById(R.id.editTextNazivIzvajalca);
        editTextOpisIzvajalca = (EditText) findViewById(R.id.editTextOpisIzvajalca);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        dodajButton = (Button) findViewById(R.id.buttonDodajIzvajalca);
        dodajButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dodajIzvajalca();
            }
        });
    }

    public void dodajIzvajalca(){
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
        SQLHelper.izvajalec.insert(izvajalec);
        progressBar.setVisibility(View.GONE);
    }

}