package com.example.vpisdogodkov;

/***********************************************************************
 * Module:  Izvajalec.java
 * Author:  Mark and Jan
 * Purpose: Defines the Class Izvajalec
 ***********************************************************************/

public class Izvajalec {

   private String naziv;
   private String opis;

   public Izvajalec(String naziv, String opis) {
      this.naziv = naziv;
      this.opis = opis;
   }

   public String vrniNaziv() {
      return naziv;
   }

   public String vrniOpis() {
      return opis;
   }

   public static void dodajIzvajalca(Izvajalec izvajalec) {
      SQLHelper.izvajalec.insert(izvajalec);
   }

}