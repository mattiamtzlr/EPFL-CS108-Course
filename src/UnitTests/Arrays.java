package UnitTests;

public class Arrays {
    public static double min(double[] array) {
        return java.util.Arrays.stream(array).min().getAsDouble();
    }
}
