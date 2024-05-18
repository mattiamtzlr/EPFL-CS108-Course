package T_02_Generics;

public record Pair<F, S>(F first, S second) {
    public String verboseString() {
        return String.format("Pair<%s, %s>: %s, %s",
                first.getClass().getSimpleName(),
                second.getClass().getSimpleName(),
                first, second
        );
    }
}
