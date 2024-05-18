package T_10_ListImplementation;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> extends SimpleAbstractList<E> {
    private int size = 0;
    private Node<E> head = null;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int i, E e) {
        if (checkPositionIndex(i) == 0) {
            // change head
            head = new Node<>(head, e); // new head now links to old head

        } else {
            Node<E> pred = getNode(i - 1); // pred for predecessor
            pred.next = new Node<>(pred.next, e); // change the content of the previous node
        }
        size++;
    }

    public void add(E e) {
        add(size, e);
    }

    @Override
    public void remove(int i) {
        if (checkElementIndex(i) == 0) {
            head = head.next;   // remove first element head is now next of old head

        } else {
            Node<E> pred = getNode(i - 1);
            // remove any other element, predecessor is next of old predecessor's next
            pred.next = pred.next.next;
        }

        size--;
    }

    @Override
    public E get(int i) {
        return getNode(checkElementIndex(i)).element;
    }

    @Override
    public E set(int i, E e) {
        Node<E> node = getNode(checkElementIndex(i));
        E oldE = node.element;
        node.element = e;
        return oldE;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> pred = null, /*precedes*/ curr = null, /*precedes*/ next = head;
            private boolean canRemove = false;
            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();

                canRemove = true;

                E element = next.element;
                pred = curr;
                curr = next;
                next = next.next;
                return element;
            }

            @Override
            public void remove() {
                if (!canRemove) throw new IllegalStateException();

                canRemove = false;

                if (pred == null)
                    head = head.next; // wrap around
                else
                    pred.next = pred.next.next;

                curr = pred;
                pred = null;
                size--;
            }
        };
    }

    /**
     * Private helper method to find the node corresponding to a given index. It follows the
     * links between the nodes to find the wanted index, which generally leads to complexitiy of
     * O(n).
     *
     * @param i the wanted index
     * @return the node at that index
     */
    private Node<E> getNode(int i) {
        Node<E> n = head;
        for (int j = 0; j < i; j++) {
            n = n.next;
        }
        return n;
    }

    private static final class Node<E> {
        private Node<E> next; // if this is null, it means that this is the last node in the list.
        private E element;

        public Node(Node<E> next, E element) {
            this.next = next;
            this.element = element;
        }
    }
}
