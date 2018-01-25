import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Picture;

import java.awt.Color;
import java.util.function.IntSupplier;

public class SeamCarver {

    private int[][] pixels;

    /**
     * Create a seam carver object based on the given picture
     *
     * @param picture source image
     */
    public SeamCarver(Picture picture) {
        validate(picture);
        pixels = createPixels(picture);
    }

    private int[][] createPixels(Picture picture) {
        int[][] colors = new int[picture.width()][picture.height()];
        for (int i = 0; i < picture.width(); i++) {
            for (int j = 0; j < picture.height(); j++) {
                colors[i][j] = picture.getRGB(i, j);
            }
        }
        return colors;
    }

    private Picture createPicture(int[][] colors) {
        int width = colors.length;
        int height = colors[0].length;
        Picture pic = new Picture(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pic.setRGB(i, j, colors[i][j]);
            }
        }
        return pic;
    }

    /**
     * Current picture
     *
     * @return current picture
     */
    public Picture picture() {
        return createPicture(pixels);
    }

    /**
     * Width of current picture
     *
     * @return width of current picture
     */
    public int width() {
        return pixels.length;
    }

    /**
     * Height of current picture
     *
     * @return height of current picture
     */
    public int height() {
        return pixels[0].length;
    }

    /**
     * Energy of pixel at column x and row y
     *
     * @param x column index
     * @param y row index
     * @return energy of pixel
     */
    public double energy(int x, int y) {
        validateRange(x, width());
        validateRange(y, height());

        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1) {
            return 1000.0;
        }

        Color upPixel = new Color(pixels[x - 1][y]);
        Color downPixel = new Color(pixels[x + 1][y]);
        Color leftPixel = new Color(pixels[x][y - 1]);
        Color rightPixel = new Color(pixels[x][y + 1]);

        return Math.sqrt(getDelta(upPixel, downPixel) + getDelta(leftPixel, rightPixel));
    }

    private double getDelta(Color upPixel, Color downPixel) {
        return Math.pow(upPixel.getRed() - downPixel.getRed(), 2) +
                Math.pow(upPixel.getGreen() - downPixel.getGreen(), 2) +
                Math.pow(upPixel.getBlue() - downPixel.getBlue(), 2);
    }

    /**
     * Sequence of indices for horizontal seam
     *
     * @return sequence of indices for horizontal seam
     */
    public int[] findHorizontalSeam() {
        pixels = transposePicture(pixels);
        int[] seam = findVerticalSeam();
        pixels = transposePicture(pixels);
        return seam;
    }

    /**
     * Sequence of indices for vertical seam
     *
     * @return sequence of indices for vertical seam
     */
    public int[] findVerticalSeam() {
        int width = width();
        int height = height();
        double[][] energy = new double[width][height];
        double[][] dists = new double[width][height];
        int[][] edges = new int[width][height];
        IndexMinPQ<Double> queue = new IndexMinPQ<>(width * height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                energy[i][j] = energy(i, j);
                dists[i][j] = Double.POSITIVE_INFINITY;
            }
            queue.insert(i * height, 0.0/*energy(i, 0)*/);
            dists[i][0] = 0.0/*energy(i, 0)*/;
        }

        while (!queue.isEmpty()) {
            int index = queue.delMin();
            relax(index, -1, queue, dists, energy, edges);
            relax(index, 0, queue, dists, energy, edges);
            relax(index, +1, queue, dists, energy, edges);
        }

        double minDist = Double.POSITIVE_INFINITY;
        int lastColumnIndex = 0;
        for (int i = 0; i < width; i++) {
            if (minDist > dists[i][height - 1]) {
                minDist = dists[i][height - 1];
                lastColumnIndex = i;
            }
        }

        int[] seam = new int[height];
        seam[height - 1] = lastColumnIndex;
        for (int i = height - 1; i > 0; i--) {
            seam[i - 1] = getColumnIndex(edges[seam[i]][i]);
        }

        return seam;
    }

    private void relax(int queueIndex, int delta, IndexMinPQ<Double> queue,
                       double[][] dists, double[][] energy, int[][] edges) {
        int col = getColumnIndex(queueIndex);
        int row = getRowIndex(queueIndex);
        int nextCol = col + delta;
        int nextRow = row + 1;

        if (nextRow < height() && nextCol >= 0 && nextCol < width()) {
            if (dists[nextCol][nextRow] > dists[col][row] + energy[nextCol][nextRow]) {

                dists[nextCol][nextRow] = dists[col][row] + energy[nextCol][nextRow];
                edges[nextCol][nextRow] = queueIndex;

                int nextIndex = getArrayIndex(nextCol, nextRow);
                if (queue.contains(nextIndex)) {
                    queue.decreaseKey(nextIndex, dists[nextCol][nextRow]);
                } else {
                    queue.insert(nextIndex, dists[nextCol][nextRow]);
                }
            }
        }
    }

    private int getArrayIndex(int col, int row) {
        return col * height() + row;
    }

    private int getColumnIndex(int arrayIndex) {
        return arrayIndex / height();
    }

    private int getRowIndex(int arrayIndex) {
        return arrayIndex % height();
    }

    /**
     * Remove horizontal seam from current picture
     *
     * @param seam to be removed
     */
    public void removeHorizontalSeam(int[] seam) {
        validateSeam(seam, this::width);
        pixels = transposePicture(pixels);
        removeVerticalSeam(seam);
        pixels = transposePicture(pixels);
    }

    /**
     * Remove vertical seam from current picture
     *
     * @param seam to be removed
     */
    public void removeVerticalSeam(int[] seam) {
        validateSeam(seam, this::height);
        int[][] colors = new int[width() - 1][height()];
        for (int i = 0; i < height(); i++) {
            int row = 0;
            for (int j = 0; j < width(); j++) {
                if (j != seam[i]) {
                    colors[j][row++] = pixels[j][i];
                }
            }
        }
        pixels = colors;
    }

    private int[][] transposePicture(int[][] colors) {
        int width = colors.length;
        int height = colors[0].length;
        int[][] pic = new int[height][width];
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                pic[j][i] = colors[i][j];
        return pic;
    }

    private void validate(Object arg) {
        if (arg == null) {
            throw new IllegalArgumentException("Argument must not be null");
        }
    }

    private void validateRange(int index, int max) {
        if (index < 0 || index >= max) {
            throw new IllegalArgumentException("Index " + index + " should be between 0 and " + (max - 1));
        }
    }

    private void validateSeam(int[] seam, IntSupplier dimensionSize) {
        validate(seam);
        int dim = dimensionSize.getAsInt();

        if (seam.length != dim) {
            throw new IllegalArgumentException("Size of picture is too small");
        }

        for (int i = 0; i < seam.length; i++) {
            validateRange(seam[i], dim);
            if (i != 0 && Math.abs(seam[i] - seam[i - 1]) > 1) {
                throw new IllegalArgumentException("Diff between 2 neighbour entries more than one");
            }

        }

        if (dimensionSize.getAsInt() <= 1) {
            throw new IllegalArgumentException("Size of picture is too small");
        }
    }
}