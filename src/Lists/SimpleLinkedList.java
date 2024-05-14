package Lists;

import java.util.Iterator;

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

    @Override
    public void remove(int i) {

    }

    @Override
    public E get(int i) {
        return null;
    }

    @Override
    public E set(int i, E e) {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
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
