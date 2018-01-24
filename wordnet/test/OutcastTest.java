import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutcastTest {

    static Outcast outcast;

    @BeforeAll
    static void createOutcast() {
        outcast = new Outcast(new WordNet("./data/synsets.txt", "./data/hypernyms.txt"));
    }

    @Test
    void outcast() {
        assertEquals("bed", outcast.outcast(
                new String[]{"water", "soda", "bed", "orange_juice", "milk", "apple_juice", "tea", "coffee"}));
    }
}