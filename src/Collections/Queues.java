package Collections;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Queues {
    // Double-ended queue, allows polling and offering at both ends
    public static Deque<Integer> deque = new ArrayDeque<>();

    public static void main(String[] args) {
        deque.addAll(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(deque);

        deque.offerFirst(0);
        deque.offerLast(6);
        System.out.println(deque);

        deque.pollFirst();
        deque.pollLast();
        System.out.println(deque);
    }
}
