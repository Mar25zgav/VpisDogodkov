package com.example.vpisdogodkov;

import java.time.LocalDateTime;

/***********************************************************************
 * Module:  Prireditev.java
 * Author:  Mark and Jan
 * Purpose: Defines the Class Prireditev
 ***********************************************************************/

public class Prireditev {

   private int sifra;
   private String naslov;
   private double cenaVstopnice;
   private LocalDateTime zacetek;
   private LocalDateTime konec;

   public Prireditev(int sifra, String naslov, double cenaVstopnice, LocalDateTime zacetek, LocalDateTime konec) {
      this.sifra = sifra;
      this.naslov = naslov;
      this.cenaVstopnice = cenaVstopnice;
      this.zacetek = zacetek;
      this.konec = konec;
   }

   public String vrniNaslov() {
      return naslov;
   }

   public int vrniTrajanje() {
      // TODO: implement
      // konec - zacetek
      return 0;
   }

   public double vrniCeno() {
      return cenaVstopnice;
   }

   public static void dodajPrireditev(Prireditev prireditev) {
      // TODO: implement
      // INSERT INTO Prireditev VALUES (prireditev.sifra, ...)
   }

}