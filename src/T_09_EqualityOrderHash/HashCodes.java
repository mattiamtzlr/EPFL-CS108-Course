package T_09_EqualityOrderHash;

import T_01_Immutability.Date;

import java.util.Objects;

public class HashCodes {
    public record Person(String firstName, String lastName, Date birthDate) {
        @Override
        public boolean equals(Object obj) {
            return obj instanceof Person that
                && this.firstName.equals(that.firstName)
                && this.lastName.equals(that.lastName)
                && this.birthDate.equals(that.birthDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName, birthDate);
        }
    }

    public record SimpleString(String value) {
        @Override
        public boolean equals(Object obj) {
            return obj instanceof SimpleString that
                && this.value.equals(that.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }
    }

    public record SimpleNumber(int value) {
        @Override
        public boolean equals(Object obj) {
            return obj instanceof SimpleNumber that
                && this.value == that.value;
        }

        @Override
        public int hashCode() {
            return value;
        }
    }
}
