package com.example.vpisdogodkov;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/***********************************************************************
 * Module:  Termin.java
 * Author:  Mark and Jan
 * Purpose: Defines the Class Termin
 ***********************************************************************/

public class Termin {
   private int sifraTermina;
   private int sifraMesta;
   private Date datum;
   private Time ura;
   private int zaseden;

   public Termin(int sifraTermina, int sifraMesta, Date datum, Time ura, int zaseden) {
      this.sifraTermina = sifraTermina;
      this.sifraMesta = sifraMesta;
      this.datum = datum;
      this.ura = ura;
      this.zaseden = zaseden;
   }

   public static List<Termin> vrniSeznamTerminovZaMestoPrireditve(int sifraMesta) {
      return SQLHelper.termin.vrniProsteTerminaZaMesto(sifraMesta);
   }

   public int vrniSifraTermina() {
      return sifraTermina;
   }

   public int vrniSifraMesta() {
      return sifraMesta;
   }

   public Date vrniDatum() {
      return datum;
   }

   public Time vrniUra() {
      return ura;
   }

   public int vrniZaseden() {
      return zaseden;
   }
}