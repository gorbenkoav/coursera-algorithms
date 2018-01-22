import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void slopeTo() {
        assertThrows(NullPointerException.class, () -> new Point(1, 2).slopeTo(null));
        assertEquals(Double.NEGATIVE_INFINITY, new Point(1, 1).slopeTo(new Point(1, 1)));
        assertEquals(+0.0, new Point(2, 1).slopeTo(new Point(1, 1)));
        assertEquals(Double.POSITIVE_INFINITY, new Point(1, 2).slopeTo(new Point(1, 1)));
        assertEquals(-1.0, new Point(1, 2).slopeTo(new Point(2, 1)));
        assertEquals(-1.5, new Point(0, 3).slopeTo(new Point(2, 0)));
    }

    @Test
    void compareTo() {
        assertEquals(0, new Point(1, 1).compareTo(new Point(1, 1)));
        assertEquals(-1, new Point(1, 1).compareTo(new Point(2, 1)));
        assertEquals(1, new Point(2, 1).compareTo(new Point(1, 1)));
        assertEquals(-1, new Point(1, 1).compareTo(new Point(1, 2)));
        assertEquals(1, new Point(1, 2).compareTo(new Point(1, 1)));
        assertThrows(NullPointerException.class, () -> new Point(1, 2).compareTo(null));
    }

    @Test
    void slopeOrder() {
    }
}