package Generics;

import java.util.List;

public class Cell<E> {
    private final E element;

    public Cell(E element) {
        this.element = element;
        var x = new BoundedTypeParam<>();
    }

    public E get() {
        return element;
    }

    public String verbosePrint() {
        return String.format("Cell<%s>: %s", element.getClass().getSimpleName(), element);
    }

    // allow this method to use the parameter S
    public <S> Pair<E, S> pairWith(S second) {
        return new Pair<E, S>(element, second);
    }
}
