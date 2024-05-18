package T_04_Lambdas;

/* The below annotation is not mandatory but useful, as it produces an error, if the interface that
 * it annotates isn't actually functional, which is to say, it has more than one abstract method. */
@FunctionalInterface
public interface RealFunction {
    double valueAt(double x);
}
