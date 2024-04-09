package DesignPatterns.Observers;

public interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
}
