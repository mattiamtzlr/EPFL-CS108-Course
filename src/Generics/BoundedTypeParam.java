package Generics;

import java.util.List;

public class BoundedTypeParam<T extends List<String>> {
    // the type parameter of this class, T, is bounded by Lists of type String, meaning instances
    // of this class can only be made with a List<String> type.
}
