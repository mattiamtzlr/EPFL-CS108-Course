package T_09_EqualityOrderHash;

import T_01_Immutability.Date;

import java.util.*;

public class Main {
    /* ======================================= Equality ============================================

        There are two general viewpoints when it comes to equality:
            1. equality by reference (i.e. by identity), which considers two objects equal when
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
            1. let the class implement the Comparable interface and thus offer a method to
               compare a given instance to another.

            2. by using an external comparator implementing the Comparator interface (as a lambda
               for example) which knows how to compare two instances of that class.



        Interface Comparable -----------------------------------------------------------------------
            The Comparable<T> interface contains a single abstract method 'compareTo' and is thus
            functional:
                - int compareTo(T that)
                  returns a negative value if this < that
                          a positive value if this > that
                          zero             if this = that


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
                          a positive value if o1 > o2
                          zero             if o1 = o2


            The main difference between Comparable and Comparator is that, Comparable is
            implemented by the class whose instances are to be compared, while Comparator is
            implemented by another class (or lambda) used to then compare two instances of
            another class.
            From this it follows, that a class implementing Comparator should pass the type of
            the objects it wants to compare to the type parameter T of Comparator.



        TreeSet ------------------------------------------------------------------------------------
            Elements in a tree set are ordered by ascending natural order.
            It's also possible to create an instance of TreeSet which sorts its elements using a
            provided comparator, which can be provided at construction time:

                Set<Integer> s = new TreeSet<>((i, j) -> Integer.compare(j, i));
     */



    /* ======================================= Hashing =============================================

        Hashing is the process of transforming given data into an integer, generally bounded by
        an interval, this transformation is done using a hash function, which produces a hash
        value for each given input.

        An easy (although kind of dumb) way to hash text consisting of the 26 latin letters is
        the following:

            h(c) = sum{pos(c_i)} mod 100

        where pos returns the position in the alphabet of each character c_i.



        Hash functions -----------------------------------------------------------------------------
            A hash function is a mathematical function in the sense that it produces the same
            hash for two equal values, the inverse is however rarely true, meaning a hash function
            is often not injective, if it is, it's called a perfect hash function.

            However, by the pigeonhole principle, this is only possible if the number of possible
            inputs is smaller than the number of available hash values.
            Thus, practical hash functions are usually optimised for the values that it
            actually encounters, not all possible values.
            If two inputs produce the same hash value, we speak of a hashing collision.



        Hashing in Java ----------------------------------------------------------------------------
            Java offers the method 'hashCode' defined on the class Object, which returns an int
            representing the hash value of the object.

            If not overridden, the hashCode method returns a hash value based on the objects
            identity (i.e. reference) which *generally* leads to a different hash value for each
            object.


            Compatibility with the 'equals' method:
                If a class overrides the equals method it should also override the hashCode
                method and vice versa, this is to guarantee that the statement

                    x.equals(y)     =>      x.hashCode() == y.hashCode()

                always holds.


            As it can be quite difficult to effectively and correctly write a hash function to
            override hashCode, Java offers the static method 'hash' of the java.util.Objects
            class, which allows to calculate a hash value for an arbitrary combination of objects:

                int hash(Object... values)


            Thus, to override the hashCode method in a class, use the hash method and pass to it
            all objects which need to be considered. This rule has some exceptions, illustrated
            below:

                - A method with only a single attribute may use the hash value of that attribute
                  as its own hash value and does not need to recompute it using the 'hash' method.

                - A method with only a single attribute of an integer type (e.g. int) may
                  directly use that value as its hash value.

            Examples of this are the classes Person, SimpleString and SimpleNumber in the class
            HashCodes in this package.


            The HashSet implementation of sets uses the hash values of its objects to store
            them in memory which allows for lookup times of O(1) (generally).
     */



    /* ========================== Methods needed by common collections =============================

        The following table shows which collections (and their implementations) need which
        methods to be defined on the objects they store, such that the objects may be stored
        correctly:

            |-----------------------------------------|
            | Collection | Required Methods           |
            |-----------------------------------------|
            | List<E>    | none                       |
            | Set<E>     | equals                     |
            | HashSet<E> | equals and hashCode        |
            | TreeSet<E> | equals and compareTo       |
            | TreeSet<E> | equals and compare         |
            |-----------------------------------------|

        Note that TreeSet is shown twice as it can either be defined using the elements natural
        order or a given comparator.
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


        // prints 1 through 8 in order
        Set<Integer> s = new TreeSet<>(Set.of(1, 3, 5, 7, 2, 4, 6, 8));
        StringJoiner sj = new StringJoiner(", ");
        s.forEach(i -> sj.add(String.valueOf(i)));
        System.out.println(sj);


        // using the classes from HashCodes
        Date date = new Date.Builder().setYear(1969).setMonth(12).setDay(28).build();
        HashCodes.Person person = new HashCodes.Person(
            "Linus", "Torvalds", date
        );
        System.out.println(STR."Hash value of 'person': \{person.hashCode()}");

        String str = "Hello, World!";
        HashCodes.SimpleString simpleString = new HashCodes.SimpleString(str);
        System.out.println(STR."Hash value of 'simpleString': \{simpleString.hashCode()}");
        System.out.println(STR."Hash value of 'str': \{str.hashCode()}");

        int num = 72;
        HashCodes.SimpleNumber simpleNumber = new HashCodes.SimpleNumber(num);
        System.out.println(STR."Hash value of 'simpleNumber': \{simpleNumber.hashCode()}");
        System.out.println(STR."Integer value of 'num': \{num}");
    }
}
