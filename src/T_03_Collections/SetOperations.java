package T_03_Collections;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {
    public static Set<Integer> s1 = new HashSet<>(Set.of(1, 2, 3, 4, 5));
    public static Set<Integer> s2 = new HashSet<>(Set.of(4, 5, 6, 7, 8));

    public static void main(String[] args) {
        { // Intersection

            Set<Integer> s1copy = newS1Copy();
            s1copy.retainAll(s2);
            System.out.println(s1copy);
        }

        { // Union

            Set<Integer> s1copy = newS1Copy();
            s1copy.addAll(s2);
            System.out.println(s1copy);
        }

        { // Difference

            Set<Integer> s1copy = newS1Copy();
            s1copy.removeAll(s2);
            System.out.println(s1copy);
        }
    }

    public static Set<Integer> newS1Copy() {
        return new HashSet<>(Set.copyOf(s1));
    }
}
