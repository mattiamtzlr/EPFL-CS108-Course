package T_04_Streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class Main {

    /*
        Streams ------------------------------------------------------------------------------------
            A stream is a sequence of elements supporting sequential and parallel aggregate
            operations.

            There are three specialisations of Stream, each more efficient in there respective type:
                - DoubleStream
                - LongStream
                - IntStream

        Stream Methods -----------------------------------------------------------------------------
            Static methods:
                Stream<T> empty()
                    empty stream (duh)

                Stream<T> of(T... vs)
                    stream containing the given values

                Stream<T> iterate(T i, UnaryOperator<T> f)
                    infinite stream containing successive applications of the operator f to the
                    initial value i, the first value of the stream is thus i, the second f(i), the
                    third f(f(i)) and so on.

                    See below for an example.

                IntStream range(int f, int t)
                    range of integers between f (inclusive) and t (exclusive), ascending

                IntStream rangeClosed(int f, int t)
                    range of integers between f (inclusive) and t (inclusive), ascending


            Stream.Builder:
                Stream also offers a builder which has a method add() to add more elements to the
                stream and a method build() to obtain the stream once enough elements have been
                added.


            Methods called on streams are separated into three categories:
                - Source Methods:
                  Generate a stream based on a source, for example stream()

                - Intermediate Methods:
                  Transform the values of the stream and return a new stream:
                    - limit(long l)
                        limits the size of the stream to the given value

                    - skip(long n)
                        returns the same stream with the remaining elements after having skipped
                        the first n elements

                    - sorted()
                        sorts the elements following their natural order

                    - sorted(Comparator<T> c)
                        sorts the elements using the given comparator

                    - filter(Predicate<T> p)
                        removes all elements from the stream that do not satisfy the predicate p

                    - map(Function<T,R> f)
                        returns the stream obtained by applying the function f to all the elements
                        of the stream. This is also really useful for doing type conversion

                - Terminal Methods:
                  Terminate the stream and convert it to another type or object:
                    - count()
                        returns the number of elements of the current stream

                    - max(Comparator<T> c)
                        (Optional) returns the greatest element using the comparator c

                    - min(Comparator<T> c)
                        (Optional) returns the least element using the comparator c

                    - average()
                        computes the average (only for Int- or DoubleStream)

                    - allMatch(Predicate<T> p)
                        returns true if all elements satisfy the predicate p

                    - anyMatch(Predicate<T> p)
                        returns true if at least one element satisfies the predicate p

                    - noneMatch(Predicate<T> p)
                        returns true if no elements satisfy the predicate p

                    - forEach(Consumer<T> c)
                        applies the consumer c to each element in an arbitrary order

                    - forEachOrdered(Consumer<T> c)
                        same as forEach but in the given order

                    - Optional<T> reduce(BinaryOperator<T> o)
                        returns the value obtained by successively applying the given operator o to
                        all the values of the stream (in any order).

                    - T reduce(T z, BinaryOperator<T> o)
                        returns the value obtained by successively applying the given operator o to
                        the initial value z and then to all the values of the stream.

                        See below for an example

                    - collect(Collector<? super T, A, R> collector)
                        collects all elements using the given collector which could for example be:
                            - Collectors.toList()
                            - Collectors.toSet()
                            - Collectors.toMap(Function <T, K> k, Function<T, V> v)
                                uses the function k to get the key and the function v to get its
                                value

                            - Collectors.joining(String d, String p, String s)
                                returns a string joined similarly as with StringJoiner, where d is
                                the delimiter, p is the prefix and s is the suffix

                                See below for an example


            These methods are used to create pipelines, which consist of a source method, zero or
            more intermediate methods and a terminal method. The temp conversion below is a
            pipeline.

     */

    // example of the reduce method used in a method to calculate the factorial
    public static int factorial(int x) {
        return IntStream.rangeClosed(2, x)
                // multiply all integers between 2 and x together
                .reduce(1, (a, b) -> a * b);
    }

    public static void main(String[] args) {
        List<String> tempF = List.of("0", "40", "-40", "", "100");

        List<String> tempC = tempF.stream()
                .filter((l -> !l.isEmpty()))                // remove empty entries
                .mapToDouble(Double::parseDouble)           // parse all entries as doubles
                .map(f -> (f - 32d) * (5d / 9d))            // calculate celsius equivalent
                .mapToObj(f -> String.format("%.3f", f))    // convert to string and round
                .toList();                                  // collect all entries in a list

        System.out.println(tempC);


        // example of the iterate method generating powers of two up until the 10th power of two
        List<Integer> powersOfTwo = Stream.iterate(1, x -> 2 * x)
                .limit(10)
                .toList();

        System.out.println(powersOfTwo);


        // example of the factorial method being used
        System.out.println(factorial(5));


        // example of collect(Collectors::joining)
        String fruits = Stream.of("apples", "bananas", "grapes", "oranges", "strawberries")
                .collect(Collectors.joining(
                        ", ",
                        "My fruits are: ",
                        "."
                ));
        System.out.println(fruits);
    }
}
