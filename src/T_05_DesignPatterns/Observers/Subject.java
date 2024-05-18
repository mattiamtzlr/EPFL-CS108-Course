package T_05_DesignPatterns.Observers;

public interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
}
