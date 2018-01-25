import edu.princeton.cs.algs4.Picture;

import java.util.function.IntSupplier;

public class SeamCarver {

    private Picture picture;

    /**
     * Create a seam carver object based on the given picture
     *
     * @param picture source image
     */
    public SeamCarver(Picture picture) {
        validate(picture);
        this.picture = new Picture(picture);
    }

    /**
     * Current picture
     *
     * @return current picture
     */
    public Picture picture() {
        return picture;
    }

    /**
     * Width of current picture
     *
     * @return width of current picture
     */
    public int width() {
        return picture.width();
    }

    /**
     * Height of current picture
     *
     * @return height of current picture
     */
    public int height() {
        return picture.height();
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


        return 0.0;
    }

    /**
     * Sequence of indices for horizontal seam
     *
     * @return sequence of indices for horizontal seam
     */
    public int[] findHorizontalSeam() {
        return null;
    }

    /**
     * Sequence of indices for vertical seam
     *
     * @return sequence of indices for vertical seam
     */
    public int[] findVerticalSeam() {
        return null;
    }

    /**
     * Remove horizontal seam from current picture
     *
     * @param seam to be removed
     */
    public void removeHorizontalSeam(int[] seam) {
        validateSeam(seam, this::width);
    }

    /**
     * Remove vertical seam from current picture
     *
     * @param seam to be removed
     */
    public void removeVerticalSeam(int[] seam) {
        validateSeam(seam, this::height);
    }

    private void validate(Object arg) {
        if (arg == null) {
            throw new IllegalArgumentException("Argument must not be null");
        }
    }

    private void validateRange(int index, int max) {
        if (index < 0 || index >= max) {
            throw new IllegalArgumentException("Index " + index + "should be between 0 and " + (max - 1));
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
            if (i != 0 && seam[i] - seam[i-1] > 1) {
                throw new IllegalArgumentException("Diff between 2 neighbour entries more than one");
            }

        }

        if (dimensionSize.getAsInt() <= 1) {
            throw new IllegalArgumentException("Size of picture is too small");
        }
    }
}