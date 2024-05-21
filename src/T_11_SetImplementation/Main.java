package T_11_SetImplementation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    /* ================================== Set implementations ======================================

        This topic shows how sets (specifically HashSet) are implemented in Java using simplified
        versions of the involved classes.


        ListSet ------------------------------------------------------------------------------------
            This is a very simple and inefficient (thus not used in Java) implementation of sets
            using a list as the underlying structure.

            The implementation in SimpleListSet uses a SimpleLinkedList to contain the elements
            and checks for double elements when adding a new element to the set.



        HashSet ------------------------------------------------------------------------------------
            HashSets usually have a complexity of O(1) for the most common operations which makes
            them very valuable.

            The general idea is the following:
                Instead of using one long ass list to store n elements, use +- n lists each of length
                1 to store the elements and put them into a hash table. This allows for lookup times
                of O(1) using the element's hash to figure out which small list it belongs to. This
                list then only contains very few elements which can be manually search through very
                quickly.

                If the hashing function has a complexity of O(1), the operations of HashSet will
                also have a complexity of O(1).


            The implementation in SimpleHashSet uses an array of SimpleLinkedLists to represent
            the hash table whose length is called the capacity of the set.

            The ratio of the number of elements in a hash table and its capacity is called the
            load factor of the hash table which should be close to 1.

            The order of elements in hash sets is random.

     */

    public static void main(String[] args) throws IOException {
        SimpleHashSet<String> words = new SimpleHashSet<>();
        assert words.isEmpty();

        String lorem = Files.readString(Path.of("files/lorem.txt"));
        for (String word : lorem.split(" ")) {
            words.add(word);
        }

        System.out.println(words);
        words.printHashTable();
    }
}
