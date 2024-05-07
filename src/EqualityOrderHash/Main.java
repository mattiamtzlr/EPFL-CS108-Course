package EqualityOrderHash;


import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    /* ======================================= Equality ============================================

        There are two general viewpoints when it comes to equality:
            1. equality by reference (or by identity), which considers two objects equal when
               they are one and the same object.

            2. equality by structure, which considers two objects equal when their values (or
               their content) is identical.

        If two objects are equal by reference they are always equal by structure but the converse
        doesn't hold.


        Example:
            Integer i1 = new Integer(5);
            Integer i2 = new Integer(5);

        i1 and i2 are equal by structure but not by reference;


        In Java, equality by reference is predefined by the operator == as well as by the method
        'equals' defined on Object.

        Structural equality is however not predefined and classes that need this functionality
        need to redefine the 'equals' method.


        When an immutable class is defined, it should redefine the method 'equals' to enable
        instances to be compared by structure as their reference equality doesn't matter. This
        shouldn't be done for non-immutable classes.
     */


    /* ======================================= Ordering ============================================

        Again, there are two general points of view when it comes to ordering instances of a class:
            1. let the class implement the Comparable interface and thus offers a method to
               compare a given instance to another.

            2. by using an external comparator implementing the Comparator interface (as a lambda
               for example) which knows how to compare two instances of that class.



        Interface Comparable -----------------------------------------------------------------------
            The Comparable<T> interface contains a single abstract method 'compareTo' and is thus
            functional:
                - int compareTo(T that)
                  returns a negative value if this < that
                            positive value if this > that
                            zero           if this = that


            As Comparable is generic, each class implementing it has to pass its own type as the
            type parameter T to Comparable.
            Thus, also the T in compareTo(T that) needs to be replaced.


            When you write a class implementing Comparable, make sure its 'compareTo' method is
            compatible with its 'equals' method, which is achieved if and only if they consider
            exactly the same pairs of two values equal.

            Thus, for each pair of instances o1 and o2, we have:
                o1.equals(o2)   <=>   o1.compareTo(o2) == 0


            The order resulting from the use of the compareTo method is called the natural order
            of that class.



        Interface Comparator -----------------------------------------------------------------------
            The Comparator<T> interface contains a single abstract method 'compare' and is thus
            functional:
                - int compare(T o1, T o2)
                  returns a negative value if o1 < o2
                            positive value if o1 > o2
                            zero           if o1 = o2


            The main difference between Comparable and Comparator is that, Comparable is
            implemented by the class whose instances are to be compared, while Comparator is
            implemented by another class (or lambda) used to then compare two instances of
            another class.
            From this it follows, that a class implementing Comparator should pass the type of
            the objects it wants to compare to the type parameter T of Comparator.



        TreeSet ------------------------------------------------------------------------------------


     */

    public static void main(String[] args) {
        // these are equal as they are compared by structure
        CompareByStructure.Integer i1 = new CompareByStructure.Integer(5);
        CompareByStructure.Integer i2 = new CompareByStructure.Integer(5);

        System.out.println(STR."i1 equals i2: \{i1.equals(i2)}");

        // these are not equal as they are compared by structure
        CompareByStructure.String s1 = new CompareByStructure.String("Hello");
        CompareByStructure.String s2 = new CompareByStructure.String("World");

        System.out.println(STR."s1 equals s2: \{s1.equals(s2)}");
    }
}
