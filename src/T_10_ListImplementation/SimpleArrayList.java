package T_10_ListImplementation;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class SimpleArrayList<E> extends SimpleAbstractList<E> {
    private int size = 0;

    private E[] array = (E[]) new Object[10]; // arbitrary start capacity of 10

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int i, E e) {
        checkPositionIndex(i);
        if (size < array.length) {
            System.arraycopy(array, i, array, i + 1, size - i);
        } else {
            E[] newArray = (E[]) new Object[array.length * 2]; // double the size -> best complexity
            System.arraycopy(array, 0, newArray, 0, i);
            System.arraycopy(array, i, newArray, i + 1, size - 1);
            array = newArray;
        }
        array[i] = e;
        size++;
    }

    public void add(E e) {
        add(size, e);
    }

    @Override
    public void remove(int i) {
        checkElementIndex(i);
        System.arraycopy(array, i + 1, array, i, size - i - 1);
        array[--size] = null;
    }

    @Override
    public E get(int i) {
        return array[checkElementIndex(i)];
    }

    @Override
    public E set(int i, E e) {
        E oldE = array[checkElementIndex(i)];
        array[i] = e;
        return oldE;
    }

    @Override
    public Iterator<E> iterator() {
        // anonymous inner class
        return new Iterator<E>() {
            private int nextI = 0;
            private boolean canRemove = false;

            @Override
            public boolean hasNext() {
                return nextI < size;
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                canRemove = true;
                return array[nextI++];
            }

            @Override
            public void remove() {
                if (!canRemove)
                    throw new IllegalStateException();
                SimpleArrayList.this.remove(--nextI);
                canRemove = false;
            }
        };
    }
}
