package T_05_DesignPatterns.Observers;

import java.util.HashSet;
import java.util.Set;

public abstract class Cell implements Subject {
    private final Set<Observer> observers = new HashSet<>();
    abstract public int value();

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    protected void notifyObservers() {
        for (Observer o : this.observers) {
            o.update(this);
        }
    }
}
