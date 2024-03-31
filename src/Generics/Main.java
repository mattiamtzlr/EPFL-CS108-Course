package Generics;

import Immutability.Date;

public class Main {
    public static void main(String[] args) {

        /*
            Generic classes ------------------------------------------------------------------------
                Generics allow the use of multiple different types where usually a specific type is
                needed, for example for attributes of a class.

                A generic class needs to define a type parameter to be used within the class and its
                methods:

                    public class Generic<E> {
                        private final E element;
                        ...

                        public Generic(E element) {
                            this.element = element;
                        }
                    }

                In the above code, the class has the type parameter E and instances of the class can
                now be created with a specific type using:

                    Generic<Integer> number = new Generic<>(5);
                    Generic<String> string = new Generic<>("Hello");

                This is great as generics do not require type casting and invoke compiler errors if
                the specific type and actual value are mismatched.

                A class can have many type parameters as the class Pair illustrates.


            Generic Methods ------------------------------------------------------------------------
                A method may also define type parameters and can thus become generic

                    public class Normal {
                        ...

                        // the type parameter is always written before the return type
                        public static <T> void genericMethod(T thing) {
                            ...
                        }
                    }

                When called, the specific type should strictly speaking be declared:
                    Normal.<Integer>genericMethod(5);

                This is usually redundant however, as these types can be inferred from the argument:
                    Normal.genericMethod(5);


            Bounded type parameters ----------------------------------------------------------------
                A type parameter (of a class or a method) can also be bounded to only extend a
                certain other class to prevent errors:

                    public <N extends Number> double inverse(N number) {
                        return 1d / number.doubleValue();
                    }

                The above method returns the inverse of all Number subclasses thus all primitive
                number types.


            Limitations of generics ----------------------------------------------------------------
                The following are not allowed:
                    - Creating new arrays with generic types:
                        T[] things = new T[]{...}; // not allowed

                    - Instance tests using instance of are not possible:
                        public <T> generic(T thing) {
                            if (t instanceof Integer) {...} // not allowed
                        }

                    - Type casting is strongly discouraged

                    - Generic exceptions are not possible, meaning a class defining an exception
                      (by extending Exception) cannot have a type parameter.
         */

        Date d = new Date.Builder().setYear(2024).setMonth(1).setDay(1).build();
        Cell<String> message = new Cell<>("Happy new year");
        Cell<Date> date = new Cell<>(d);

        System.out.println(message.verboseString());
        System.out.println(date.verboseString());

        System.out.println("Year: " + date.get().getYear());

        // ================================================================= pair shenanigans
        Pair<String, Date> pair = new Pair<>("Happy new year ", d);
        System.out.println(pair.first() + pair.second().getYear());

        Cell<String> message2 = new Cell<>("Happy new year ");
        Pair<String, Date> pair2 = message2.pairWith(d);
        System.out.println(pair2.first() + pair2.second().getYear());

        Pair<String, Boolean> one = new Pair<>("one", true);
        Pair<String, Boolean> zero = new Pair<>("zero", false);
        System.out.println(one.verboseString());
        System.out.println(zero.verboseString());
    }
}
