import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircularSuffixArrayTest {

    @Test
    void index() {
        assertThrows(IllegalArgumentException.class, () -> new CircularSuffixArray(null));
        CircularSuffixArray suffixArray = new CircularSuffixArray("ABRACADABRA!");
        assertThrows(IllegalArgumentException.class, () -> suffixArray.index(13));
        assertEquals(2, suffixArray.index(11));
        assertEquals(8, suffixArray.index(6));
    }
}