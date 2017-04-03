package statistics;

public interface StatsSubject {
    void notifyObservers();
    void attach(StatsObserver observer);
    int getNumber_of_crashes();
    void setNumber_of_crashes(int c);
}
