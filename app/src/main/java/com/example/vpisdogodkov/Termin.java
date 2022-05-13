package com.example.vpisdogodkov;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/***********************************************************************
 * Module:  Termin.java
 * Author:  Mark and Jan
 * Purpose: Defines the Class Termin
 ***********************************************************************/

public class Termin {

   private LocalDate datum;
   private LocalTime ura;

   public Termin(LocalDate datum, LocalTime ura) {
      this.datum = datum;
      this.ura = ura;
   }

   public static List<Termin> vrniSeznamTerminovZaMestoPrireditve(String mesto) {
      // TODO: implement
      // SELECT datum, ura FROM Termin WHERE zaseden = false;
      return null;
   }

}