package Immutability;

public class Main {
    public static void main(String[] args) {
        Date d = new Date(1903, 12, 28);
        Person j = new Person("John", d);

        Date b = j.birthdate().withYear(1969);
        Person l = new Person("Linus", b);

        System.out.println(j);
        System.out.println(l);
    }
}