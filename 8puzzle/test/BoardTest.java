import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void hammingWithoutWrongPositions() {
        int[][] blocks = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}};
        Board board = new Board(blocks);
        assertEquals(0, board.hamming());
    }

    @Test
    void hammingWithSomeWrongPositions() {
        int[][] blocks = {
                {1, 2, 6},
                {4, 8, 0},
                {7, 3, 5}};
        Board board = new Board(blocks);
        assertEquals(4, board.hamming());
    }

    @Test
    void hammingWithAllWrongPositions() {
        int[][] blocks = {
                {0, 8, 7},
                {1, 4, 5},
                {2, 3, 6}};
        Board board = new Board(blocks);
        assertEquals(8, board.hamming());
    }

    @Test
    void manhattanWithoutWrongPositions() {
        int[][] blocks = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}};
        Board board = new Board(blocks);
        assertEquals(0, board.manhattan());
    }

    @Test
    void manhattanWithSomeWrongPositions() {
        int[][] blocks = {
                {4, 2, 6},
                {1, 5, 0},
                {7, 3, 8}};
        Board board = new Board(blocks);
        assertEquals(7, board.manhattan());
    }

    @Test
    void manhattanWithAllWrongPositions() {
        int[][] blocks = {
                {0, 8, 7},
                {1, 4, 5},
                {2, 3, 6}};
        Board board = new Board(blocks);
        assertEquals(16, board.manhattan());
    }

    @Test
    void isGoal() {
        int[][] blocks = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}};
        Board board = new Board(blocks);
        assertTrue(board.isGoal());
    }

    @Test
    void isNotGoal() {
        int[][] blocks = {
                {1, 2, 3},
                {0, 5, 6},
                {7, 8, 4}};
        Board board = new Board(blocks);
        assertFalse(board.isGoal());
    }
}