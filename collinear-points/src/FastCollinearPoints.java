import java.util.Arrays;

public class FastCollinearPoints {

    private int count;
    private LineSegment[] segments;

    private Point[] partPoints;
    private double[] partSlopes;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Argument should not be null");
        }

        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException("Each point should not be null");
            }
        }

        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Repeated points are denied");
                }
            }
        }

        segments = new LineSegment[points.length * points.length];
        partPoints = new Point[points.length * points.length];
        partSlopes = new double[points.length * points.length];
        findSegments(points);
    }

    private void findSegments(Point[] original) {
        Point[] points = Arrays.copyOf(original, original.length);
        Arrays.sort(points);

        for (int p = 0; p < points.length - 3; p++) {
            Arrays.sort(points, p + 1, points.length, points[p].slopeOrder());
            int counter = 0;
            for (int q = p + 1; q < points.length - 1; q++) {
                double slope1 = points[p].slopeTo(points[q]);
                double slope2 = points[p].slopeTo(points[q + 1]);
                boolean isSlopesEqual = Double.compare(slope1, slope2) == 0;

                if (counter >= 2 && !isSlopesEqual) {
                    if (!isLineSegmentExist(points[q], slope1)) {
                        segments[count] = new LineSegment(points[p], points[q]);
                        partPoints[count] = points[q];
                        partSlopes[count] = slope1;
                        count++;
                    }
                } else if (counter >= 1 && isSlopesEqual && q == points.length - 2) {
                    if (!isLineSegmentExist(points[q + 1], slope1)) {
                        segments[count] = new LineSegment(points[p], points[q + 1]);
                        partPoints[count] = points[q + 1];
                        partSlopes[count] = slope1;
                        count++;
                    }
                }

                if (isSlopesEqual) {
                    counter++;
                } else {
                    counter = 0;
                }
            }
            Arrays.sort(points, p + 1, points.length);
        }
    }

    private boolean isLineSegmentExist(Point p, double slope) {
        for (int i = 0; i < count; i++) {
            if (partPoints[i].compareTo(p) == 0 && Double.compare(partSlopes[i], slope) == 0) {
                return true;
            }
        }
        return false;
    }

    public int numberOfSegments() {
        return count;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, count);
    }
}