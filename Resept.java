public abstract class Resept implements Comparable<Resept> {
    protected Pasient pasient;
    protected Legemiddel legemiddel;
    protected Lege lege;
    protected int reit;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.lege = utskrivendeLege;
        this.reit = reit;
        this.pasient = pasient;
        pasient.leggTilResept(this);
        utskrivendeLege.leggTilResept(this);
    }

    public int hentId() {
        return legemiddel.hentId();
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return lege;
    }

    public int hentPasientId() {
        return pasient.hentID();
    }

    public int hentReit() {
        return reit;
    }

    public boolean bruk() {

        if (reit > 0) {
            reit--;
            System.out.println("Brukt Resept på " + legemiddel.hentNavn() + "! Gjenværende reit: "+reit);
            return true;
        } else if (reit == 0) {
            System.out.println("Kunne ikke bruke resept på " + legemiddel.hentNavn()+" (ingen gjenværende reit).");
            return false;
        } else {
            System.out.println("Brukt resept på "+legemiddel.hentNavn());
            return true;
        }
    }

    @Override
    public int compareTo(Resept resept) {
        if (legemiddel.navn.compareTo(resept.legemiddel.navn) < 0) {
            return -1;
        } else if (legemiddel.navn.compareTo(resept.legemiddel.navn) > 0) {
            return 1;
        } else if (legemiddel.navn.compareTo(resept.legemiddel.navn) == 0) {
            return 0;
        } else {
            System.out.println("Feil med sammenlikning av pasient ID");
            return 0;
        }
    }


    abstract public String farge();

    abstract public int prisAaBetale();

}
