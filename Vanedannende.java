public class Vanedannende extends Legemiddel {
    private final int styrke;

    public Vanedannende(String navn, int pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    @Override
    public String toString() {
        return navn + " | ID: " + id + " | Pris: " + pris + "kr | Virkestoff: " + virkestoff +
                "mg | Vanedannende styrke:" + styrke;
    }

    public int hentVanedannendeStyrke() {
        return styrke;
    }
}

