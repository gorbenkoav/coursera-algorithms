import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.LinkedList;

public class MoveToFront {

    /**
     * Apply move-to-front encoding, reading from standard input and writing to standard output
     */
    public static void encode() {
        char c;
        LinkedList<Character> symbols = generateSymbolTable();
        while (!BinaryStdIn.isEmpty()) {
            c = BinaryStdIn.readChar(8);
            int index = symbols.indexOf(c);
            BinaryStdOut.write((char)index, 8);
            symbols.remove(index);
            symbols.addFirst(c);
        }
        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    private static LinkedList<Character> generateSymbolTable() {
        LinkedList<Character> symbols = new LinkedList<>();
        for (int i = 0; i < 255; i++) {
            symbols.add((char) i);
        }
        return  symbols;
    }

    /**
     * Apply move-to-front decoding, reading from standard input and writing to standard output
     */
    public static void decode() {
        int index;
        LinkedList<Character> symbols = generateSymbolTable();
        while (!BinaryStdIn.isEmpty()) {
            index = BinaryStdIn.readInt(8);
            char c = symbols.get(index);
            BinaryStdOut.write(c, 8);
            symbols.remove(index);
            symbols.addFirst(c);
        }
        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    /**
     * if args[0] is '-', apply move-to-front encoding
     * if args[0] is '+', apply move-to-front decoding
     */
    public static void main(String[] args) {
        if (args[0].charAt(0) == '-') {
            encode();
        } else if (args[0].charAt(0) == '+') {
            decode();
        }
    }
}