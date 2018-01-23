import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

import java.util.HashMap;

public class WordNet {

    private final SAP sap;

    private final HashMap<String, Integer> nouns = new HashMap<>();
    private final HashMap<Integer, Iterable<String>> ids = new HashMap<>();

    /**
     * Constructor takes the name of the two input files
     *
     * @param synsets   filename of synonyms
     * @param hypernyms filename of hypernyms
     */
    public WordNet(String synsets, String hypernyms) {
        validate(synsets);
        validate(hypernyms);

        int nounCount = 0;
        In in = new In(synsets);
        while (in.hasNextLine()) {
            String[] line = in.readLine().split(",");
            String[] syns = line[1].split(" ");

            Stack<String> items = new Stack<>();
            ids.put(Integer.valueOf(line[0]), items);

            for (int i = 0; i < syns.length; i++) {
                nouns.put(syns[i], Integer.valueOf(line[0]));
                items.push(syns[i]);
            }
            nounCount++;
        }

        Digraph digraph = new Digraph(nounCount);
        in = new In(hypernyms);
        while (in.hasNextLine()) {
            String[] line = in.readLine().split(",");
            for (int i = 1; i < line.length; i++) {
                digraph.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[i]));
            }

        }
        sap = new SAP(digraph);
    }

    private void validate(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument must not be null");
        }
    }

    private void validateWord(String word) {
        validate(word);
        if (!isNoun(word)) {
            throw new IllegalArgumentException("Word must be a noun");
        }
    }
    /**
     * Returns all WordNet nouns
     *
     * @return number of nouns
     */
    public Iterable<String> nouns() {
        return nouns.keySet();
    }

    /**
     * Is the word a WordNet noun?
     *
     * @param word to be checked
     * @return true if word is a noun
     */
    public boolean isNoun(String word) {
        validate(word);
        return nouns.containsKey(word);
    }

    /**
     * Distance between nounA and nounB
     * distance(A, B) = distance is the minimum length of
     * any ancestral path between any synset v of A and any synset w of B.
     *
     * @param nounA first noun
     * @param nounB second noun
     * @return distance between nouns
     */
    public int distance(String nounA, String nounB) {
        validateWord(nounA);
        validateWord(nounB);
        return sap.length(nouns.get(nounA), nouns.get(nounB));
    }

    /**
     * A synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
     * in a shortest ancestral path (defined below)
     *
     * @param nounA first noun
     * @param nounB second noun
     * @return ancestor noun
     */
    public String sap(String nounA, String nounB) {
        validateWord(nounA);
        validateWord(nounB);
        for (String item : ids.get(sap.ancestor(nouns.get(nounA), nouns.get(nounB)))) {
            return item;
        }
        return null;
    }

    public static void main(String[] args) {

    }
}