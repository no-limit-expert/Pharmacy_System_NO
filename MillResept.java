public class MillResept extends HvitResept {

    public MillResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {
        super(legemiddel, utskrivendeLege, pasient, -1);
    }

    @Override
    public int prisAaBetale() {
        return 0;
    }

    @Override
    public String toString() {
        return "Millitær resept" +
                " | Legemiddel: " + legemiddel.hentNavn() +
                " | LegemiddelID: " + hentId() +
                " | Lege: " + lege.hentNavn() +
                " | PasientID: " + pasient.hentID() +
                " | Pris: " + prisAaBetale() + "kr";
    }
}

