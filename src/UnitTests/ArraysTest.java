package UnitTests;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ArraysTest {
    /*
        Generally:
            @Test
            void function() {
                ...

                assert...(expected, actual);
            }
    */
    @Test
    void minWorksOnNonTrivialArray() {
        double[] a = new double[]{
                1, 2, -12, 20, 40, -1003, -1003.2, 12, 12, -1003.2
        };

        assertEquals(-1003.2, Arrays.min(a));
    }

    @Test
    void minFailsOnEmptyArray() {
        assertThrows(
                NoSuchElementException.class,
                () -> Arrays.min(new double[0])
        );
    }

    @Test
    void precision() {
        // if the absolute difference between expected and actual is smaller than delta, the test
        // still passes.
        assertEquals(3.0, 3.00001, 1e-4);
    }
}
