// vorgegebene Klasse
public class Tuple implements Comparable<Tuple> {
    protected int[] v;
    protected static int sortIndex;

    public Tuple(int[] t) {
        v = new int[t.length];
        System.arraycopy(t, 0, v, 0, t.length);
    }
    public int get(int i) {return v[i];}

    public int compareTo(Tuple p) {
        return Integer.compare(v[sortIndex], p.v[sortIndex]);
    }

    public int getSize() {return v.length;}

    public static void setSortIndex(int i) {
        sortIndex = i;
    }
}
