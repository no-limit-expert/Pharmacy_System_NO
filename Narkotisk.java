class Narkotisk extends Legemiddel {
    private final int styrke;

    public Narkotisk(String navn, int pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    @Override
    public String toString() {
        return navn + " | ID: " + id + " | Pris: " + pris + "kr | Virkestoff: " + virkestoff +
                "mg | Narkotisk styrke:" + styrke;
    }

    public int hentNarkotiskStyrke() {
        return styrke;
    }
}