import java.util.Arrays;

public class BruteCollinearPoints {

    private int count;
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
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

        findSegments(points);
    }

    private void findSegments(Point[] original) {
        Point[] points = Arrays.copyOf(original, original.length);
        Arrays.sort(points);
        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p + 1; q < points.length - 2; q++) {
                for (int r = q + 1; r < points.length - 1; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        if (Double.compare(points[p].slopeTo(points[q]), points[q].slopeTo(points[r])) == 0 &&
                                Double.compare(points[q].slopeTo(points[r]), points[r].slopeTo(points[s])) == 0) {
                            segments[count++] = new LineSegment(points[p], points[s]);
                        }

                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return count;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, count);
    }
}
