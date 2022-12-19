public class HvitResept extends Resept {
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String farge() {
        return "hvit";
    }
    @Override
    public int prisAaBetale() {
        return hentLegemiddel().hentPris();
    }

    @Override
    public String toString() {
        return "Hvit resept" +
                " | Legemiddel: " + legemiddel.hentNavn() +
                " | LegemiddelID: " + hentId() +
                " | Lege: " + lege.hentNavn() +
                " | PasientID: " + pasient.hentID() +
                " | Pris: " + prisAaBetale() + "kr" +
                " | Reit: " + reit;
    }
}

