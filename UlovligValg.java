
public class UlovligValg extends Exception {
    UlovligValg (int valg){
        super(valg+" er ingen gyldig alternativ!");
    }
}
