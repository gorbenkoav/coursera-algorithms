public class WordNet {

    /** Constructor takes the name of the two input files
     * @param synsets filename of synonyms
     * @param hypernyms filename of hypernyms
     */
    public WordNet(String synsets, String hypernyms) {

    }

    /**
     * Returns all WordNet nouns
     * @return number of nouns
     */
    public Iterable<String> nouns() {
        return null;
    }

    /**
     * Is the word a WordNet noun?
     * @param word to be checked
     * @return true if word is a noun
     */
    public boolean isNoun(String word) {
        return false;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        return 0;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        return null;
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}