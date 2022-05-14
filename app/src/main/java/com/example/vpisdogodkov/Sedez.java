package com.example.vpisdogodkov;

import java.util.List;

/***********************************************************************
 * Module:  Sedez.java
 * Author:  Mark and Jan
 * Purpose: Defines the Class Sedez
 ***********************************************************************/

public class Sedez {
   private int sifraSedeza;
   private int sifraMesta;

   public Sedez(int sifraSedeza, int sifraMesta) {
      this.sifraSedeza = sifraSedeza;
      this.sifraMesta = sifraMesta;
   }

   public static List<Sedez> vrniSeznamSedezev(int sifraMesta) {
      return SQLHelper.sedez.vrniSedezeIzMesta(sifraMesta);
   }

   public static int vrniSteviloSedezev(int sifraMesta) {
      return SQLHelper.sedez.vrniSteviloSedezev(sifraMesta);
   }

   public int vrniSifroSedeza() {
      return sifraSedeza;
   }
   public int vrniSifroMesta() {
      return sifraMesta;
   }

}