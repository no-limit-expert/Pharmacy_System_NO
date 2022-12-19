public class Stabel<T> extends Lenkeliste<T> {
    @Override
    public void leggTil(T x) {
        Node ny = new Node(x);
        ny.neste = start;
        start = ny;
        lengde++;
    }
}
