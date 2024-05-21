package T_11_SetImplementation;

import java.util.Iterator;

public interface SimpleSet<E> extends Iterable<E> {
    int size();
    boolean isEmpty();
    void add(E e);
    void remove(E e);
    boolean contains(E e);
    Iterator<E> iterator();
}
