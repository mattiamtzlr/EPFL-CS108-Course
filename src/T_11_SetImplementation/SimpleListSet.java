package T_11_SetImplementation;

import T_10_ListImplementation.SimpleLinkedList;
import T_10_ListImplementation.SimpleList;

import java.util.Iterator;

@SuppressWarnings("unused")
public final class SimpleListSet<E> extends SimpleAbstractSet<E> {
    private final SimpleList<E> list = new SimpleLinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(E e) {
        if (!list.contains(e))
            list.add(e);

    }

    @Override
    public void remove(E e) {
        Iterator<E> listIt = list.iterator();

        while (listIt.hasNext()) {
            E e1 = listIt.next();
            if (e1.equals(e)) {
                listIt.remove();
                return;
            }
        }
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
