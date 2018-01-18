import edu.princeton.cs.algs4.Point2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KdTreeTest {

    @Test
    void isEmpty() {
        KdTree tree = new KdTree();

        assertTrue(tree.isEmpty());

        tree.insert(new Point2D(0.3,0.9));

        assertFalse(tree.isEmpty());
    }

    @Test
    void size() {
        KdTree tree = new KdTree();

        assertEquals(0, tree.size());

        tree.insert(new Point2D(0.3,0.9));
        tree.insert(new Point2D(0.1,0.2));
        tree.insert(new Point2D(0.3,0.9));
        tree.insert(new Point2D(0.3,0.7));

        assertEquals(3, tree.size());
    }

    @Test
    void contains() {
        KdTree tree = new KdTree();

        assertFalse(tree.contains(new Point2D(0.3,0.9)));

        tree.insert(new Point2D(0.3,0.9));
        tree.insert(new Point2D(0.1,0.2));
        tree.insert(new Point2D(0.3,0.9));

        assertFalse(tree.contains(new Point2D(0.3,0.8)));
        assertTrue(tree.contains(new Point2D(0.3,0.9)));
    }
}