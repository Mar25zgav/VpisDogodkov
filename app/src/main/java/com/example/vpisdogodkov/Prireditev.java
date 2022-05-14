package com.example.vpisdogodkov;

import java.sql.Date;

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
   private Date zacetek;
   private Date konec;

   public Prireditev(int sifraPrireditve, int sifraIzvajalca, int sifraMesta, String naslov, double cenaVstopnice, Date zacetek, Date konec) {
      this.sifraPrireditve = sifraPrireditve;
      this.sifraIzvajalca = sifraIzvajalca;
      this.sifraMesta = sifraMesta;
      this.naslov = naslov;
      this.cenaVstopnice = cenaVstopnice;
      this.zacetek = zacetek;
      this.konec = konec;
   }

   public Prireditev(int sifraIzvajalca, int sifraMesta, String naslov, double cenaVstopnice, Date zacetek, Date konec) {
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
   public Date vrniZacetek() {
      return zacetek;
   }
   public Date vrniKonec() {
      return konec;
   }

   public int vrniTrajanje() {
      // TODO: implement
      // konec - zacetek
      return 0;
   }

   public static void dodajPrireditev(Prireditev prireditev) {
      SQLHelper.prireditev.insert(prireditev);
   }

}