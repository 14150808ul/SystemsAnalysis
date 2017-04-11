package statistics;
import java.util.ArrayList;

public class Statistics implements StatsSubject{
    private int number_of_crashes;
    private int number_of_cars;
    private long start_time;

    private ArrayList<StatsObserver> observers;

    public Statistics(){
        observers = new ArrayList<>();
        number_of_crashes = 0;
        number_of_cars = 0;

        start_time = System.currentTimeMillis();
    }

    @Override
    public void attach(StatsObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for( int i = 0; i < observers.size(); i++ ){
            observers.get(i).update();
        }
    }

    public int getNumber_of_crashes(){

        return number_of_crashes;
    }

    public void setNumber_of_crashes(int num){
        number_of_crashes = num;
        notifyObservers();
    }

    public int getNumber_of_cars(){
        return number_of_cars;
    }
    
    @Override
    public void setNumber_of_cars(int num){
        number_of_cars = num;
        notifyObservers();
    }

    public long getSimulationTime(){
        return (System.currentTimeMillis() - start_time) / 1000;
    }
}
