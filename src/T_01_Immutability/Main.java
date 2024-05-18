package T_01_Immutability;

public class Main {

    /*
        Immutable classes --------------------------------------------------------------------------
            A class is immutable if:
                - its attributes are final, thus initialised during construction and not modified
                  afterward.

                - every mutable value passed to the constructor is copied before being saved to the
                  instances attributes.

                - no mutable value is ever passed to the outside of the instance, either they are
                  made unmodifiable or only a deep copy is given.

                - the class itself is final, such that it cannot be extended and possibly be
                  rendered mutable.


            To still be able to modify an instance of an immutable class, methods similar to setters
            are provided, they however return a new instance with the wanted modifications.

            Thus:
                public className withAttribute(attributeType value) {
                    return new className(value {, ...});
                }

                instead of:

                public void setAttribute(attributeType value) {
                    this.attributeName = value;
                }

            Java Records are pretty good for this use-case as they and their attributes are already
            final. The only thing that remains is to copy eventual mutable values passed to the
            constructor.

            Another way to guarantee immutability is to use a builder for the class, which is also
            done for the Date class.


        Builders -----------------------------------------------------------------------------------
            An immutable class can have a builder to help construct an immutable instance, if for
            example not all values for the instances attributes are known at the same time.

            A builder usually ressembles the class it builds, and generally has the same attributes
            as said class. The attributes of the builder are however usually modifiable and only
            become no longer so when the instance is built.

            The methods to set the values of the builder instances attributes usually return a (new)
            instance of the builder to be able to chain these methods after one another.

            The builder class is usually nested inside the class it builds and is thus static:

            Example:
                public class Person {
                    private final int age;
                    private final String name;

                    // private on purpose to guarantee immutability through builder
                    private Person(int age, String name) {
                        this.age = age;
                        this.name = name;
                    }

                    public static final class Builder {
                        private int age;
                        private String name;

                        public Builder() {
                            this.age = 0;
                            this.name = "";
                        }

                        public Builder setAge(int age) {
                            this.age = age;
                            return this;
                        }

                        public Builder setName(String name) {
                            this.name = name;
                            return this;
                        }

                        public Person build() {
                            return new Person(this.age, this.name);
                        }
                    }
                }

                public class Main {
                    public static void main(String[] args) {
                        Person p = new Person.Builder()
                                        .setAge(19)
                                        .setName("Mattia")
                                        .build();
                    }
                }
     */

    public static void main(String[] args) {
        Date d = new Date.Builder()
                .setYear(1903)
                .setMonth(12)
                .setDay(28)
                .build();

        Person j = new Person("John", d);

        Date b = j.birthdate().withYear(1969);
        Person l = new Person("Linus", b);

        System.out.println(j);
        System.out.println(l);

        // ================================================= String shenanigans
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(" ").append(i);
        }
        System.out.println(sb);
    }
}