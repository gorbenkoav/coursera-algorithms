import edu.princeton.cs.algs4.Picture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeamCarverTest {

    @Test
    void energy() {
        Picture picture = new Picture("./data/6x5.png");
        SeamCarver carver = new SeamCarver(picture);

        assertThrows(IllegalArgumentException.class, () -> carver.energy(6, 4));
        assertThrows(IllegalArgumentException.class, () -> carver.energy(0, 5));
        assertEquals(1000.0, carver.energy(0, 0));
        assertEquals(237.35, carver.energy(1, 1), 0.01);
        assertEquals(174.01, carver.energy(2, 3), 0.01);
    }

    @Test
    void findVerticalSeam() {
        Picture picture = new Picture("./data/6x5.png");
        SeamCarver carver = new SeamCarver(picture);
        assertEquals(5, carver.findVerticalSeam().length);
    }
}