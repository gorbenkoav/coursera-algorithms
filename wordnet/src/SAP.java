import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {

    private final Digraph graph;

    /**
     * Constructor takes a digraph (not necessarily a DAG)
     * @param graph digraph
     */
    public SAP(Digraph graph) {
        validate(graph);
        this.graph = new Digraph(graph);
    }

    private void validate(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument must not be null");
        }
    }

    /**
     * Length of shortest ancestral path between v and w; -1 if no such path
     * @param v first vertex
     * @param w second vertex
     * @return shortest ancestral path
     */
    public int length(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return length(new BreadthFirstDirectedPaths(graph, v), new BreadthFirstDirectedPaths(graph, w));
    }

    private int length(BreadthFirstDirectedPaths vPaths, BreadthFirstDirectedPaths wPaths) {
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < graph.V(); i++) {
            if (vPaths.hasPathTo(i) && wPaths.hasPathTo(i)) {
                minDistance = Integer.min(minDistance, vPaths.distTo(i) + wPaths.distTo(i));
            }
        }
        return Integer.compare(minDistance, Integer.MAX_VALUE) == 0 ? -1: minDistance;
    }

    private void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= graph.V()) {
            throw new IllegalArgumentException("Vertex out of range");
        }
    }

    /**
     * A common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
     * @param v first vertex
     * @param w second vertex
     * @return ancestor of v and w that participates in a shortest ancestral path;
     */
    public int ancestor(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return ancestor(new BreadthFirstDirectedPaths(graph, v), new BreadthFirstDirectedPaths(graph, w));
    }

    private int ancestor(BreadthFirstDirectedPaths vPaths, BreadthFirstDirectedPaths wPaths) {
        int minDistance = Integer.MAX_VALUE;
        int minVertex = -1;
        for (int i = 0; i < graph.V(); i++) {
            if (vPaths.hasPathTo(i) && wPaths.hasPathTo(i)) {
                int iDistance = vPaths.distTo(i) + wPaths.distTo(i);
                if (iDistance < minDistance) {
                    minDistance = iDistance;
                    minVertex = i;
                }
            }
        }
        return minVertex;
    }

    /**
     * length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     * @param v first set of vertex
     * @param w second set of vertex
     * @return shortest ancestral path between any vertex in v and any vertex in w
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        validate(v);
        validate(w);
        return length(new BreadthFirstDirectedPaths(graph, v), new BreadthFirstDirectedPaths(graph, w));
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        validate(v);
        validate(w);
        return ancestor(new BreadthFirstDirectedPaths(graph, v), new BreadthFirstDirectedPaths(graph, w));
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}