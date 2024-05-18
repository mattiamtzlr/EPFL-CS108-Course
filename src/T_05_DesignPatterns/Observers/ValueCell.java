package T_05_DesignPatterns.Observers;

import static java.lang.StringTemplate.STR;

public final class ValueCell extends Cell {
    private final String name;
    private int value = 0;

    public ValueCell(String name) {
        this.name = name;
    }

    @Override
    public int value() {
        return value;
    }

    public void setValue(int value) {
        if (value != this.value) {
            this.value = value;
            this.notifyObservers();
        }
    }

    @Override
    public String toString() {
        return STR."\{this.name}";
    }
}
