package com.example.vpisdogodkov;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/***********************************************************************
 * Module:  KVpisDogodka.java
 * Author:  Mark and Jan
 * Purpose: Defines the Class KVpisDogodka
 ***********************************************************************/

public class KVpisDogodka {

   public List<Prireditev> prireditev;
   public List<Izvajalec> izvajalec;
   public List<MestoPrireditve> mestaPrireditve;
   public SvSistemEposte svSistemEposte;

   public KVpisDogodka() {
      mestaPrireditve = MestoPrireditve.vrniMesta();
   }

   public void zakljuciZVpisomDogodka() {
      // TODO: implement
   }

   public void dodajNovoPrireditev(int sifra, String naslov, double cenaVstopnice, LocalDateTime zacetek, LocalDateTime konec) {
      Prireditev prireditev = new Prireditev(sifra, naslov, cenaVstopnice, zacetek, konec);
      Prireditev.dodajPrireditev(prireditev);
   }

   public HashMap<String, String> vrniPodrobnostiMestaPrireditve(String naziv) {
      HashMap<String, String> podrobnosti = new HashMap<>();

      for (MestoPrireditve mesto : mestaPrireditve) {
         if (mesto.vrniNaziv().equals(naziv)) {
            podrobnosti.put("naziv", mesto.vrniNaziv());
            podrobnosti.put("naslov", mesto.vrniNaslov());
            //podrobnosti.put("steviloSedezev", String.valueOf(mesto.vrniSteviloSedezev()));
            break;
         }
      }

      return podrobnosti;
   }

   public List<MestoPrireditve> vrniSeznamMest() {
      return mestaPrireditve;
   }

   public List<Termin> vrniProsteTermineZaMesto(String naziv) {
      return MestoPrireditve.vrniProsteTermine(naziv);
   }

   public void dodajNovegaIzvajalca(String naziv, String opis) {
      Izvajalec izvajalec = new Izvajalec(naziv, opis);
      Izvajalec.dodajIzvajalca(izvajalec);
   }

}