import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int count;

    public RandomizedQueue() {
        items = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return count < 1;
    }

    public int size() {
        return count;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item must be not null");
        }
        if (count == items.length) {
            resize(count * 2);
        }
        items[count] = item;
        count++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(size());
        Item removedItem = items[randomIndex];
        items[randomIndex] = items[count - 1];
        items[count - 1] = null;
        count--;
        if (!isEmpty() && count < items.length / 4) {
            resize(items.length / 2);
        }
        return removedItem;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return items[StdRandom.uniform(size())];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<>();
    }

    private void resize(int newSize) {
        Item[] copy = (Item[]) new Object[newSize];
        for (int i = 0; i < count; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item> {

        int[] availableItems = new int[count];
        int availableItemsCount;

        public RandomizedQueueIterator() {
            availableItemsCount = count;
            for (int i = 0; i < count; i++) {
                availableItems[i] = i;
            }
        }

        @Override
        public boolean hasNext() {
            return availableItemsCount > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int randomIndex = StdRandom.uniform(availableItemsCount);
            Item randomItem = (Item) items[availableItems[randomIndex]];
            availableItems[randomIndex] = availableItems[availableItemsCount - 1];
            availableItemsCount--;
            return randomItem;
        }
    }
}