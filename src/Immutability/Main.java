package Immutability;

public class Main {
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