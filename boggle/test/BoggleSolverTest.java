import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

class BoggleSolverTest {

    private static BoggleSolver solverAlgs;
    private static BoggleSolver solverYawl;

    @BeforeAll
    static void constructSolver() {
        In in = new In("./data/dictionary-algs4.txt");
        String[] dictionary = in.readAllStrings();
        solverAlgs = new BoggleSolver(dictionary);

        in = new In("./data/dictionary-yawl.txt");
        dictionary = in.readAllStrings();
        solverYawl = new BoggleSolver(dictionary);
    }

    @Test
    void getAllValidWordsFromBoardWithoutQu() {
        BoggleBoard board = new BoggleBoard("./data/board4x4.txt");
        int score = 0;
        for (String word : solverAlgs.getAllValidWords(board)) {
            StdOut.println(word);
            score += solverAlgs.scoreOf(word);
        }
        StdOut.println("Score = " + score);
        assertEquals(33, score);
    }

    @Test
    void getAllValidWordsFromBoardWithQu() {
        BoggleBoard board = new BoggleBoard("./data/board-q.txt");
        int score = 0;
        for (String word : solverAlgs.getAllValidWords(board)) {
            StdOut.println(word);
            score += solverAlgs.scoreOf(word);
        }
        StdOut.println("Score = " + score);
        assertEquals(84, score);
    }

    @Test
    void getAllValidWordsPerformance() {
        assertTimeout(Duration.ofSeconds(25), () -> {
            for (int i = 0; i < 10000; i++) {
                solverAlgs.getAllValidWords(new BoggleBoard(4, 4));
            }
        });
    }

    @Test
    void scoreOfLongWord() {
        assertEquals(11, solverYawl.scoreOf("antidisestablishmentarianisms".toUpperCase()));


        solverYawl.getAllValidWords(new BoggleBoard("./data/board-dichlorodiphenyltrichloroethanes.txt"));
    }
}