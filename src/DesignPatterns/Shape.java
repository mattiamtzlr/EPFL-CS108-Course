package DesignPatterns;

import java.util.AbstractList;
import java.util.List;

public interface Shape {
    boolean contains(Point p);

    /*
        Design Pattern: Decorators -----------------------------------------------------------------
            Split subclasses of a general class (Component) into concrete implementations
            (ConcreteComponent) and subclasses which only modify or change the behavior of the
            instance (Decorator).

            An example is the below class Translated.
            Another example is the relationship between List<E> and UnmodifiableList<E> of java.util


        Design Pattern: Composites -----------------------------------------------------------------
            Subclasses that are used to perform actions on multiple instances or return a
            composition of multiple instances are referred to as composites.

            An example is the below class Group.


        Design Pattern: Adapter --------------------------------------------------------------------
            Helper class to allow instances of one type to be processed by methods usually expecting
            another type.

            An example is the below class ArrayAdapter<E>, which allows normal arrays to be passed
            to Collections.shuffle as seen in Main class.
     */

    // Circle is a concrete implementation of Shape
    final class Circle implements Shape {
        private final Point pos;
        private final double radius;
        public Circle(Point pos, double radius) {
            this.pos = pos;
            this.radius = radius;
        }
        @Override
        public boolean contains(Point p) {
            return
                    (pos.x() + radius - Math.abs(p.x()) > 0) &&
                    (pos.y() + radius - Math.abs(p.y()) > 0);
        }
    }

    // Translated is seen as a decorator
    final class Translated implements Shape {
        private final Shape shape;
        private final double dx, dy;
        public Translated(Shape shape, double dx, double dy) {
            this.shape = shape;
            this.dx = dx;
            this.dy = dy;
        }
        public boolean contains(Point p) {
            return shape.contains(new Point(p.x() - dx, p.y() - dy));
        }
    }

    // Group is seen as a composite
    final class Group implements Shape {
        private final List<Shape> shapes;

        public Group(List<Shape> shapes) {
            this.shapes = shapes;
        }

        public boolean contains(Point p) {
            for (Shape s : shapes)
                if (s.contains(p))
                    return true;
            return false;
        }
    }

    // ArrayAdapter is seen as an adapter
    final class ArrayAdapter<E> extends AbstractList<E> {
        private final E[] array;

        public ArrayAdapter(E[] array) {
            this.array = array;
        }

        @Override
        public E get(int index) {
            return array[index];
        }

        @Override
        public E set(int index, E newValue) {
            E oldValue = array[index];
            array[index] = newValue;
            return oldValue;
        }

        @Override
        public int size() {
            return array.length;
        }
    }
}
