import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KdTreeTest {

    @Test
    void isEmpty() {
        KdTree tree = new KdTree();

        assertTrue(tree.isEmpty());

        tree.insert(new Point2D(0.3, 0.9));

        assertFalse(tree.isEmpty());
    }

    @Test
    void size() {
        KdTree tree = new KdTree();

        assertEquals(0, tree.size());

        tree.insert(new Point2D(0.3, 0.9));
        tree.insert(new Point2D(0.1, 0.2));
        tree.insert(new Point2D(0.3, 0.9));
        tree.insert(new Point2D(0.3, 0.7));

        assertEquals(3, tree.size());
    }

    @Test
    void contains() {
        KdTree tree = new KdTree();

        assertFalse(tree.contains(new Point2D(0.3, 0.9)));

        tree.insert(new Point2D(0.3, 0.9));
        tree.insert(new Point2D(0.1, 0.2));
        tree.insert(new Point2D(0.3, 0.9));

        assertFalse(tree.contains(new Point2D(0.3, 0.8)));
        assertTrue(tree.contains(new Point2D(0.3, 0.9)));
    }

    @Test
    void nearest() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.372, 0.497));
        kdTree.insert(new Point2D(0.564, 0.413));
        kdTree.insert(new Point2D(0.226, 0.577));
        kdTree.insert(new Point2D(0.144, 0.179));
        kdTree.insert(new Point2D(0.083, 0.510));
        kdTree.insert(new Point2D(0.320, 0.708));
        kdTree.insert(new Point2D(0.417, 0.362));
        kdTree.insert(new Point2D(0.862, 0.825));
        kdTree.insert(new Point2D(0.785, 0.725));
        kdTree.insert(new Point2D(0.499, 0.208));

        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(0.372, 0.497));
        pointSET.insert(new Point2D(0.564, 0.413));
        pointSET.insert(new Point2D(0.226, 0.577));
        pointSET.insert(new Point2D(0.144, 0.179));
        pointSET.insert(new Point2D(0.083, 0.510));
        pointSET.insert(new Point2D(0.320, 0.708));
        pointSET.insert(new Point2D(0.417, 0.362));
        pointSET.insert(new Point2D(0.862, 0.825));
        pointSET.insert(new Point2D(0.785, 0.725));
        pointSET.insert(new Point2D(0.499, 0.208));

        Point2D randomPoint;
        for (int i = 0; i < 1000; i++) {
            randomPoint = new Point2D(StdRandom.uniform(), StdRandom.uniform());
            assertEquals(kdTree.nearest(randomPoint), pointSET.nearest(randomPoint));
        }


    }
}