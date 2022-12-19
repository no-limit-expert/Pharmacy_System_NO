public class PResept extends HvitResept {
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }
    @Override
    public int prisAaBetale() {
        if (legemiddel.hentPris() < 108) {return 0;}
        else {return legemiddel.hentPris()-108;}
    }
    @Override
    public String toString() {
        return "P resept" +
                " | Legemiddel: " + legemiddel.hentNavn() +
                " | LegemiddelID: " + hentId() +
                " | Lege: " + lege.hentNavn() +
                " | PasientID: " + pasient.hentID() +
                " | Pris: " + prisAaBetale() + "kr" +
                " | Reit: " + reit;
    }
}
