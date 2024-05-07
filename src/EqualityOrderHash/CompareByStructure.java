package EqualityOrderHash;

import java.util.Objects;

public class CompareByStructure {
    public record Integer(int value) {
        @Override
            public boolean equals(Object obj) {
                if (obj instanceof Integer that)
                    return this.value == that.value;
                else
                    return false;
            }
        }

    public record String(java.lang.String value) {
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof String that)
                return Objects.equals(this.value, that.value);
            else
                return false;
        }
    }
}
