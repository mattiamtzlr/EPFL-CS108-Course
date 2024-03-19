package Lambdas;

import Collections.Maps;

import java.util.*;

public class Sorter {

    /*
        Functional Interface -----------------------------------------------------------------------
            An interface is said to be functional if it contains exactly one abstract method.
            It may contain as many concrete static or default methods as desired.

            Comparator is for example a functional interface, which is why we can pass a lambda
            function to replace a comparator.
            Its only abstract method is compare, which is why there is no ambiguity as to which
            method the lambda actually implements.

            Another example would be the class RealFunction in the same package as this.


        Lambda functions ---------------------------------------------------------------------------
            A lambda function creates an anonymous class of the given functional interface, with
            the given arguments and body, then calls the (only !) abstract method on it.

            Lambdas can only be used with functional interfaces, for the reasons described above.

            Lambda functions always have as many arguments as the abstract function that they call.
            The arguments can have explicit types, this is optional however as they are inferred
            by java. Also, if there is only one argument, the parentheses may be omitted:

                (Int x) -> x * x      or      (x) -> x * x      or      x -> x * x

            If the body has multiple expressions it needs an explicit return statement:

               Comparator<String> c = (s1, s2) -> {
                  int lc = Integer.compare(s1.length(), s2.length());
                  return lc != 0 ? lc : s1.compareTo(s2);
                };

            A useful use case of lambda functions is Iterable::forEach, which applies the given
            method or lambda function to each element of the given list. See below.


        Method References --------------------------------------------------------------------------
            To reference a static method (example):
                Integer::sum        equivalent:     (a, b) -> a + b

            To reference a constructor (example):
                ArrayList::new      equivalent:     () -> new ArrayList<>();

            To reference a non-static method (example):
                Either:
                    String::compareTo       equivalent:     (s1, s2) -> s1.compareTo(s2)
                     - first argument is the one that the method is called on
                Or:
                    "hello"::charAt         equivalent:     i -> "hello".charAt(i)

            All of these have to be assigned to a valid type of course, for example
                Comparator, Supplier, BiFunction or Function

     */

    public static void sortDescending(List<Integer> l) {
        // takes arguments i1, i2 and passes them to Integer::compare but in reversed order.
        l.sort((i1, i2) -> Integer.compare(i2, i1));
    }

    public static void printValueAt(RealFunction function, int x) {
        System.out.println(function.valueAt(x));
    }

    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>(List.of(34, 18, 58, 23, 95, 87, 72, 46));
        sortDescending(ints);
        System.out.println(ints);

        printValueAt(
                (x) -> x * x        // either pass a lambda function ...
                , 5
        );
        printValueAt(
                Math::sqrt,         // ... or an already existing function
                81
        );

        // use with forEach
        List<Integer> intsPlus1 = new ArrayList<>(ints.size());
        ints.forEach(
                a -> intsPlus1.add(a + 1)
        );
        System.out.println(intsPlus1);

        Maps.charIntMap.forEach(
                (k, v) -> System.out.println(k + ": " + v)
        );

        // useful for counting occurrences
        List<String> words = List.of("to", "be", "or", "not", "to", "be");
        Map<String, Integer> count = new HashMap<>();
        for (String word : words) {
            count.merge(word, 1, (k, v) -> k + 1);
        }
        System.out.println(count);
    }
}
