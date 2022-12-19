import java.util.Iterator;

abstract class Lenkeliste<T> implements Liste<T> {
    Node start;
    int lengde = 0;

    class Node {
        Node neste = null;
        T data;

        public Node(T x) {
            data = x;
        }

    }

    public Node pekPaaPosisjon(int pos) {
        if (pos < 0 || pos >= lengde) {
            throw new UgyldigListeindeks(pos);
        }
        Node peker = start;
        for (int i = 0; i < pos; i++) {
            peker = peker.neste;
        }
        return peker;
    }

    private class LenkeListeIterator implements Iterator<T> {

        private int gjeldendeIndeks = 0;

        public T next() {
            return pekPaaPosisjon(gjeldendeIndeks++).data;
        }

        public boolean hasNext() {
            return gjeldendeIndeks < lengde;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new LenkeListeIterator();
    }

    @Override
    public int stoerrelse() {
        Node peker = start;
        int teller = 0;
        while (peker != null) {
            teller++;
            peker = peker.neste;
        }
        return teller;
    }

    @Override
    public void leggTil(T x) {
        if (lengde == 0) {
            start = new Node(x);
        } else {
            pekPaaPosisjon(lengde - 1).neste = new Node(x);

        }
        lengde++;
    }

    @Override
    public T fjern() {
        if (lengde == 0) {
            throw new UgyldigListeindeks(-1);
        }
        T fjernes = pekPaaPosisjon(0).data;
        if (lengde == 1) start = null;
        else start = pekPaaPosisjon(1);
        lengde--;
        return fjernes;
    }

    @Override
    public T hent() {
        return start.data;
    }

    @Override
    public String toString() {
        StringBuilder liste = new StringBuilder();
        for (int x = 0; x < lengde; x++) {
            String nummer = "Indeks " + x + "\n";
            liste.append(nummer).append(pekPaaPosisjon(x).data.toString()).append("\n");
        }
        return liste.toString();
    }

}