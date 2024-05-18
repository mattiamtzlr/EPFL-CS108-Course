package T_01_Immutability;

import java.util.ArrayList;
import java.util.List;

public class Lists {
    public static void main(String[] args) {
        List<Thing> things = new ArrayList<>(
                List.of(new Thing(1), new Thing(2), new Thing(3), new Thing(4))
        );

        List<Thing> newList = new ArrayList<>(things);
        List<Thing> copy = new ArrayList<>(List.copyOf(things));

        System.out.println(things);
        System.out.println(newList);
        System.out.println(copy);

        things.removeFirst();
        System.out.println("Removed first element from things.");

        System.out.println(things);
        System.out.println(newList);
        System.out.println(copy);

        things.getFirst().setA(5);
        System.out.println("Set a in first element of things to 5.");

        System.out.println(things);
        System.out.println(newList);
        System.out.println(copy);
    }
}

class Thing {
    private int a;

    public Thing(int a) {
        this.a = a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return String.valueOf(this.a);
    }
}
