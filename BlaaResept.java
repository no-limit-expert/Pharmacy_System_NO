import static java.lang.Math.round;

public class BlaaResept extends Resept {
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String farge() {
        return "blaa";
    }

    @Override
    public int prisAaBetale() {
        return (int) round((double)(legemiddel.hentPris()*25)/100);
    }
    @Override
    public String toString() {
        return "Blå resept" +
                " | Legemiddel: " + legemiddel.hentNavn() +
                " | LegemiddelID: " + hentId() +
                " | Lege: " + lege.hentNavn() +
                " | PasientID: " + pasient.hentID() +
                " | Pris: " + prisAaBetale() + "kr" +
                " | Reit: " + reit;
    }
}
