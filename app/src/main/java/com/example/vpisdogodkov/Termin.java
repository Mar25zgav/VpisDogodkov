package com.example.vpisdogodkov;

import java.util.Date;
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
   private int zaseden;

   public Termin(int sifraTermina, int sifraMesta, Date datum, int zaseden) {
      this.sifraTermina = sifraTermina;
      this.sifraMesta = sifraMesta;
      this.datum = datum;
      this.zaseden = zaseden;
   }

   public static List<Termin> vrniSeznamProstihTerminovZaMesto(int sifraMesta) {
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

   public int vrniZaseden() {
      return zaseden;
   }
}