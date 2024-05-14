package Lists;

import java.util.Iterator;

public interface SimpleList<E> extends Iterable<E> {
    int size();
    boolean isEmpty();
    void add(int i, E e);
    void remove(int i);
    boolean contains(E e);
    E get(int i);
    E set(int i, E e);
    Iterator<E> iterator();
}
