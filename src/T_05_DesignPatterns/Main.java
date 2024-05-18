package T_05_DesignPatterns;

import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Shape shape1 = new Shape.Translated(
                new Shape.Circle(new Point(0, 2), 5d),
                -1, 4
        );

        Shape shape2 = new Shape.Circle(new Point(-1, 3), 10d);

        Shape group = new Shape.Group(List.of(shape1, shape2));
        System.out.println(group.contains(new Point(-2, 4)));

        String[] array = {"Apples", "Bananas", "Oranges", "Pears", "Strawberries"};
        List<String> adapted = new Shape.ArrayAdapter<>(array);
        Collections.shuffle(adapted);
        System.out.println(adapted);
    }
}
