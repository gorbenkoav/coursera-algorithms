import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    public PercolationStats(int n, int trials) {
        if (n < 1) {
            throw new IllegalArgumentException("n should be greater than zero");
        }
        if (trials < 1) {
            throw new IllegalArgumentException("trials should be greater than zero");
        }

        int[] openedSites = new int[trials];
        Percolation percolation;
        for (int i = 0; i < trials; i++) {
            percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                }
            }
            openedSites[i] = percolation.numberOfOpenSites();
        }

        mean = StdStats.mean(openedSites) / (n * n);
        stddev = StdStats.stddev(openedSites) / (n * n);
        double delta = 1.96 * stddev / Math.sqrt(trials);
        confidenceLo = mean - delta;
        confidenceHi = mean + delta;
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidenceLo;
    }

    public double confidenceHi() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean\t = " + stats.mean());
        System.out.println("stddev\t = " + stats.stddev());
        System.out.println("95% confidence interval\t = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
