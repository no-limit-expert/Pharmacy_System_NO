import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Legesystem {
    // SortertIndeksertListe<T> sorterer alt som blir lagt inn.
    // Fordelen med denne er at kan hente/fjerne vilkårlig indeks og alt er sortert.
    // Slik kan man senere implementere flere funksjoner.
    private final SortertIndeksertListe<Lege> legeListe = new SortertIndeksertListe<>();
    private final SortertIndeksertListe<Resept> reseptListe = new SortertIndeksertListe<>();
    private final IndeksertListe<Pasient> pasientListe = new IndeksertListe<>();
    private final IndeksertListe<Legemiddel> legemiddelListe = new IndeksertListe<>();
    private final String filPath;
    public int ugyldigPasientData = 0;
    public int ugyldigLegemiddelData = 0;
    public int ugyldigLegeData = 0;
    public int ugyldigReseptData = 0;

    public Legesystem(String filPath) {
        this.filPath = filPath;
        File fil = new File(filPath);
        Scanner reader;
        try {
            int felt = 0;
            reader = new Scanner(fil).useLocale(Locale.ENGLISH);
            while (reader.hasNextLine()) {
                // PASIENTER
                switch (felt) {
                    case 0:
                    case 1: {
                        String data = reader.nextLine();
                        if (data.contains("#")) {
                            felt++;
                        } else {
                            try {
                                String[] delt = data.split(",");
                                String[] renset = new String[delt.length];
                                for (int i = 0; i < delt.length; i++) {
                                    renset[i] = delt[i].strip();
                                }
                                pasientListe.leggTil(new Pasient(renset[0], renset[1]));
                            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                                ugyldigPasientData++;
                            }
                        }
                        break;
                    }
                    // LEGEMIDLER
                    case 2: {

                        String data = reader.nextLine();
                        if (data.contains("#")) {
                            felt++;
                        } else {
                            try {
                                String[] delt = data.split(",");
                                String[] renset = new String[delt.length];
                                for (int i = 0; i < delt.length; i++) {
                                    renset[i] = delt[i].strip();
                                }
                                switch (renset[1]) {
                                    case "narkotisk" -> legemiddelListe.leggTil(new Narkotisk(renset[0],
                                            (int) Double.parseDouble(renset[2]),
                                            Double.parseDouble(renset[3]), Integer.parseInt(renset[4])));
                                    case "vanedannende" -> legemiddelListe.leggTil(new Vanedannende(renset[0],
                                            (int) Double.parseDouble(renset[2]), Double.parseDouble(renset[3]),
                                            Integer.parseInt(renset[4])));
                                    case "vanlig" -> legemiddelListe.leggTil(new Vanlig(renset[0],
                                            (int) Double.parseDouble(renset[2]), Double.parseDouble(renset[3])));
                                }
                            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                                ugyldigLegemiddelData++;
                            }
                        }
                        break;
                    }

                    // LEGER / SPESIALISTER
                    case 3: {

                        String data = reader.nextLine();
                        if (data.contains("#")) {
                            felt++;
                        } else {
                            try {
                                String[] delt = data.split(",");
                                String[] renset = new String[delt.length];
                                for (int i = 0; i < delt.length; i++) {
                                    renset[i] = delt[i].strip();
                                }
                                if (Integer.parseInt(renset[1]) == 0) {
                                    legeListe.leggTil(new Lege(renset[0]));
                                } else {
                                    legeListe.leggTil(new Spesialist(renset[0], renset[1]));
                                }
                            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                                ugyldigLegeData++;
                            }
                        }
                        break;
                    }
                    // RESEPTER
                    case 4: {
                        while (reader.hasNextLine()) {
                            try {
                                String[] delt = reader.nextLine().split(",");
                                String[] renset = new String[delt.length];
                                for (int i = 0; i < delt.length; i++) {
                                    renset[i] = delt[i].strip();
                                }
                                Legemiddel legemiddel = null;
                                Lege lege = null;
                                Pasient pasient = null;

                                for (Legemiddel l : legemiddelListe) {
                                    if (l.hentId() == Integer.parseInt(renset[0])) {
                                        legemiddel = l;
                                        break;
                                    }
                                }
                                for (Lege l : legeListe) {
                                    if (l.hentNavn().equals(renset[1])) {
                                        lege = l;
                                        break;
                                    }
                                }
                                for (Pasient p : pasientListe) {
                                    if (p.hentID() == Integer.parseInt(renset[2])) {
                                        pasient = p;
                                        break;
                                    }
                                }
                                if (lege != null && legemiddel != null && pasient != null) {
                                    switch (renset[3]) {
                                        case "hvit" -> reseptListe.leggTil(lege.skrivHvitResept(legemiddel, pasient,
                                                Integer.parseInt(renset[4])));
                                        case "blaa" -> reseptListe.leggTil(lege.skrivBlaaResept(legemiddel, pasient,
                                                Integer.parseInt(renset[4])));
                                        case "militaer" -> reseptListe.leggTil(lege.skrivMilResept(legemiddel, pasient));
                                        case "p" -> reseptListe.leggTil(lege.skrivPResept(legemiddel, pasient,
                                                Integer.parseInt(renset[4])));
                                    }
                                }
                            } catch (IllegalArgumentException | IndexOutOfBoundsException | UlovligUtskrift e) {
                                ugyldigReseptData++;
                            }
                        }
                        break;
                    }
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public IndeksertListe<Pasient> hentPasientListe() {
        return pasientListe;
    }

    public IndeksertListe<Legemiddel> hentLegemiddelListe() {
        return legemiddelListe;
    }

    public IndeksertListe<Lege> hentLegeListe() {
        return legeListe;
    }

    public IndeksertListe<Resept> hentReseptListe() {
        return reseptListe;
    }


    // SORTERT ETTER PASIENT ID
    public void skrivUtPasienter() {
        int i = 1;
        for (Pasient pasient : pasientListe) {
            System.out.println(i + ": " + pasient);
            i++;
        }
    }

    // SORTERT ETTER NAVN TIL LEGE
    public void skrivUtLeger() {
        int i = 1;
        for (Lege lege : legeListe) {
            System.out.println(i + ": " + lege);
            i++;
        }
    }

    // SORTERT ETTER LEGEMIDDEL ID
    public void skrivUtLegemidler() {
        int i = 1;
        for (Legemiddel legemiddel : legemiddelListe) {
            System.out.println(i + ": " + legemiddel);
            i++;
        }
    }

    // SORTERT ETTER NAVN PÅ LEGEMIDDEL
    public void skrivUtResepter() {
        int i = 1;
        for (Resept resept : reseptListe) {
            System.out.println(i + ": " + resept);
            i++;
        }
    }


    public void startLegesystem() {
        Scanner input = new Scanner(System.in).useLocale(Locale.ENGLISH);
        outer:
        while (true) {
            int valg = 0;
            while (valg < 1 || valg > 6) {
                System.out.println("""
                        \nVelg alternativ (1-6)
                        1. Skriv ut data
                        2. Opprett eller legg til data
                        3. Bruk resept fra pasient liste
                        4. Skriv ut statistikk til fil
                        5. Lagre endringer
                        6. Avslutt program""");
                try {
                    valg = input.nextInt();
                    if (valg < 1 || valg > 6) {
                        throw new IllegalArgumentException();
                    }
                } catch (Exception e) {
                    System.out.println("\nVennligst oppgi en gyldig indeks mellom 1 og 6.\n");
                }
            }
            switch (valg) {
                case 1 -> {
                    while (true) {
                        valg = 0;
                        while (valg < 1 || valg > 6) {
                            System.out.println("""
                                    \nVelg alternativ (1-6)
                                    1. Skriv ut pasienter
                                    2. Skriv ut leger/spesialister
                                    3. Skriv ut legemidler
                                    4. Skriv ut resepter
                                    5. Skriv ut alt
                                    6. Tilbake til hovedmeny""");
                            try {
                                valg = input.nextInt();
                                if (valg < 1 || valg > 6) {
                                    throw new IllegalArgumentException();
                                }
                            } catch (Exception e) {
                                System.out.println("\nVennligst oppgi en gyldig indeks mellom 1 og 6.\n");
                            }
                        }
                        switch (valg) {
                            case 1 -> skrivUtPasienter();
                            case 2 -> skrivUtLeger();
                            case 3 -> skrivUtLegemidler();
                            case 4 -> skrivUtResepter();
                            case 5 -> {
                                System.out.println("\nPasienter\n-----");
                                skrivUtPasienter();
                                System.out.println("\nLeger\n-----");
                                skrivUtLeger();
                                System.out.println("\nLegemidler\n-----");
                                skrivUtLegemidler();
                                System.out.println("\nResepter\n-----");
                                skrivUtResepter();
                            }
                            case 6 -> {
                                continue outer;
                            }
                        }
                    }

                }
                case 2 -> {
                    while (true) {
                        valg = 0;
                        while (valg < 1 || valg > 6) {
                            System.out.println("""
                                    \nVelg alternativ (1-6)
                                    1. Legg til ny pasient
                                    2. Legg til ny lege
                                    3. Legg til ny spesialist
                                    4. Legg til nytt legemiddel
                                    5. Legg til ny resept
                                    6. Tilbake til hovedmeny""");
                            try {
                                valg = input.nextInt();
                                if (valg < 1 || valg > 6) {
                                    throw new UlovligValg(valg);
                                }
                            } catch (UlovligValg e) {
                                System.out.println("\nVennligst oppgi en gyldig indeks mellom 1 og 6.\n");
                            }
                        }
                        switch (valg) {
                            // LEGG TIL NY PASIENT
                            case 1 -> {
                                System.out.println("Oppgi navn:");
                                input.nextLine();
                                String navn = input.nextLine();
                                System.out.println("Oppgi fødselsnummer:");
                                String fnr = input.next();
                                pasientListe.leggTil(new Pasient(navn, fnr));
                                System.out.println("Pasienten med navn " + navn + " og fødselsnummer " + fnr +
                                        " har blitt lagt til!");
                            }
                            // LEGG TIL NY LEGE
                            case 2 -> {
                                System.out.println("Oppgi navn:");
                                input.nextLine();
                                String navn = input.nextLine();
                                legeListe.leggTil(new Lege(navn));
                                System.out.println("Lege med navn " + navn + " har blitt lagt til!");
                            }
                            // LEGG TIL NY SPESIALIST
                            case 3 -> {
                                System.out.println("Oppgi navn:");
                                input.nextLine();
                                String navn = input.nextLine();
                                System.out.println("Oppgi kontroll ID:");
                                String kontrollId = input.next();
                                legeListe.leggTil(new Spesialist(navn, kontrollId));
                                System.out.println("Spesialist med navn " + navn + " og kontroll ID " + kontrollId +
                                        " har blitt lagt til!");
                            }
                            // LEGG TIL NYTT LEGEMIDDEL
                            case 4 -> {
                                System.out.println("Oppgi navn på legemiddel:");
                                input.nextLine();
                                String navn = input.nextLine();
                                System.out.println("Oppgi pris i kr:");
                                double pris = input.nextDouble();
                                System.out.println("Oppgi mengde virkestoff mg:");
                                double virkestoff = input.nextDouble();
                                int type = 0;
                                while (type < 1 || type > 3) {
                                    System.out.println("""
                                            Er stoffet er;
                                            1. vanlig
                                            2. narkotisk
                                            3. vanedannene
                                            """);
                                    try {
                                        type = input.nextInt();
                                        if (type < 1 || type > 3) {
                                            throw new UlovligValg(type);
                                        }
                                    } catch (UlovligValg e) {
                                        System.out.println("\nVennligst oppgi en gyldig indeks mellom 1 og 3.\n");
                                    }
                                }
                                switch (type) {
                                    case 1:
                                        legemiddelListe.leggTil(new Vanlig(navn, (int) pris, virkestoff));
                                    case 2:
                                    case 3:
                                        System.out.println("Oppgi mengde styrke:");
                                        int styrke = input.nextInt();
                                        if (type == 2) legemiddelListe.leggTil(new Narkotisk(navn, (int) pris,
                                                virkestoff, styrke));
                                        else if (type == 3) legemiddelListe.leggTil(new Narkotisk(navn, (int) pris,
                                                virkestoff, styrke));
                                }
                                System.out.println(navn + " har blitt lagt til!");
                            }
                            // LEGG TIL NY RESEPT
                            case 5 -> {
                                Lege lege = null;
                                Pasient pasient = null;
                                Legemiddel legemiddel = null;

                                // FINN LEGE
                                int legeNr = 0;
                                while (legeNr < 1 || legeNr > legeListe.lengde) {
                                    System.out.println("Hvem er utskrivende lege?");
                                    skrivUtLeger();
                                    try {
                                        legeNr = input.nextInt();
                                        if (legeNr < 1 || legeNr > legeListe.lengde) {
                                            throw new UlovligValg(legeNr);
                                        }
                                        lege = legeListe.hent(legeNr - 1);
                                    } catch (UlovligValg e) {
                                        System.out.println("\nVennligst oppgi en gyldig indeks mellom 1 og " +
                                                legeListe.lengde + ".\n");
                                    }
                                }
                                // FINN PASIENT
                                int pasientNr = 0;
                                while (pasientNr < 1 || pasientNr > pasientListe.lengde) {
                                    System.out.println("Velg pasient:");
                                    skrivUtPasienter();
                                    try {
                                        pasientNr = input.nextInt();
                                        if (pasientNr < 1 || pasientNr > pasientListe.lengde) {
                                            throw new UlovligValg(pasientNr);
                                        }
                                        pasient = pasientListe.hent(pasientNr - 1);
                                    } catch (UlovligValg e) {
                                        System.out.println("\nVennligst oppgi en gyldig indeks mellom 1 og " +
                                                pasientListe.lengde + ".\n");
                                    }
                                }
                                // FINN LEGEMIDDEL
                                int legeMiddelID = -1;
                                while (legeMiddelID < 0 || legeMiddelID > legemiddelListe.lengde) {
                                    System.out.println("Oppgi legemiddel ID (NB! husk å oppi ID og ikke indeks!):\n");
                                    skrivUtLegemidler();
                                    try {
                                        legeMiddelID = input.nextInt();
                                        if (legeMiddelID < 0 || legeMiddelID > legemiddelListe.lengde) {
                                            throw new UlovligValg(legeMiddelID);
                                        }
                                        for (Legemiddel l : legemiddelListe) {
                                            if (legeMiddelID == l.hentId()) {
                                                legemiddel = l;
                                                break;
                                            }
                                        }
                                    } catch (UlovligValg e) {
                                        System.out.println("\nVennligst oppgi et gyldig legemiddel ID.\n");
                                    }
                                }
                                // FINN TYPE RESEPT
                                int type = 0;
                                while (type < 1 || type > 4) {
                                    System.out.println("""
                                            Er resepten er en;
                                            1. blå resept
                                            2. hvit resept
                                            3. militær resept
                                            4. p-resept
                                            """);
                                    try {
                                        type = input.nextInt();
                                        if (type < 1 || type > 4) {
                                            throw new UlovligValg(type);
                                        }
                                    } catch (UlovligValg e) {
                                        System.out.println("\nVennligst oppgi en gyldig indeks mellom 1 og 4.\n");
                                    }
                                }
                                // FINN REIT
                                int reit = -1;
                                if (type != 3) {
                                    while (reit < 0) {
                                        System.out.println("Oppgi reit:");
                                        try {
                                            reit = input.nextInt();
                                            if (reit < 0) {
                                                throw new UlovligValg(reit);
                                            }
                                        } catch (UlovligValg e) {
                                            System.out.println("\nVennligst oppgi et gyldig tall.");
                                        }
                                    }
                                }
                                // SKRIV RESEPT
                                try {
                                    assert (legemiddel != null);
                                    assert (lege != null);
                                    assert (pasient != null);

                                    switch (type) {
                                        case 1 -> reseptListe.leggTil(lege.skrivBlaaResept(legemiddel, pasient, reit));
                                        case 2 -> reseptListe.leggTil(lege.skrivHvitResept(legemiddel, pasient, reit));
                                        case 3 -> reseptListe.leggTil(lege.skrivMilResept(legemiddel, pasient));
                                        case 4 -> reseptListe.leggTil(lege.skrivPResept(legemiddel, pasient, reit));
                                    }
                                } catch (UlovligUtskrift e) {
                                    System.out.println(lege.hentNavn() + " har ikke lov til å skrive ut " +
                                            legemiddel.navn);
                                } catch (Exception e) {
                                    System.out.println("Ujkent feil! Går tilbake til hovedmeny.");
                                    continue outer;
                                }
                                System.out.println("Resept lagt til!");
                            }
                            case 6 -> {
                                continue outer;
                            }
                        }
                    }

                }
                case 3 -> {
                    Pasient pasient = null;
                    Resept resept = null;
                    // FINN PASIENT
                    int pasientNr = 0;
                    while (pasientNr < 1 || pasientNr > pasientListe.lengde) {
                        System.out.println("Hvilken pasient vil du see resepter for?");
                        skrivUtPasienter();
                        try {
                            pasientNr = input.nextInt();
                            if (pasientNr < 1 || pasientNr > pasientListe.lengde) {
                                throw new UlovligValg(pasientNr);
                            }
                            pasient = pasientListe.hent(pasientNr - 1);
                        } catch (UlovligValg e) {
                            System.out.println("\nVennligst oppgi en gyldig indeks mellom 1 og " +
                                    pasientListe.lengde + ".\n");
                        }
                    }
                    try {
                        assert pasient != null;
                    } catch (AssertionError e) {
                        System.out.println("Fant ikke pasienten.");
                        continue;
                    }
                    if (pasient.hentReseptListe().lengde == 0) {
                        System.out.println("Denne personen har ingen resepter!");
                        continue;
                    }
                    valg = 0;
                    while (valg < 1 || valg > pasient.hentReseptListe().lengde) {
                        System.out.println("Velg resept:");
                        for (int i = 0; i < pasient.hentReseptListe().lengde; i++) {
                            System.out.println(i + 1 + ": " + pasient.hentReseptListe().hent(i));
                        }
                        try {
                            valg = input.nextInt();
                            if (valg < 1 || valg > pasient.hentReseptListe().lengde) {
                                throw new UlovligValg(valg);
                            }
                            resept = pasient.hentReseptListe().hent(valg - 1);
                        } catch (UlovligValg e) {
                            System.out.println("\nVennligst oppgi en gyldig indeks mellom 1 og " +
                                    pasient.hentReseptListe().lengde + ".\n");
                        }

                    }
                    assert (resept != null);
                    resept.bruk();

                }
                case 4 -> {
                    IndeksertListe<Resept> vanedannedeResepter = new IndeksertListe<>();
                    IndeksertListe<Resept> narkotiskeResepter = new IndeksertListe<>();
                    Prioritetskoe<Lege> legerMedNarkotiskRes = new Prioritetskoe<>();
                    Prioritetskoe<Pasient> pasienterMedNarkotiskRes = new Prioritetskoe<>();
                    for (int i = 0; i < reseptListe.lengde; i++) {
                        if (reseptListe.hent(i).legemiddel instanceof Narkotisk) {
                            narkotiskeResepter.leggTil(reseptListe.hent(i));
                        }
                        if (reseptListe.hent(i).legemiddel instanceof Vanedannende) {
                            vanedannedeResepter.leggTil(reseptListe.hent(i));
                        }
                    }
                    for (int i = 0; i < legeListe.lengde; i++) {
                        Lege lege = legeListe.hent(i);
                        for (int j = 0; j < lege.hentUtskrevneResepter().lengde; j++) {
                            if (lege.hentUtskrevneResepter().hent(j).legemiddel instanceof Narkotisk) {
                                legerMedNarkotiskRes.leggTil(lege);
                                break;
                            }
                        }
                    }
                    for (int i = 0; i < pasientListe.lengde; i++) {
                        Pasient pasient = pasientListe.hent(i);
                        for (int j = 0; j < pasient.hentReseptListe().lengde; j++) {
                            if (pasient.hentReseptListe().hent(j).legemiddel instanceof Narkotisk) {
                                pasienterMedNarkotiskRes.leggTil(pasient);
                                break;
                            }
                        }
                    }

                    System.out.println("Antall Narkotiske resepter: " + narkotiskeResepter.lengde);
                    System.out.println("Antall vanedannende resepter: " + vanedannedeResepter.lengde);
                    System.out.println("\nLeger med minst 1 utskrevet narkotisk resept:");
                    while (legerMedNarkotiskRes.lengde != 0) {
                        System.out.println(legerMedNarkotiskRes.fjern());
                    }
                    System.out.println("\nPasienter med utskrevet narkotisk resept: ");
                    while (pasienterMedNarkotiskRes.lengde != 0) {
                        Pasient pasient = pasienterMedNarkotiskRes.fjern();
                        int antall = 0;
                        for (Resept r : pasient.hentReseptListe()) {
                            if (r.legemiddel instanceof Narkotisk) {
                                antall++;
                            }
                        }
                        System.out.println(pasient + "\nAntall narkotiske resepter: " + antall);
                    }

                    System.out.println("\nUgyldig data ved scan" +
                            "\nPasienter : " + ugyldigPasientData +
                            "\nLeger/spesialister: " + ugyldigLegeData +
                            "\nLegemidler: " + ugyldigLegemiddelData +
                            "\nResepter: " + ugyldigReseptData);
                }

                case 5 -> {
                    lagreEndringer();
                    System.out.println("\nEndringer har blitt lagret!\n");
                }
                case 6 -> {
                    break outer;
                }
            }
        }
        input.close();
    }

    public void lagreEndringer() {
        try {
            PrintWriter skriver = new PrintWriter(filPath);
            String leggesTil;

            skriver.print("# Pasienter (navn, fnr)");
            for (Pasient pasient : pasientListe) {
                leggesTil = "\n" + pasient.hentNavn() + "," + pasient.hentFodselsnummer();
                skriver.write(leggesTil);
            }

            skriver.append("\n# Legemidler (navn,type,pris,virkestoff,[styrke])");
            for (Legemiddel legemiddel : legemiddelListe) {
                leggesTil = "\n" + legemiddel.navn + ",";
                int styrke = -1;
                if (legemiddel instanceof Vanedannende) {
                    styrke = ((Vanedannende) legemiddel).hentVanedannendeStyrke();
                    leggesTil += "vanedannende";
                } else if (legemiddel instanceof Narkotisk) {
                    styrke = ((Narkotisk) legemiddel).hentNarkotiskStyrke();
                    leggesTil += "narkotisk";
                } else {
                    leggesTil += "vanlig";
                }
                leggesTil += "," + legemiddel.pris + "," + legemiddel.virkestoff;

                if (styrke != -1) {
                    leggesTil += "," + styrke;
                }
                skriver.write(leggesTil);
            }
            skriver.append("\n# Leger (navn,kontrollid / 0 hvis vanlig lege)");
            for (Lege lege : legeListe) {
                if (lege instanceof Spesialist) {
                    leggesTil = "\n" + lege.navn + "," + ((Spesialist) lege).hentKontrollID();
                } else {
                    leggesTil = "\n" + lege.navn + "," + 0;
                }
                skriver.write(leggesTil);
            }
            skriver.append("\n# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
            for (Resept resept : reseptListe) {
                leggesTil = "\n" + resept.legemiddel.id + "," + resept.lege.navn + "," + resept.hentPasientId();
                if (resept instanceof HvitResept) {
                    if (resept instanceof MillResept) {
                        leggesTil += ",militaer";
                    } else if (resept instanceof PResept) {
                        leggesTil += ",p," + resept.reit;
                    } else {
                        leggesTil += ",hvit," + resept.reit;
                    }
                } else {
                    leggesTil += ",blaa," + resept.reit;
                }
                skriver.write(leggesTil);
            }
            skriver.flush();
            skriver.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
