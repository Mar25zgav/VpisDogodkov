package com.example.vpisdogodkov;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/***********************************************************************
 * Module:  KVpisDogodka.java
 * Author:  Mark and Jan
 * Purpose: Defines the Class KVpisDogodka
 ***********************************************************************/

public class KVpisDogodka {

   public java.util.Collection<Prireditev> prireditev;
   public SvSistemEposte svSistemEposte;
   public java.util.Collection<Izvajalec> izvajalec;
   public MestoPrireditve[] mestoPrireditve;

   public void zakljuciZVpisomDogodka() {
      // TODO: implement
   }

   public void dodajNovoPrireditev() {
      // TODO: implement
   }

   public HashMap<String, String> vrniPodrobnostiMestaPrireditve() {
      // TODO: implement
      return null;
   }

   public List<MestoPrireditve> vrniSeznamMest() {
      // TODO: implement
      return null;
   }

   public List<Termin> vrniProsteTermineZaMesto() {
      // TODO: implement
      return null;
   }

   public void dodajNovegaIzvajalca() {
      // TODO: implement
   }
   

   public Collection<Prireditev> getPrireditev() {
      if (prireditev == null)
         prireditev = new HashSet<>();
      return prireditev;
   }

   public Iterator<Prireditev> getIteratorPrireditev() {
      if (prireditev == null)
         prireditev = new HashSet<>();
      return prireditev.iterator();
   }

   public void setPrireditev(Collection<Prireditev> newPrireditev) {
      removeAllPrireditev();
      for (Prireditev value : newPrireditev) addPrireditev(value);
   }

   public void addPrireditev(Prireditev newPrireditev) {
      if (newPrireditev == null)
         return;
      if (this.prireditev == null)
         this.prireditev = new HashSet<>();
      if (!this.prireditev.contains(newPrireditev))
         this.prireditev.add(newPrireditev);
   }

   public void removePrireditev(Prireditev oldPrireditev) {
      if (oldPrireditev == null)
         return;
      if (this.prireditev != null)
         this.prireditev.remove(oldPrireditev);
   }

   public void removeAllPrireditev() {
      if (prireditev != null)
         prireditev.clear();
   }

   public Collection<Izvajalec> getIzvajalec() {
      if (izvajalec == null)
         izvajalec = new HashSet<>();
      return izvajalec;
   }
   

   public Iterator<Izvajalec> getIteratorIzvajalec() {
      if (izvajalec == null)
         izvajalec = new java.util.HashSet<>();
      return izvajalec.iterator();
   }

   public void setIzvajalec(Collection<Izvajalec> newIzvajalec) {
      removeAllIzvajalec();
      for (Izvajalec value : newIzvajalec) addIzvajalec(value);
   }

   public void addIzvajalec(Izvajalec newIzvajalec) {
      if (newIzvajalec == null)
         return;
      if (this.izvajalec == null)
         this.izvajalec = new HashSet<>();
      if (!this.izvajalec.contains(newIzvajalec))
         this.izvajalec.add(newIzvajalec);
   }

   public void removeIzvajalec(Izvajalec oldIzvajalec) {
      if (oldIzvajalec == null)
         return;
      if (this.izvajalec != null)
         this.izvajalec.remove(oldIzvajalec);
   }

   public void removeAllIzvajalec() {
      if (izvajalec != null)
         izvajalec.clear();
   }

}