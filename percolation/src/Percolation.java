import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n;
    private int numberOfOpenSites;
    private boolean[] openedSites;
    private final WeightedQuickUnionUF unionUF;
    private final WeightedQuickUnionUF backwashUF;

    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n should be greater than zero");
        }
        this.n = n;
        openedSites = new boolean[n * n];
        unionUF = new WeightedQuickUnionUF(n * n + 2);
        backwashUF = new WeightedQuickUnionUF(n * n + 1);
        if (n > 1) {
            for (int i = 0; i < n; i++) {
                backwashUF.union(i, n * n);
                unionUF.union(i, n * n);
                unionUF.union(n * n - i - 1, n * n + 1);
            }
        }
    }

    public void open(int row, int col) {
        checkRange(row, col);
        if (!isOpen(row, col)) {
            numberOfOpenSites++;
            openedSites[coors2point(row, col)] = true;
            if (n > 1) {
                if (col != n && isOpen(row, col + 1)) {
                    unionUF.union(coors2point(row, col), coors2point(row, col + 1));
                    backwashUF.union(coors2point(row, col), coors2point(row, col + 1));
                }
                if (row != n && isOpen(row + 1, col)) {
                    unionUF.union(coors2point(row, col), coors2point(row + 1, col));
                    backwashUF.union(coors2point(row, col), coors2point(row + 1, col));
                }
                if (col != 1 && isOpen(row, col - 1)) {
                    unionUF.union(coors2point(row, col), coors2point(row, col - 1));
                    backwashUF.union(coors2point(row, col), coors2point(row, col - 1));
                }
                if (row != 1 && isOpen(row - 1, col)) {
                    unionUF.union(coors2point(row, col), coors2point(row - 1, col));
                    backwashUF.union(coors2point(row, col), coors2point(row - 1, col));
                }
            } else {
                backwashUF.union(0, 1);
                unionUF.union(0, 1);
                unionUF.union(0, 2);
            }

        }
    }

    private int coors2point(int row, int col) {
        return (row - 1) * n + col - 1;
    }

    private void checkRange(int row, int col) {
        if (row < 1 || row > n) {
            throw new IllegalArgumentException("parameter row should be between 1 and n");
        }

        if (col < 1 || col > n) {
            throw new IllegalArgumentException("parameter col should be between 1 and n");
        }
    }

    public boolean isOpen(int row, int col) {
        checkRange(row, col);
        return openedSites[coors2point(row, col)];
    }

    public boolean isFull(int row, int col) {
        return isOpen(row, col)
                && unionUF.connected(coors2point(row, col), n * n)
                && backwashUF.connected(coors2point(row, col), n * n);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return unionUF.connected(n * n, n * n + 1);
    }

}
