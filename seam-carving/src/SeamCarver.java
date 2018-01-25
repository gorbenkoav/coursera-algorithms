import edu.princeton.cs.algs4.Picture;

public class SeamCarver {

    private Picture picture;

    /**
     * Create a seam carver object based on the given picture
     *
     * @param picture source image
     */
    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture);
    }

    /**
     * Current picture
     *
     * @return current picture
     */
    public Picture picture() {
        return null;
    }

    /**
     * Width of current picture
     *
     * @return width of current picture
     */
    public int width() {
        return 0;
    }

    /**
     * Height of current picture
     * @return height of current picture
     */
    public int height() {
        return 0;
    }

    /**
     * Energy of pixel at column x and row y
     * @param x column index
     * @param y row index
     * @return energy of pixel
     */
    public double energy(int x, int y) {
        return 0.0;
    }

    /**
     * Sequence of indices for horizontal seam
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

    }

    /**
     * Remove vertical seam from current picture
     * @param seam to be removed
     */
    public void removeVerticalSeam(int[] seam) {

    }
}