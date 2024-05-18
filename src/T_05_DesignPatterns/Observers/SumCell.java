package T_05_DesignPatterns.Observers;

public final class SumCell extends Cell implements Observer {
    private final Cell c1, c2;
    private int sum = 0;

    public SumCell(Cell c1, Cell c2) {
        this.c1 = c1;
        this.c2 = c2;
        c1.addObserver(this);
        c2.addObserver(this);
    }

    @Override
    public void update(Subject s) {
        int newSum = c1.value() + c2.value();
        if (newSum != sum) {
            this.sum = newSum;
            this.notifyObservers();
        }
    }

    @Override
    public int value() {
        return sum;
    }

    @Override
    public String toString() {
        return STR."\{this.sum}";
    }
}
