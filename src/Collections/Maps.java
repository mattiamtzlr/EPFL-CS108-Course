package Collections;

import java.util.*;

public class Maps {

    /*
            HashMap  -  guarantees that the keys are hash-able and thus guarantees O(1) lookup (generally)
                        but gives no guarantee of the order of lookup.

            TreeMap  -  guarantees that the keys are comparable, thus guaranteeing increasing lookup order
                        but has O(log n)
     */

    static Map<Character, Integer> charIntMap = new HashMap<>(Map.of(
            'a', 403,
            'b', 810,
            'c', 300,
            'd', 164,
            'e', 760
    ));

    public static void main(String[] args) {
        System.out.println(charIntMap);

        System.out.printf("charIntMap contains the key '%s': %b%n", 'd', charIntMap.containsKey('d'));

        System.out.println(charIntMap.get('c'));
        System.out.println(charIntMap.getOrDefault('f',0));

        charIntMap.put('b', 902);
        charIntMap.putIfAbsent('a', 856);
        charIntMap.putIfAbsent('f', 541);

        System.out.println(charIntMap);

        // check for null for example, there is also computeIfAbsent
        charIntMap.compute('e', (k, v) -> v == null ? 0 : v);
        System.out.println(charIntMap);

        // if 'g' is already assigned, replace it with the output of the function, else assign it the value
        charIntMap.merge('g', 123, (k, v) -> v == null ? 0 : v);
        System.out.println(charIntMap);

        // if the given key maps to the first given value, replace it by the second given value, return
        // true, else don't do anything, return false
        charIntMap.replace('c', 300, 450);
        System.out.println(charIntMap);

        // removes a key-value pair if it has the given value
        charIntMap.remove('a', 403);
        System.out.println(charIntMap);

        System.out.println(charIntMap.keySet());
        System.out.println(charIntMap.values());

        Set<Map.Entry<Character, Integer>> entries = charIntMap.entrySet();
        for (Map.Entry<Character, Integer> entry : entries) {
            System.out.printf("Entry: %c -> %d%n", entry.getKey(), entry.getValue());
        }
    }
}