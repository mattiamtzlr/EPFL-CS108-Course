package Collections;

import java.util.*;

public class Iterators {
    // useful way to loop over elements of a collection
    public static Set<String> strings = new HashSet<>(Set.of("Hello", "World"));
    public static List<Integer> integers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));

    public static void main(String[] args) {
        printCollection(strings);
        printCollection(integers);
    }

    public static <E> void printCollection(Collection<E> collection) {
        Iterator<E> it = collection.iterator();
        StringBuilder output = new StringBuilder("Collection: ");

        while (it.hasNext()) {
            output.append(" '").append(it.next().toString()).append("'");
        }

        System.out.println(output);
    }
}
