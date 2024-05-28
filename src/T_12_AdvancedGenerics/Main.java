package T_12_AdvancedGenerics;

import java.util.ArrayList;
import java.util.List;

public class Main {
    /* =================================== Advanced Generics =======================================

        This topics delves deeper into generics and their usages. These further topics are lower
        bounds for type parameters and wildcards.


        Subtypes -----------------------------------------------------------------------------------
            If for example a class inherits from another class, that first class' type is a
            subtype of that of the second class. Also, if a class implements an interface, that
            class' type is a subtype of the interface.
            All types are subtypes of the Object type.

            Subtypes allow polymorphism, which allows to substitute a value of type T1 by one of
            type T2, as long as T2 is a subtype of T1.


            In the following, the notation T1 < T2 is used to declare T1 as a subtype of T2.

            The relation of subtypes is
                - reflexive:        each type is a subtype of itself
                - transitive:       T1 < T2 and T2 < T3 implies T1 < T3
                - antisymmetric:    T1 < T2 and T2 < T1 implies T1 = T2

            which means that subtyping is a partial order.


            If T1 is a subtype of T2, then T2 is a supertype of T1, denoted by T2 > T1.



        Subtypes and Generics ----------------------------------------------------------------------
            In Java, generic instances of a generic type are never subtypes of each other. For
            example the type List<U> is never a subtype of List<V>, even if U < V. The only
            exception to this is the trivial case U = V.

            This makes it impossible, for example, to use the method addAll on a list of type
            List<Number> with an argument of type List<Integer> even though Integer < Number.

            The reason for this, is that it would allow adding a double to an integer list, if
            this restriction wouldn't exist.


            One method to get around this issue is using the upper bound for type parameters:

                <F extends E> void addAll(List<F> other);

            This forces the passed list to have a type which is a subtype of the type of the list
            to be added to.


            As the type parameter F isn't used further in the code, o make this notion more
            concise, a wildcard may be used:

                void addAll(List<? extends E> other);


            A wildcard may also be used without an upper bound, which then just sets the upper
            bound to the Object type:

                void addAll(List<?> other);

            This is however not often desired.



        Lower bounds -------------------------------------------------------------------------------
            To create a lower bound for type parameters, which is to say, specify a type which is
            a supertype (!) of some other type.

            This could for example be useful to create the method addAllInto specified below,
            which takes two lists and adds all elements of the first into the second.
            It uses a lower bound for the type parameter of the second list to ensure that its
            abstraction level is not lower than that of the first list.

            If this lower bound wouldn't be in place, it would for example be possible to add
            doubles to a list of ints.


            Generally, lower bounds are specified like this:

                <F super E>

            Or if the type F isn't actually used anymore:

                <? super E>



        When to use what ---------------------------------------------------------------------------
            If a structure is read-only, use an upper bound:        <F extends E>
            If a structure is write-only, use a lower bound:        <F super E>
            If a structure is read/write, use no bound.

     */

    private static <E> void addAllInto(List<E> l1, List<? super E> l2) {
        l2.addAll(l1);
    }

    public static void main(String[] args) {
        List<Number> numbers = new ArrayList<>();
        numbers.add(1.57); numbers.add(4); numbers.add(-3);
        System.out.println(STR."numbers: \{numbers}");

        List<Double> doubles = new ArrayList<>();
        doubles.add(5.61); doubles.add(-2.31); doubles.add(3.14);
        System.out.println(STR."doubles: \{doubles}");

        addAllInto(doubles, numbers);
        System.out.println(STR."numbers: \{numbers}");
    }
}
