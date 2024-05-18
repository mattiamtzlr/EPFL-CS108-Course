package T_05_DesignPatterns.Observers;

import java.util.List;

public final class Spreadsheet implements Observer {
    private final List<Cell> cells;
    @Override
    public void update(Subject s) {
        Cell c = (Cell) s;
        System.out.printf("New value of %s: %d%n", c, c.value());
    }

    public Spreadsheet() {
        ValueCell A1 = new ValueCell("A1"), A2 = new ValueCell("A2");
        SumCell A3 = new SumCell(A1, A2);

        ValueCell B1 = new ValueCell("B1"), B2 = new ValueCell("B2");
        SumCell B3 = new SumCell(B1, B2);

        ValueCell C1 = new ValueCell("C1"), C2 = new ValueCell("C2");
        SumCell C3 = new SumCell(C1, C2);

        this.cells = List.of(
                A1, A2, A3,
                B1, B2, B3,
                C1, C2, C3
        );

        this.cells.forEach(c -> c.addObserver(this));

        A1.setValue(5);
        A2.setValue(3);
        B1.setValue(8);
        B2.setValue(1);
        C1.setValue(9);
        C2.setValue(7);
    }

    @Override
    public String toString() {
        return STR."Spreadsheet{cells=\{cells}\{'}'}";
    }
}
