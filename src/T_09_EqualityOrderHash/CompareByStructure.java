package T_09_EqualityOrderHash;

import java.util.Objects;

public class CompareByStructure {
    public record Integer(int value) {
        @Override
        public boolean equals(Object obj) {
            return obj instanceof Integer that
                && this.value == that.value;
        }
    }

    public record String(java.lang.String value) {
        @Override
        public boolean equals(Object obj) {
            return obj instanceof String that
                && Objects.equals(this.value, that.value);
        }
    }
}
