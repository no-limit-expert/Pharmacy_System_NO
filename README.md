# Pharmacy System

Dette er et fullt legesystem der jeg har importert minst mulig bibloteker i java og selv implementert alt som jeg har behov for. Dette er et legesystem der man kan ha oversikt over pasienter, legemidler, resepter og leger. Dette systemet leser og skriver på en .txt fil som har lagret data. Jeg kunne også ha brukt .csv eller .xlsx fil som hadde gjort ting lettere, men her bruker jeg .txt fil for å lære meg å utnytte .txt filer bedre. 

Filene Legesystem.txt (liten) og LegesystemS.txt (stor) er testfiler som kan brukes.

Pastient.java inneholder klasse for pasienter.

Følgende filer er klasser for leger:
- Lege.java
- Spesialist.java   // Subklasse til Lege. Implementer interface Godkjenningsfritak. Kun spesialister kan gi resept for narkotiske legemidler.

Følgende filer er klasser for legemidler:
- Legemiddel.java   // Abstrakt superklasse til alle andre legemidler.
- Vanlig.java
- Narkotisk.java
- Vanedannende.java

Følgende filer er klasser for resepter:
- Resept.java       // Abstrakt superklasse til PResept, Blaaresept og Hvitresept.
- PResept.java
- HvitResept.java
- BlaaResept.java
- MillResept.java   // Subklasse til HvitResept.

Følgende filer er implementasjoner av listene:
- Liste.java                  // Dette er en interface som inneholder alle nødvendige variabler og funksjoner som en liste skal kunne ha.
- LenkeListe.java             // Her implementeres en lenket liste. Dette er superklassen til alle liste klasser. Denne klassen er abstrakt.
- IndeksertListe.java         // Denne klassen er nesten helt lik java.util.ArrayList.
- Koe.java                    // Denne klassen er helt lik en vanlig kø. Fjern elementer som ligger fremst, legg til elementer bakerst i køen.
- Prioritetskoe.java          // Vanlig prioritet kø der elementer eller objekter blir sammenliknet.
- Stabel.java                 // Helt lik en stack.
- SortertIndeksertListe.java  // En indeksert liste som en ArrayList men du kan plukke ut verdier fra hvilken som helst indeks og listen sorteres når elementer legges til.


