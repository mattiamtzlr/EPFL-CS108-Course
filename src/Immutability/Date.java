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

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Date that
                && this.year == that.year
                && this.month == that.month
                && this.day == that.day;
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

        // return a DateBuilder to allow chaining (see Main)
        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        // return a DateBuilder to allow chaining (see Main)
        public Builder setMonth(int month) {
            this.month = month;
            return this;
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
