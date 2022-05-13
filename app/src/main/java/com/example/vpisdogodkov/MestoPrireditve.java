package com.example.vpisdogodkov;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

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

   public MestoPrireditve() {}

   public MestoPrireditve(String naziv, String naslov, List<Sedez> seznamSedezev) {
      this.naziv = naziv;
      this.naslov = naslov;
      this.seznamSedezev = seznamSedezev;
   }

   public int vrniSteviloSedezev() {
      return seznamSedezev.size();
   }

   public String vrniNaziv() {
      return naziv;
   }

   public List<Termin> vrniProsteTermine() {
      // TODO: implement
      return null;
   }

   public String vrniNaslov() {
      return naslov;
   }

   public List<MestoPrireditve> vrniMesta() {
      // TODO: implement
      return null;
   }

}