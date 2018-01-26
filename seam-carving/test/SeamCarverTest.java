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

    @Test
    void removeVerticalSeam() {
        Picture picture = new Picture("./data/7x3.png");
        SeamCarver carver = new SeamCarver(picture);
        assertThrows(IllegalArgumentException.class, () -> carver.removeVerticalSeam(new int[]{3}));
        assertThrows(IllegalArgumentException.class, () -> carver.removeVerticalSeam(new int[]{3,0,3}));
        carver.removeVerticalSeam(new int[]{2,3,2});
        assertEquals(6, carver.width());
    }

    @Test
    void removeVerticalSeamInLinePicture() {
        Picture picture = new Picture("./data/8x1.png");
        SeamCarver carver = new SeamCarver(picture);
        carver.removeVerticalSeam(new int[]{2});
        assertEquals(7, carver.width());
    }
}