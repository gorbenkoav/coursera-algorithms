import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {

    @Test
    void addFirst() {
        Deque<String> deque = new Deque<>();
        assertEquals(0, deque.size());
        deque.addFirst("a");
        assertEquals(1, deque.size());
        deque.addFirst("b");
        deque.addFirst("c");
        assertEquals(3, deque.size());
    }

    @Test
    void addLast() {
        Deque<String> deque = new Deque<>();
        assertEquals(0, deque.size());
        deque.addLast("a");
        deque.addLast("b");
        assertEquals(2, deque.size());
    }

    @Test
    void addMixed() {
        Deque<String> deque = new Deque<>();
        assertEquals(0, deque.size());
        deque.addLast("a");
        deque.addLast("b");
        assertEquals(2, deque.size());
        deque.addFirst("b");
        deque.addFirst("c");
        assertEquals(4, deque.size());
    }


    @Test()
    void addFirstNull() {
        Deque<String> deque = new Deque<>();
        assertThrows(IllegalArgumentException.class, () -> deque.addFirst(null));
    }

    @Test()
    void addLastNull() {
        Deque<String> deque = new Deque<>();
        assertThrows(IllegalArgumentException.class, () -> deque.addLast(null));
    }

    @Test
    void removeFirstFromEmptyList() {
        Deque<String> deque = new Deque<>();
        assertThrows(NoSuchElementException.class, deque::removeFirst);
        deque.addLast("a");
        deque.removeFirst();
        assertThrows(NoSuchElementException.class, deque::removeFirst);
    }

    @Test
    void removeLastFromEmptyList() {
        Deque<String> deque = new Deque<>();
        assertThrows(NoSuchElementException.class, deque::removeLast);
        deque.addFirst("a");
        deque.removeLast();
        assertThrows(NoSuchElementException.class, deque::removeLast);
    }

    @Test
    void removeFirst() {
        Deque<String> deque = new Deque<>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        deque.addLast("d");
        deque.addLast("e");
        deque.addLast("f");

        assertEquals("c", deque.removeFirst());
        assertEquals("b", deque.removeFirst());
        assertEquals("a", deque.removeFirst());
        assertEquals("d", deque.removeFirst());
    }

    @Test
    void removeLast() {
        Deque<String> deque = new Deque<>();
        deque.addFirst("a");
        deque.addFirst("d");
        deque.addLast("f");
        assertEquals("d", deque.removeFirst());
        assertEquals("a", deque.removeFirst());
        assertEquals("f", deque.removeFirst());
        deque.addLast("z");
        assertEquals("z", deque.removeFirst());
    }

    @Test
    void isEmpty() {
        Deque<String> deque = new Deque<>();
        assertTrue(deque.isEmpty());
        deque.addFirst("a");
        assertFalse(deque.isEmpty());
        deque.removeFirst();
        assertTrue(deque.isEmpty());
    }

    @Test
    void size() {
        Deque<String> deque = new Deque<>();
        assertEquals(0, deque.size());
        deque.addFirst("a");
        assertEquals(1, deque.size());
        deque.removeFirst();
        assertEquals(0, deque.size());
    }

    @Test
    void iterator() {
        Deque<String> deque = new Deque<>();
        deque.addFirst("a");
        deque.addFirst("d");
        deque.addLast("f");
        for (String outer : deque) {
            for (String inner : deque) {
                //TODO
            }
        }
    }

}