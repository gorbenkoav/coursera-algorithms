import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private final WordNet wordnet;

    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    /**
     * Given an array of WordNet nouns, return an outcast
     *
     * @param nouns an array of WordNet nouns
     * @return outcast word
     */
    public String outcast(String[] nouns) {
        int maxDistance = 0;
        String outcast = null;
        for (int i = 0; i < nouns.length; i++) {
            int sum = 0;
            int dist;
            for (int j = 0; j < nouns.length; j++) {
                dist = wordnet.distance(nouns[i], nouns[j]);
                if (dist != -1) {
                    sum += dist;
                }
            }
            if (maxDistance < sum) {
                maxDistance = sum;
                outcast = nouns[i];
            }
        }
        return outcast;
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}