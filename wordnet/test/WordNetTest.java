import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordNetTest {

    static WordNet wordnet;

    @BeforeAll
    static void createWordnet() {
        wordnet = new WordNet("./data/synsets.txt", "./data/hypernyms.txt");
    }

    @Test
    void distance() {
        assertEquals(16, wordnet.distance("Sialis", "lynx"));
    }

    @Test
    void sap() {
        assertEquals("abstraction abstract_entity", wordnet.sap("Polybotria", "crapette"));
    }

    @Test
    void rootedDag() {
        assertThrows(IllegalArgumentException.class, () ->
                new WordNet("./data/synsets3.txt", "./data/hypernyms3InvalidTwoRoots.txt"));
    }
}