package T_11_SetImplementation;

import T_10_ListImplementation.SimpleLinkedList;
import T_10_ListImplementation.SimpleList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashSet<E> extends SimpleAbstractSet<E> {
    private static final int MIN_CAPACITY = 10;
    private static final double MIN_LOAD_FACTOR = 0.4;
    private static final double MAX_LOAD_FACTOR = 1;
    private static final double IDEAL_LOAD_FACTOR = 0.7;

    private int size;
    private SimpleList<E>[] table = newTable(10);

    /**
     * Helper method to create a new hash table with the given capacity
     *
     * @param capacity The capacity, thus number of sub-lists of the hash table
     * @param <E>      The type of the elements to be stored
     * @return The new hash table
     */
    private static <E> SimpleList<E>[] newTable(int capacity) {
        @SuppressWarnings("unchecked")
        SimpleList<E>[] table = new SimpleList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new SimpleLinkedList<>();
        }
        return table;
    }

    /**
     * Helper method to calculate and get the list of the hash table to which element belongs.
     *
     * @param table   The table to search the list for element in
     * @param element The element to search for
     * @param <E>     The type of the elements in the hash table
     * @return The list of the table in which element is (or should be) stored.
     */
    private static <E> SimpleList<E> listFor(SimpleList<E>[] table, E element) {
        // floorMod keeps the sign of the divisor
        return table[Math.floorMod(element.hashCode(), table.length)];
    }

    /**
     * Helper method to rehash the table if its load factor has become too high, meaning its
     * capacity is almost as high as the number of its elements.
     * We want the capacity of the table to be a bit higher than the number of elements, thus the
     * load factor should ideally be smaller than 1.
     *
     * @see #MIN_LOAD_FACTOR
     * @see #MAX_LOAD_FACTOR
     * @see #IDEAL_LOAD_FACTOR
     */
    private void rehashIfNeeded() {
        double loadFactor = size / (double) table.length;
        if (MIN_LOAD_FACTOR <= loadFactor && loadFactor <= MAX_LOAD_FACTOR)
            return;

        int idealCapacity = (int) (size / IDEAL_LOAD_FACTOR);
        if (idealCapacity <= MIN_CAPACITY)
            return;

        SimpleList<E>[] newTable = newTable(idealCapacity);
        for (E e : this) {
            listFor(newTable, e).add(e);
        }

        table = newTable;
    }

    public void printHashTable() {
        System.out.println("Hash Table: ");
        for (int i = 0; i < table.length; i++) {
            System.out.printf("    %02d: %s%n", i, table[i].toString());
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E e) {
        SimpleList<E> target = listFor(table, e);

        if (!target.contains(e)) {
            target.add(e);
            size++;
            rehashIfNeeded();
        }
    }

    @Override
    public void remove(E e) {
        Iterator<E> targetIt = listFor(table, e).iterator();

        while (targetIt.hasNext()) {
            E e1 = targetIt.next();
            if (e1.equals(e)) {
                targetIt.remove();
                size--;
                rehashIfNeeded();
                return;
            }
        }
    }

    @Override
    public boolean contains(E e) {
        return listFor(table, e).contains(e);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int remaining = size;
            private int targetIndex = 0;
            private Iterator<E> targetIt = table[targetIndex].iterator();

            @Override
            public boolean hasNext() {
                return remaining > 0;
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();

                remaining--;
                while (!targetIt.hasNext())
                    targetIt = table[++targetIndex].iterator();

                return targetIt.next();
            }

            @Override
            public void remove() {
                targetIt.remove();
                size--;
            }
        };
    }
}
