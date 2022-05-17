package com.example.vpisdogodkov;

import android.os.Build;

import androidx.annotation.RequiresApi;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

/***********************************************************************
 * Module:  Prireditev.java
 * Author:  Mark and Jan
 * Purpose: Defines the Class Prireditev
 ***********************************************************************/

public class Prireditev {

   private int sifraPrireditve;
   private int sifraIzvajalca;
   private int sifraMesta;
   private String naslov;
   private double cenaVstopnice;
   private Calendar zacetek;
   private Calendar konec;

   public Prireditev(int sifraPrireditve, int sifraIzvajalca, int sifraMesta, String naslov, double cenaVstopnice, Calendar zacetek, Calendar konec) {
      this.sifraPrireditve = sifraPrireditve;
      this.sifraIzvajalca = sifraIzvajalca;
      this.sifraMesta = sifraMesta;
      this.naslov = naslov;
      this.cenaVstopnice = cenaVstopnice;
      this.zacetek = zacetek;
      this.konec = konec;
   }

   public Prireditev(int sifraIzvajalca, int sifraMesta, String naslov, double cenaVstopnice, Calendar zacetek, Calendar konec) {
      this.sifraIzvajalca = sifraIzvajalca;
      this.sifraMesta = sifraMesta;
      this.naslov = naslov;
      this.cenaVstopnice = cenaVstopnice;
      this.zacetek = zacetek;
      this.konec = konec;
   }

   public int vrniSifroPrirediteve() {
      return sifraPrireditve;
   }
   public int vrniSifroIzvajalca() {
      return sifraIzvajalca;
   }
   public int vrniSifroMesta() {
      return sifraMesta;
   }
   public String vrniNaslov() {
      return naslov;
   }
   public double vrniCenoVstopnice() {
      return cenaVstopnice;
   }
   public Calendar vrniZacetek() {
      return zacetek;
   }
   public Calendar vrniKonec() {
      return konec;
   }

   @RequiresApi(api = Build.VERSION_CODES.O)
   public int vrniTrajanje() {
      return (int) ChronoUnit.HOURS.between(zacetek.toInstant(), konec.toInstant());
   }

   public static void dodajPrireditev(Prireditev prireditev) {
      SQLHelper.prireditev.insert(prireditev);
   }

}