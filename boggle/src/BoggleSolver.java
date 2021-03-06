import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TST;

import java.util.HashSet;
import java.util.Set;

public class BoggleSolver {

    private final TST<Boolean> dictionary;

    /**
     * Initializes the data structure using the given array of strings as the dictionary.
     * (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
     *
     * @param dictionary with allowed dictionary
     */
    public BoggleSolver(String[] dictionary) {
        this.dictionary = new TST<>();
        for (String s : dictionary) {
            this.dictionary.put(s, true);
        }
    }

    /**
     * Returns the set of all valid dictionary in the given Boggle board, as an Iterable.
     *
     * @param board with 16 letters
     * @return all founded dictionary
     */
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        Set<String> foundedWords = new HashSet<>();
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                searchWords(board, "", i, j, foundedWords, new boolean[board.rows() * board.cols()]);
            }
        }
        return foundedWords.stream().sorted()::iterator;
    }

    private void searchWords(BoggleBoard board, String word, int i, int j, Set<String> foundedWords, boolean[] path) {
        char letter = board.getLetter(i, j);
        word += String.valueOf(letter);
        if (letter == 'Q') {
            word += 'U';
        }
        path[board.cols() * i + j] = true;

        if (isThereWordsWithPrefix(word)) {
            if (scoreOf(word) > 0 && !foundedWords.contains(word)) {
                foundedWords.add(word);
            }

            for (int k = i - 1; k <= i + 1; k++) {
                for (int l = j - 1; l <= j + 1; l++) {
                    if ((i != k || l != j) && k >= 0 && k < board.rows() && l >= 0 && l < board.cols()) {
                        if (!path[board.cols() * k + l]) {
                            searchWords(board, word, k, l, foundedWords, path);
                        }
                    }
                }
            }
        }
        path[board.cols() * i + j] = false;
    }


    private boolean isThereWordsWithPrefix(String prefix) {
        return dictionary.keysWithPrefix(prefix).iterator().hasNext();
    }

    /**
     * Returns the score of the given word if it is in the dictionary, zero otherwise.
     * (You can assume the word contains only the uppercase letters A through Z.)
     *
     * @param word to be scored
     * @return score of the word
     */
    public int scoreOf(String word) {
        if (dictionary.contains(word)) {
            switch (word.length()) {
                case 0:
                case 1:
                case 2:
                    return 0;
                case 3:
                case 4:
                    return 1;
                case 5:
                    return 2;
                case 6:
                    return 3;
                case 7:
                    return 5;
                default:
                    return 11;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }
}
