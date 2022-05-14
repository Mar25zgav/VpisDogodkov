package com.example.vpisdogodkov;

import java.util.List;

/***********************************************************************
 * Module:  MestoPrireditve.java
 * Author:  Mark and Jan
 * Purpose: Defines the Class MestoPrireditve
 ***********************************************************************/

public class MestoPrireditve {

   private int sifraMesta;
   private String naziv;
   private String naslov;

   public MestoPrireditve(int sifraMesta, String naziv, String naslov) {
      this.sifraMesta = sifraMesta;
      this.naziv = naziv;
      this.naslov = naslov;
   }

   public String vrniNaziv() {
      return naziv;
   }

   public String vrniNaslov() {
      return naslov;
   }

   public int vrniSifro() { return sifraMesta; }

   public static List<Termin> vrniProsteTermine(String naziv) {
      return Termin.vrniSeznamTerminovZaMestoPrireditve(naziv);
   }

   public static List<MestoPrireditve> vrniMesta() {
      // TODO: implement
      // SELECT naziv, naslov FROM MestoPrireditve;
      return SQLHelper.mestoPrireditve.getAll();
   }

}