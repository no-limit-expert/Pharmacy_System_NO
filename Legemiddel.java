public abstract class Legemiddel implements Comparable<Legemiddel> {
    protected String navn;
    protected int id;
    protected int pris;
    protected static int antId;
    protected double virkestoff;

    public Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        antId++;
        id = antId;
    }

    public String hentNavn() {
        return navn;
    }

    public int hentId() {
        return id;
    }

    public int hentPris() {
        return pris;
    }

    public double hentVirkestoff() {
        return virkestoff;
    }

    public void settNyPris(int nyPris) {
        pris = nyPris;
    }

    @Override
    public String toString() {
        return navn + " | ID: " + id + " | Pris: " + pris + "kr | Virkestoff: " + virkestoff + "mg | Vanlig";
    }

    @Override
    public int compareTo(Legemiddel annenLegemiddel) {
        if (navn.compareTo(annenLegemiddel.hentNavn()) < 0) {
            return -1;
        } else if (navn.compareTo(annenLegemiddel.hentNavn()) > 0) {
            return 1;
        } else if (navn.compareTo(annenLegemiddel.hentNavn()) == 0) {
            return 0;
        } else {
            System.out.println("Feil med sammenlikning av legemiddel ID");
            return 0;
        }
    }
}






