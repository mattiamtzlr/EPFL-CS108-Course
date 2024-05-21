package T_11_SetImplementation;

import java.util.StringJoiner;

public abstract class SimpleAbstractSet<E> implements SimpleSet<E> {
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "{", "}");
        this.forEach(e -> sj.add(e.toString()));
        return sj.toString();
    }
}
