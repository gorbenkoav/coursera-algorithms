import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class RandomizedQueueTest {

    @Test
    void isEmpty() {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        assertTrue(queue.isEmpty());
        queue.enqueue("a");
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    void size() {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        assertEquals(0, queue.size());
        queue.enqueue("a");
        assertEquals(1, queue.size());
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    void enqueue() {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");
        assertEquals(5, queue.size());
        assertThrows(IllegalArgumentException.class, () -> queue.enqueue(null));
    }

    @Test
    void dequeue() {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue("a");
        assertEquals("a", queue.dequeue());
        assertThrows(NoSuchElementException.class, queue::dequeue);
    }

    @Test
    void sample() {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        assertThrows(NoSuchElementException.class, queue::sample);
        queue.enqueue("a");
        assertEquals("a", queue.sample());
        assertEquals("a", queue.sample());
    }

    @Test
    void iterator() {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        for (String s : queue) {
            System.out.print(s + " ");
        }
    }
}