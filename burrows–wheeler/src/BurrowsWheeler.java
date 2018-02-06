import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

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
        int first = BinaryStdIn.readInt();
        String input = BinaryStdIn.readString();
        char[] sorted = input.toCharArray();
        Arrays.sort(sorted);
        int[] next = new int[input.length()];

        int currentSymbol = sorted[0];
        Queue<Integer> currentIndexes = new Queue<>();
        currentIndexes.enqueue(0);

        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] != currentSymbol) {
                fillNext(input, next, currentSymbol, currentIndexes, i);
                currentSymbol = sorted[i];
            }
            currentIndexes.enqueue(i);
        }
        fillNext(input, next, currentSymbol, currentIndexes, sorted.length);

        StringBuilder source = new StringBuilder();
        while (source.length() != sorted.length) {
            source.append(sorted[first]);
            first = next[first];
        }

        BinaryStdOut.write(source.toString());
        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    private static void fillNext(String input, int[] next, int currentSymbol, Queue<Integer> currentIndexes, int currentIndex) {
        int lastOccur = 0;
        while (!currentIndexes.isEmpty()) {
            int index = input.indexOf(currentSymbol, lastOccur);
            lastOccur = index + 1;
            next[currentIndex - currentIndexes.size()] = index;
            currentIndexes.dequeue();
        }
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