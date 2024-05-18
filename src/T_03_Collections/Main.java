package T_03_Collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        List<Integer> l = new ArrayList<>(Arrays.asList(a));

        List<Integer> subL = l.subList(1, 5);

        subL.remove(1); // this will remove the element from l and subL

        printList1(l);
        printList1(subL);

        subL.set(2, 13); // this will modify l and subL

        printList2(l);
        printList2(subL);
    }

    public static void printList1(List<Integer> l) {
        Iterator<Integer> it = l.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }

    public static void printList2(List<Integer> l) {
        for (Integer i : l) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
