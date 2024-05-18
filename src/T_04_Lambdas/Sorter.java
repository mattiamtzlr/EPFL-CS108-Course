package T_04_Lambdas;

import java.util.List;

@SuppressWarnings("unused")
public final class Sorter {
    /**
     * Sorts a list of Integers in the given order.
     *
     * @param l the list to be sorted, modified not returned
     * @param o (Sorter.Order) the order in which the list should be sorted, either ASCENDING or
     *          DESCENDING.
     */
    public static void sort(List<Integer> l, Order o) {
        l.sort(
                (i1, i2) -> o.equals(Order.ASCENDING)
                        ? Integer.compare(i1, i2)
                        : Integer.compare(i2, i1)
        );
    }

    public enum Order {
        ASCENDING, DESCENDING
    }
}