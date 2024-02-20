package UnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;


public class ArraysTest {
    @Test
    void minWorksOnNonTrivialArray() {
        double[] a = new double[] {
                1, 2, -12, 20, 40, -1003, -1003.2, 12, 12, -1003.2
        };

        assertEquals(-1003.2, Arrays.min(a));
    }

    @Test
    void minFailsOnEmptyArray() {
        assertThrows(NoSuchElementException.class, () -> {
            Arrays.min(new double[0]);
        });
    }
}
