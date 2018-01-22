import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node firstSentinelNode;
    private Node lastSentinelNode;
    private int size;

    public Deque() {
        firstSentinelNode = new Node(null, null, null);
        lastSentinelNode = new Node(null, null, null);
        firstSentinelNode.next = lastSentinelNode;
        lastSentinelNode.prev = firstSentinelNode;
    }

    public boolean isEmpty() {
        return size < 1;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item must be not null");
        }
        Node firstNode = new Node(firstSentinelNode, item, firstSentinelNode.next);
        firstSentinelNode.next.prev = firstNode;
        firstSentinelNode.next = firstNode;
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item must be not null");
        }
        Node lastNode = new Node(lastSentinelNode.prev, item, lastSentinelNode);
        lastSentinelNode.prev.next = lastNode;
        lastSentinelNode.prev = lastNode;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node removedNode = firstSentinelNode.next;
        Item removedItem = removedNode.item;
        firstSentinelNode.next = removedNode.next;
        removedNode.next.prev = firstSentinelNode;
        size--;
        return removedItem;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node removedNode = lastSentinelNode.prev;
        Item removedItem = removedNode.item;
        lastSentinelNode.prev = removedNode.prev;
        removedNode.prev.next = lastSentinelNode;
        size--;
        return removedItem;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            Node currentNode = firstSentinelNode;

            @Override
            public boolean hasNext() {
                return currentNode.next != lastSentinelNode;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                currentNode = currentNode.next;
                return currentNode.item;
            }
        };
    }


    private class Node {
        public Node prev;
        public Item item;
        public Node next;

        public Node(Node prev, Item item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

}