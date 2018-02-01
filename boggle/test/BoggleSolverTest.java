import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class BoggleSolverTest {

    BoggleSolver solver;

    @BeforeEach
    void constructSolver() {
        In in = new In("./data/dictionary-algs4.txt");
        String[] dictionary = in.readAllStrings();
        solver = new BoggleSolver(dictionary);
    }

    @Test
    void getAllValidWordsFromBoardWithoutQu() {
        BoggleBoard board = new BoggleBoard("./data/board4x4.txt");
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
        assertEquals(33, score);
    }

    @Test
    void getAllValidWordsFromBoardWithQu() {
        In in = new In("./data/dictionary-algs4.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard("./data/board-q.txt");
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
        assertEquals(84, score);
    }

    @Test
    void getAllValidWordsPerformance() {
        assertTimeout(Duration.ofSeconds(2), ()-> {
            for (int i = 0; i < 100; i++) {
                solver.getAllValidWords(new BoggleBoard(4,4));
            }
        });
    }

}