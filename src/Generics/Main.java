package Generics;

import Immutability.Date;

public class Main {
    public static void main(String[] args) {
        Date d = new Date.Builder().setYear(2024).setMonth(1).setDay(1).build();
        Cell<String> message = new Cell<>("Happy new year");
        Cell<Date> date = new Cell<>(d);

        System.out.println(message.verbosePrint());
        System.out.println(date.verbosePrint());

        System.out.println("Year: " + date.get().getYear());

        // ================================================================= pair shenanigans
        Pair<String, Date> pair = new Pair<>("Happy new year ", d);
        System.out.println(pair.first() + pair.second().getYear());

        Cell<String> message2 = new Cell<>("Happy new year ");
        Pair<String, Date> pair2 = message2.pairWith(d);
        System.out.println(pair2.first() + pair2.second().getYear());
    }
}
