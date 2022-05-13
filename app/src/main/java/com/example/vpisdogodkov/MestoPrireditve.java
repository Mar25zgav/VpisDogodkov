package com.example.vpisdogodkov;

import java.util.List;

/***********************************************************************
 * Module:  MestoPrireditve.java
 * Author:  Mark and Jan
 * Purpose: Defines the Class MestoPrireditve
 ***********************************************************************/

public class MestoPrireditve {

   private String naziv;
   private String naslov;
   private List<Sedez> seznamSedezev;

   public MestoPrireditve(String naziv, String naslov, List<Sedez> seznamSedezev) {
      this.naziv = naziv;
      this.naslov = naslov;
      this.seznamSedezev = Sedez.vrniSeznamSedezev(naziv);
   }

   public int vrniSteviloSedezev() {
      return seznamSedezev.size();
   }

   public String vrniNaziv() {
      return naziv;
   }

   public String vrniNaslov() {
      return naslov;
   }

   public static List<Termin> vrniProsteTermine(String naziv) {
      return Termin.vrniSeznamTerminovZaMestoPrireditve(naziv);
   }

   public static List<MestoPrireditve> vrniMesta() {
      // TODO: implement
      // SELECT naziv, naslov FROM MestoPrireditve;
      return null;
   }

}