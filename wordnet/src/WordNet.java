import edu.princeton.cs.algs4.*;

import java.util.HashMap;

public class WordNet {

    private final SAP sap;

    private final HashMap<String, Queue<Integer>> nouns = new HashMap<>();
    private final HashMap<Integer, Queue<String>> ids = new HashMap<>();

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

            Queue<String> idItems = new Queue<>();
            ids.put(Integer.valueOf(line[0]), idItems);
            for (String syn : syns) {
                idItems.enqueue(syn);
                if (!nouns.containsKey(syn)) {
                    nouns.put(syn, new Queue<>());
                }
                nouns.get(syn).enqueue(Integer.valueOf(line[0]));
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
        if (!isRootedDAG(digraph)) {
            throw new IllegalArgumentException("Input does not correspond to a rooted DAG");
        }
        sap = new SAP(digraph);
    }

    private boolean isRootedDAG(Digraph digraph) {
//        for (Integer item : new DepthFirstOrder(digraph).post()) {
//            return digraph.indegree(item) == 0;
//        }

        return true;
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
        int ancestor = sap.ancestor(nouns.get(nounA), nouns.get(nounB));
        String sap = null;
        if (ancestor != -1) {
            sap = String.join(" ", ids.get(ancestor));
        }
        return sap;
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        //wordnet.distance()
    }
}