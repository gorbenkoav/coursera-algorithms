import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a set of points in the unit square
 */
public class PointSET {

    private Set<Point2D> points;

    /**
     * Construct an empty set of points
     */
    public PointSET() {
        points = new TreeSet<>();
    }

    /**
     * Validate point
     *
     * @param p point to be validated
     * @throws IllegalArgumentException if point is null
     */
    private void validate(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Point must not be null");
        }
    }

    /**
     * Is the set empty?
     *
     * @return true if this set contains no elements
     */
    public boolean isEmpty() {
        return points.isEmpty();
    }

    /**
     * Number of points in the set
     *
     * @return number of points in the set
     */
    public int size() {
        return points.size();
    }

    /**
     * Add the point to the set (if it is not already in the set)
     *
     * @param p point to be added
     */
    public void insert(Point2D p) {
        validate(p);
        points.add(p);
    }

    /**
     * Does the set contain point p?
     *
     * @param p point to be checked
     * @return true if set contains point p
     */
    public boolean contains(Point2D p) {
        validate(p);
        return points.contains(p);
    }

    /**
     * Draws all points to standard draw
     */
    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }

    }

    /**
     * Get all points that are inside the rectangle (or on the boundary)
     *
     * @param rect rectangle in the unit square
     * @return points that are inside the rectangle
     */
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("Rectangle must not be null");
        }
        return points.stream().filter(rect::contains)::iterator;
    }

    /**
     * A nearest neighbor in the set to point p; null if the set is empty
     *
     * @param p point to be searched for nearest neighbor
     * @return nearest point
     */
    public Point2D nearest(Point2D p) {
        validate(p);
        return points.stream().min((o1, o2) -> {
            if (o1.equals(o2)) {
                return 0;
            }
            return o1.distanceSquaredTo(p) < o2.distanceSquaredTo(p) ? -1 : 1;
        }).orElse(null);
    }


    public static void main(String[] args) {
        PointSET points = new PointSET();
        points.insert(new Point2D(0.1, 0.3));
        StdDraw.setPenRadius(0.02);
        points.draw();
        StdDraw.show();
    }
}