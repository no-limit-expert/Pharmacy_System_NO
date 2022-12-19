public class Pasient implements Comparable<Pasient> {
    private final SortertIndeksertListe<Resept> reseptListe;
    private final String navn;
    private final String fodselsnummer;
    private final int id;
    private static int antID = 0;

    public Pasient(String navn, String fodselsnummer) {
        this.reseptListe = new SortertIndeksertListe<>();
        this.navn = navn;
        this.fodselsnummer = fodselsnummer;
        antID++;
        this.id = antID;
    }

    public void leggTilResept(Resept resept) {
        reseptListe.leggTil(resept);
    }

    public String hentNavn() {
        return navn;
    }

    public String hentFodselsnummer() {
        return fodselsnummer;
    }

    public int hentID() {
        return id;
    }

    public SortertIndeksertListe<Resept> hentReseptListe() {
        return reseptListe;
    }

    @Override
    public int compareTo(Pasient annenPasient) {
        if (navn.compareTo(annenPasient.hentNavn()) < 0) {
            return -1;
        } else if (navn.compareTo(annenPasient.hentNavn()) > 0) {
            return 1;
        } else if (navn.compareTo(annenPasient.hentNavn()) == 0) {
            return 0;
        } else {
            System.out.println("Feil med sammenlikning av pasient ID");
            return 0;
        }
    }

    @Override
    public String toString(){
        return navn+" | fnr: "+fodselsnummer+" | pasient id: "+id;
    }
}
