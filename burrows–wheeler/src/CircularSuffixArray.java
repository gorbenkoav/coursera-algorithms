import java.util.TreeMap;

public class CircularSuffixArray {

    private Integer[] indexes;

    /**
     * Define circular suffix array of s
     *
     * @param s source string
     */
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        int length = s.length();
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put(s, 0);

        StringBuilder builder = new StringBuilder(s);
        for (int i = 1; i < length; i++) {
            builder.append(builder.charAt(0)).deleteCharAt(0);
            map.put(builder.toString(), i);
        }

        indexes = map.values().toArray(new Integer[0]);
    }

    /**
     * Length of source string
     *
     * @return length
     */
    public int length() {
        return indexes.length;
    }

    /**
     * Returns index of ith sorted suffix
     *
     * @param i index
     * @return index of ith sorted suffix
     */
    public int index(int i) {
        if (i < 0 || i >= length()) {
            throw new IllegalArgumentException();
        }
        return indexes[i];
    }

    public static void main(String[] args) {

    }
}