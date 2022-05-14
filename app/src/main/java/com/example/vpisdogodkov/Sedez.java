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
   private String oznaka;
   private int status;

   public Sedez(int sifraSedeza, int sifraMesta, String oznaka, int status) {
      this.sifraSedeza = sifraSedeza;
      this.sifraMesta = sifraMesta;
      this.oznaka = oznaka;
      this.status = status;
   }

   public static List<Sedez> vrniSeznamSedezev(int sifraMesta) {
      return SQLHelper.sedez.vrniSedezeIzMesta(sifraMesta);
   }

   public int vrniSifroSedeza() {
      return sifraSedeza;
   }
   public int vrniSifroMesta() {
      return sifraMesta;
   }
   public String vrniOznako() {
      return oznaka;
   }
   public int vrniStatus() {
      return status;
   }

}