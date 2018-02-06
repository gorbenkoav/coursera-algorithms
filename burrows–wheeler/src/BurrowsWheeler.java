import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    /**
     * Apply Burrows-Wheeler transform, reading from standard input and writing to standard output
     */
    public static void transform() {
        String input = BinaryStdIn.readString();
        CircularSuffixArray suffixes = new CircularSuffixArray(input);
        int first = 0;


        StringBuilder lastColumn = new StringBuilder();
        for (int i = 0; i < suffixes.length(); i++) {
            int index = suffixes.index(i) - 1;
            if (index < 0) {
                index = suffixes.length() - 1;
            }
            lastColumn.append(input.charAt(index));
            if (suffixes.index(i) == 0) {
                first = i;
            }
        }
        BinaryStdOut.write(first);
        BinaryStdOut.write(lastColumn.toString());

        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    /**
     * Apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
     */
    public static void inverseTransform() {

    }

    /**
     * if args[0] is '-', apply Burrows-Wheeler transform
     * if args[0] is '+', apply Burrows-Wheeler inverse transform
     */
    public static void main(String[] args) {
        if (args[0].charAt(0) == '-') {
            transform();
        } else if (args[0].charAt(0) == '+') {
            inverseTransform();
        }
    }
}