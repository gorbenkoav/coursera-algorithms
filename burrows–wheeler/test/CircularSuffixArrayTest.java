import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircularSuffixArrayTest {

    @Test
    void index() {
        CircularSuffixArray suffixArray = new CircularSuffixArray("ABRACADABRA!");
        assertThrows(IllegalArgumentException.class, () -> suffixArray.index(13));
        assertEquals(2, suffixArray.index(11));
        assertEquals(8, suffixArray.index(6));
    }

    @Test
    void length() {
        assertThrows(IllegalArgumentException.class, () -> new CircularSuffixArray(null));
        assertEquals(0, new CircularSuffixArray("").length());
    }

    @Test
    void largeText() {
        new CircularSuffixArray(new In("./moby1.txt").readAll());
    }
}