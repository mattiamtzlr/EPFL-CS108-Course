package T_10_ListImplementation;

import java.util.Iterator;

public class Main {
    /* ================================= List implementations ======================================

        To optimally use the different implementations of collections (specifically lists) the
        following highlights implementation details of the most common list types.

        To do this, the most common java implementations of lists - ArrayList and LinkedList -
        are rewritten in a simple manner, based on the interface SimpleList<E>.
        This interface is implemented by the class SimpleAbstractList which is then extended by
        SimpleArrayList and SimpleLinkedList.



        ArrayList ----------------------------------------------------------------------------------
            The elements of an ArrayList are stored in an array whose size is updated when needed.

            Its strength is an access complexity of O(1) for an element of known index, its
            weakness is an insertion complexity of O(n) to insert an element at an arbitrary
            index. The complexity of inserting an element at the end, is O(1).


            See the class SimpleArrayList to see how it is implemented.



        LinkedList ---------------------------------------------------------------------------------
            The elements of a LinkedList are represented by inter-chained nodes which reference
            the elements themselves. 'inter-chained' means that each node contains a reference to
            at least one of its 'neighbors'.

            If each node contains only one reference (usually to its successor) the list is
            called singly-linked, if it contains references to both neighbors, it's called a
            doubly-liked list. Finally, if the last node and the first node are neighbors (and
            are thus linked), the list is called circular.

            A LinkedList always has access complexity of O(n) but insertion complexity is O(1) as
            no elements need to be moved around. This however only holds, if we assume that there
            is already a reference to a node next to the one we want to insert.


            The class SimpleLinkedList represents such a singly-linked (!) LinkedList.
            It also contains the internal class Node<E> representing the inter-chained nodes.

     */

    public static void main(String[] args) {
        System.out.println("======== Array List ========");

        SimpleArrayList<String> fruits = new SimpleArrayList<>();
        fruits.add("Apples");
        fruits.add("Bananas");
        fruits.add("Pears");
        fruits.add("Tomato");
        System.out.println(fruits);

        fruits.add(2, "Grapes");
        System.out.println(fruits);

        assert !fruits.isEmpty();

        fruits.remove(3);
        System.out.println(fruits);

        assert fruits.contains("Bananas");
        System.out.println(fruits.get(0));


        SimpleArrayList<Integer> numbers = new SimpleArrayList<>();
        for (int i = 0; i <= 20; i++) {
            if (i != 15)
                numbers.add(i);
        }

        System.out.println(STR."\n\{numbers}");

        numbers.add(15, 15); // trigger resizing from 20 to 40
        System.out.println(numbers);


        System.out.println("\n\n======== Linked List ========");

        SimpleLinkedList<String> shapes = new SimpleLinkedList<>();
        shapes.add("circles");
        shapes.add("rectangles");
        shapes.add("squares");
        System.out.println(shapes);

        shapes.add(1, "parallelograms");
        System.out.println(shapes);

        assert !shapes.isEmpty();

        shapes.remove(3);
        System.out.println(shapes);

        assert shapes.contains("parallelograms");
        System.out.println(shapes.get(0));

        Iterator<String> shapesIt = shapes.iterator();
        while (shapesIt.hasNext()) {
            System.out.println(shapesIt.next());
        }
    }
}
