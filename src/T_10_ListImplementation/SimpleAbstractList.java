package T_10_ListImplementation;

import java.util.StringJoiner;

public abstract class SimpleAbstractList<E> implements SimpleList<E> {
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(E e) {
        for (E el : this) {
            if (el.equals(e))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (E e : this) {
            sj.add(e.toString());
        }
        return sj.toString();
    }

    /**
     * Checks whether the given index is valid with respect to the elements of the list, thus if
     * it there is an element at that index.
     *
     * @param i the index to be checked
     * @return the index i if it is valid
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    protected final int checkElementIndex(int i) {
        if (!(0 <= i && i < size()))
            throw new IndexOutOfBoundsException(
                    STR."Element index \{i} is invalid for size \{size()}");
        return i;
    }

    /**
     * Checks whether the given index is valid with respect to the insertion positions of the list,
     * thus if it were possible to insert an element at that index.
     *
     * @param i the index to be checked
     * @return the index i if it is valid
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    protected final int checkPositionIndex(int i) {
        if (!(0 <= i && i <= size()))
            throw new IndexOutOfBoundsException(
                    STR."Insertion index \{i} is invalid for size \{size()}");
        return i;
    }
}
