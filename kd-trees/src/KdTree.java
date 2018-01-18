import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.util.TreeSet;

/**
 * Represents a set of points in the unit square
 */
public class KdTree {

    private Node root;
    private int size;

    /**
     * Construct an empty set of points
     */
    public KdTree() {

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
        return root == null;
    }

    /**
     * Number of points in the set
     *
     * @return number of points in the set
     */
    public int size() {
        return size;
    }

    /**
     * Add the point to the set (if it is not already in the set)
     *
     * @param p point to be added
     */
    public void insert(Point2D p) {
        validate(p);
        root = insert(root, p, true, 0, 0, 1, 1);
    }

    private Node insert(Node node, Point2D p, boolean isVerticalDivision,
                        double xmin, double ymin, double xmax, double ymax) {
        if (node == null) {
            size++;
            return new Node(null, p, null, new RectHV(xmin, ymin, xmax, ymax));
        }

        if (p.equals(node.point)) {
            return node;
        }

        if (isVerticalDivision) {
            if (p.x() < node.point.x()) {
                node.left = insert(node.left, p, false, xmin, ymin, node.point.x(), ymax);
            } else {
                node.right = insert(node.right, p, false, node.point.x(), ymin, xmax, ymax);
            }
        } else {
            if (p.y() < node.point.y()) {
                node.left = insert(node.left, p, true, xmin, ymin, xmax, node.point.y());
            } else {
                node.right = insert(node.right, p, true, xmin, node.point.y(), xmax, ymax);
            }
        }

        return node;
    }


    /**
     * Does the set contain point p?
     *
     * @param p point to be checked
     * @return true if set contains point p
     */
    public boolean contains(Point2D p) {
        validate(p);
        return contains(root, p, true);
    }

    private boolean contains(Node node, Point2D p, boolean isVerticalDivision) {
        if (node == null) {
            return false;
        }

        if (p.equals(node.point)) {
            return true;
        }

        if (isVerticalDivision) {
            if (p.x() < node.point.x()) {
                return contains(node.left, p, false);
            } else {
                return contains(node.right, p, false);
            }
        } else {
            if (p.y() < node.point.y()) {
                return contains(node.left, p, true);
            } else {
                return contains(node.right, p, true);
            }
        }

    }

    /**
     * Draws all points to standard draw
     */
    public void draw() {
        draw(root, true);
    }

    private void draw(Node node, boolean isVerticalDivision) {
        if (node == null) {
            return;
        }

        draw(node.left, !isVerticalDivision);

        Color color = StdDraw.getPenColor();
        if (isVerticalDivision) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(), node.rect.ymax());
        } else {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
        }
        StdDraw.setPenColor(color);

        draw(node.right, !isVerticalDivision);
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
        return range(root, rect, new TreeSet<>());
    }

    private Iterable<Point2D> range(Node node, RectHV rect, TreeSet<Point2D> inside) {
        if (node == null) {
            return inside;
        }

        if (rect.intersects(node.rect)) {
            if (rect.contains(node.point)) {
                inside.add(node.point);
            }
            range(node.left, rect, inside);
            range(node.right, rect, inside);
        }
        return inside;
    }

    /**
     * A nearest neighbor in the set to point p; null if the set is empty
     *
     * @param p point to be searched for nearest neighbor
     * @return nearest point
     */
    public Point2D nearest(Point2D p) {
        validate(p);
        if (isEmpty()) {
            return null;
        }
        return nearest(root, p, root.point);
    }

    private Point2D nearest(Node node, Point2D target, Point2D closest) {
        if (node == null) {
            return closest;
        }

        Point2D currentClosest = closest;
        double closestDistance = closest.distanceSquaredTo(target);
        if (Double.compare(node.rect.distanceSquaredTo(target), closestDistance) <= 0) {

            double nodeDistance = node.point.distanceSquaredTo(target);
            currentClosest = Double.compare(nodeDistance, closestDistance) == -1 ? node.point : closest;

            if (node.left != null && node.left.rect.contains(target)) {
                currentClosest = nearest(node.left, target, currentClosest);
                currentClosest = nearest(node.right, target, currentClosest);
            } else {
                currentClosest = nearest(node.right, target, currentClosest);
                currentClosest = nearest(node.left, target, currentClosest);
            }

        }

        return currentClosest;
    }


    public static void main(String[] args) {
        KdTree points = new KdTree();
        points.draw();
    }

    private static class Node {
        private Node left;
        private Point2D point;
        private Node right;
        private RectHV rect;

        public Node(Node left, Point2D point, Node right, RectHV rect) {
            this.left = left;
            this.point = point;
            this.right = right;
            this.rect = rect;
        }
    }
}