import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    @Test
    void moves3x3_0() {
        int[][] blocks = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}};
        Board board = new Board(blocks);
        Solver solver = new Solver(board);
        assertEquals(0, solver.moves());
    }

    @Test
    void moves3x3_4() {
        int[][] blocks = {
                {0, 1, 2},
                {4, 5, 3},
                {7, 8, 6}};
        Board board = new Board(blocks);
        Solver solver = new Solver(board);
        assertEquals(4, solver.moves());
    }

    @Test
    void moves3x3_20() {
        int[][] blocks = {
                {7, 4, 3},
                {2, 8, 6},
                {0, 5, 1}};
        Board board = new Board(blocks);
        Solver solver = new Solver(board);
        assertEquals(20, solver.moves());
    }

    @Test
    void moves2x2_unsolvable() {
        int[][] blocks = {
                {2, 1,},
                {3, 0}};
        Board board = new Board(blocks);
        Solver solver = new Solver(board);
        assertEquals(-1, solver.moves());
    }

    @Test
    void moves3x3_unsolvable() {
        int[][] blocks = {
                {1, 2, 3},
                {4, 5, 6},
                {8, 7, 0}};
        Board board = new Board(blocks);
        Solver solver = new Solver(board);
        assertEquals(-1, solver.moves());
    }
}