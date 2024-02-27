package Immutability;

public class Date {
    private final int year, month, day;

    private Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date withYear(int year) {
        return new Date(year, this.month, this.day);
    }

    @Override
    public String toString() {
        return String.format("%d.%d.%d", day, month, year);
    }

    public static final class Builder {
        private int year, month, day;

        public Builder(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public Builder() {
            this(0, 0, 0);
        }

        public int getYear() {
            return year;
        }

        // return a DateBuilder to allow chaining (see Main)
        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public int getMonth() {
            return month;
        }

        // return a DateBuilder to allow chaining (see Main)
        public Builder setMonth(int month) {
            this.month = month;
            return this;
        }

        public int getDay() {
            return day;
        }

        // return a DateBuilder to allow chaining (see Main)
        public Builder setDay(int day) {
            this.day = day;
            return this;
        }

        public Date build() {
            return new Date(this.year, this.month, this.day);
        }
    }
}
