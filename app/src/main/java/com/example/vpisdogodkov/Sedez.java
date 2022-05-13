package com.example.vpisdogodkov;

import java.util.List;

/***********************************************************************
 * Module:  Sedez.java
 * Author:  Mark and Jan
 * Purpose: Defines the Class Sedez
 ***********************************************************************/

public class Sedez {

   private String oznaka;
   private int status;

   public Sedez(String oznaka, int status) {
      this.oznaka = oznaka;
      this.status = status;
   }

   public static List<Sedez> vrniSeznamSedezev(String naziv) {
      // TODO: implement
      // SELECT oznaka, status FROM Sedez WHERE mesto = naziv;
      return null;
   }

   public String vrniOznako() {
      return oznaka;
   }

   public int vrniStatus() {
      return status;
   }

}