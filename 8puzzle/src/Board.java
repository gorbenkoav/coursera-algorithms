import java.util.Stack;

public class Board {

    private final int[][] board;
    private final int dimension;

    public Board(int[][] blocks) {
        dimension = blocks.length;
        board = copyBlocks(blocks);
    }

    private int[][] copyBlocks(int[][] blocks) {
        int[][] copy = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                copy[i][j] = blocks[i][j];
            }
        }
        return copy;
    }

    public int dimension() {
        return dimension;
    }

    public int hamming() {
        int count = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (!isBlankBlock(i, j) && !isRightPositionBlock(i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isBlankBlock(int row, int col) {
        return board[row][col] == 0;
    }

    private boolean isRightPositionBlock(int row, int col) {
        return isBlankBlock(row, col) ? isLastBlock(row, col) : board[row][col] == row * dimension + col + 1;
    }

    private boolean isLastBlock(int row, int col) {
        return row == dimension - 1 && col == dimension - 1;
    }

    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (!isBlankBlock(i, j) && !isRightPositionBlock(i, j)) {
                    int rightPositionRow = (board[i][j] - 1) / dimension;
                    int rightPositionCol = (board[i][j] - 1) % dimension;
                    sum += abs(i - rightPositionRow) + abs(j - rightPositionCol);
                }
            }
        }
        return sum;
    }

    private int abs(int num) {
        return num < 0 ? -num : num;
    }

    public boolean isGoal() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (!isRightPositionBlock(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Board twin() {
        int[][] twin = copyBlocks(board);

        exchange:
        for (int i = 0; i < dimension; i++) {
            for (int j = 1; j < dimension; j++) {
                if (!isBlankBlock(i, j) && !isBlankBlock(i, j - 1)) {
                    swapBlocks(twin, i, j, i, j - 1);
                    break exchange;
                }
            }
        }

        return new Board(twin);
    }

    private void swapBlocks(int[][] blocks, int row1, int col1, int row2, int col2) {
        int swap = blocks[row1][col1];
        blocks[row1][col1] = blocks[row2][col2];
        blocks[row2][col2] = swap;
    }

    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (this == y) {
            return true;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }

        Board other = (Board) y;
        return toString().equals(other.toString());
    }

    private Board createNeighbor(int[][] original, int row1, int col1, int row2, int col2) {
        int[][] neighbor = copyBlocks(original);
        swapBlocks(neighbor, row1, col1, row2, col2);
        return new Board(neighbor);
    }

    public Iterable<Board> neighbors() {
        Stack<Board> neighbors = new Stack<>();

        create:
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (isBlankBlock(i, j)) {
                    if (i - 1 >= 0) {
                        neighbors.push(createNeighbor(board, i, j, i - 1, j));
                    }
                    if (i + 1 < dimension) {
                        neighbors.push(createNeighbor(board, i, j, i + 1, j));
                    }
                    if (j - 1 >= 0) {
                        neighbors.push(createNeighbor(board, i, j, i, j - 1));
                    }
                    if (j + 1 < dimension) {
                        neighbors.push(createNeighbor(board, i, j, i, j + 1));
                    }
                    break create;
                }
            }
        }

        return neighbors;
    }

    public String toString() {
        StringBuilder boardPicture = new StringBuilder(dimension + "\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                boardPicture.append(" ").append(board[i][j]);
            }
            boardPicture.append("\n");
        }
        return boardPicture.toString();
    }

    public static void main(String[] args) {
        System.out.println(7 % 4);
        int[][] blocks = {
                {0, 2},
                {1, 5}};
        Board board = new Board(blocks);
        board.twin();

    } // unit tests (not graded)
}