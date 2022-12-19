public class Lege implements Comparable<Lege> {
    private final IndeksertListe<Resept> utskrevneResepter;
    protected final String navn;

    public Lege(String navn) {
        this.navn = navn;
        utskrevneResepter = new IndeksertListe<>();
    }


    public String hentNavn() {
        return navn;
    }

    public IndeksertListe<Resept> hentUtskrevneResepter() {
        return utskrevneResepter;
    }

    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient,int reit) throws UlovligUtskrift{
        if(legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        else return new HvitResept(legemiddel, this, pasient, reit);
    }

    public MillResept skrivMilResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift{
        if(legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        else return new MillResept(legemiddel, this, pasient);
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient,int reit) throws UlovligUtskrift{
        if(legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        else return new PResept(legemiddel, this, pasient, reit);
    }

    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient,int reit) throws UlovligUtskrift{
        if(legemiddel instanceof Narkotisk) {
            if(this instanceof Spesialist) {
                return new BlaaResept(legemiddel, this, pasient, reit);
            }
            else {
                throw new UlovligUtskrift(this, legemiddel);
            }
        }
        else return new BlaaResept(legemiddel, this, pasient, reit);
    }


    public void leggTilResept(Resept resept){
        utskrevneResepter.leggTil(resept);
    }

    @Override
    public String toString() {
        return "Lege: " + hentNavn();
    }

    @Override
    public int compareTo(Lege annenLege) {
        if (navn.compareTo(annenLege.hentNavn()) < 0) {
            return -1;
        } else if (navn.compareTo(annenLege.hentNavn()) > 0) {
            return 1;
        } else if (navn.compareTo(annenLege.hentNavn()) == 0) {
            return 0;
        } else {
            throw new UnknownError();
        }
    }
}
