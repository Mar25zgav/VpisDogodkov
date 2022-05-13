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

   public Sedez() { }

   public Sedez(String oznaka, int status) {
      this.oznaka = oznaka;
      this.status = status;
   }

   public List<Sedez> vrniSeznamSedezev() {
      // TODO: implement
      return null;
   }

   public String vrniOznako() {
      return oznaka;
   }

   public int vrniStatus() {
      return status;
   }

}