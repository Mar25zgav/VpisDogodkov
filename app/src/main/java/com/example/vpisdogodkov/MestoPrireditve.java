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
   private int steviloSedezev;
   private boolean expandable;

   public MestoPrireditve(int sifraMesta, String naziv, String naslov) {
      this.sifraMesta = sifraMesta;
      this.naziv = naziv;
      this.naslov = naslov;
      this.expandable = false;
   }

   public String vrniNaziv() {
      return naziv;
   }

   public String vrniNaslov() {
      return naslov;
   }

   public int vrniSifro() { return sifraMesta; }

   public void setSteviloSedezev() {
      steviloSedezev = SQLHelper.sedez.vrniSteviloSedezev(sifraMesta);
   }

   public int vrniSteviloSedezev() {
      return steviloSedezev;
   }

   public static List<Termin> vrniProsteTermine(int sifraMesta) {
      return Termin.vrniSeznamProstihTerminovZaMesto(sifraMesta);
   }

   public static List<MestoPrireditve> vrniMesta() {
      return SQLHelper.mestoPrireditve.getAll();
   }

   public boolean isExpandable() {
      return expandable;
   }

   public void setExpandable(boolean expandable) {
      this.expandable = expandable;
   }
}