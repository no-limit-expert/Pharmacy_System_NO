public class SortertIndeksertListe<T extends Comparable<T>> extends IndeksertListe<T> {
    @Override
    public void leggTil(T x) {
        Node nyNode = new Node(x);
        if (lengde == 0) {
            start = nyNode;
        } else {
            for (int i = 0; i < lengde; i++) {
                if (x.compareTo(pekPaaPosisjon(i).data) < 0) {
                    nyNode.neste = pekPaaPosisjon(i);
                    if (i == 0) {
                        start = nyNode;
                    } else {
                        pekPaaPosisjon(i - 1).neste = nyNode;
                    }
                    break;
                } else if (i == lengde - 1) {
                    pekPaaPosisjon(i).neste = nyNode;
                    break;
                }
            }
        }
        lengde++;
    }
}
