public class CircularSuffixArray {

    private final int[] indexes;

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
        indexes = new int[length];
        for (int i = 0; i < length; i++) {
            indexes[i] = i;
        }

        sort(s);
    }

    private void sort(String s) {
        int alphabetLength = 256;
        int length = s.length();
        int[] aux = new int[length];

        char[] a = s.toCharArray();

        for (int k = length - 1; k >= 0; k--) {

            int[] count = new int[alphabetLength + 1];
            for (int i = 0; i < length; i++) {
                count[a[i] + 1]++;
            }

            for (int r = 0; r < alphabetLength; r++) {
                count[r + 1] += count[r];
            }

            for (int i = 0; i < length; i++) {
                aux[count[a[(k + indexes[i]) % length]]++] = indexes[i];
            }

            for (int i = 0; i < length; i++) {
                indexes[i] = aux[i];
            }
        }

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