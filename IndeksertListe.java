class IndeksertListe<T> extends Lenkeliste<T> {

    public void leggTil(int pos, T x) {
        if (pos < 0 || pos > lengde) {
            throw new UgyldigListeindeks(pos);
        }
        if (lengde > 0) {
            Node nyNode = new Node(x);
            if (pos == 0) {
                nyNode.neste = pekPaaPosisjon(pos);
                start = nyNode;
            } else {
                if (lengde != pos) {
                    nyNode.neste = pekPaaPosisjon(pos);
                }
                pekPaaPosisjon(pos - 1).neste = nyNode;
            }
        } else {
            start = new Node(x);
        }
        lengde++;
    }

    public void sett(int pos, T x) {
        if (pos < 0 || pos >= lengde - 1) {
            throw new UgyldigListeindeks(pos);
        }
        Node nyNode = new Node(x);
        nyNode.neste = pekPaaPosisjon(pos + 1);
        pekPaaPosisjon(pos - 1).neste = nyNode;
    }

    public T hent(int pos) {
        if (pos < 0 || pos > lengde - 1) {
            throw new UgyldigListeindeks(pos);
        }
        return pekPaaPosisjon(pos).data;
    }

    public T fjern(int pos) {
        if (pos < 0 || pos > lengde - 1) {
            throw new UgyldigListeindeks(pos);
        }
        T fjernes = pekPaaPosisjon(pos).data;
        if (lengde == 1) {
            start = null;
        } else {
            if (pos == lengde - 1) {
                pekPaaPosisjon(pos - 1).neste = null;
            } else {
                pekPaaPosisjon(pos - 1).neste = pekPaaPosisjon(pos + 1);
            }
        }
        lengde--;
        return fjernes;
    }


}