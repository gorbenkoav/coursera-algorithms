import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class BaseballEliminationTest {
    @Test
    void detroit() {
        BaseballElimination division = new BaseballElimination("./data/teams5.txt");
        assertThrows(IllegalArgumentException.class, () -> division.isEliminated("LA"));
        assertTrue(division.isEliminated("Detroit"));

        Iterable<String> certificateOfElimination = division.certificateOfElimination("Detroit");
        AtomicInteger count = new AtomicInteger();
        certificateOfElimination.forEach(s -> count.getAndIncrement());
        assertEquals(4, count.get());

        assertFalse(division.isEliminated("Toronto"));
        assertFalse(division.isEliminated("Boston"));
        assertFalse(division.isEliminated("Baltimore"));
        assertFalse(division.isEliminated("New_York"));
    }

    @Test
    void philadelphia() {
        BaseballElimination division = new BaseballElimination("./data/teams4.txt");
        assertTrue(division.isEliminated("Philadelphia"));
        assertFalse(division.isEliminated("Atlanta"));
        assertTrue(division.isEliminated("Montreal"));
        assertFalse(division.isEliminated("New_York"));
    }

    @Test
    void ghaddafi() {
        BaseballElimination division = new BaseballElimination("./data/teams4a.txt");
        assertTrue(division.isEliminated("Ghaddafi"));
        assertFalse(division.isEliminated("CIA"));
        assertTrue(division.isEliminated("Bin_Ladin"));
        assertFalse(division.isEliminated("Obama"));
    }

    @Test
    void baltimore() {
        BaseballElimination division = new BaseballElimination("./data/teams5b.txt");
        assertFalse(division.isEliminated("Baltimore"));
    }

    @Test
    void indiana() {
        BaseballElimination division = new BaseballElimination("./data/teams10.txt");
        assertFalse(division.isEliminated("Indiana"));
    }
}