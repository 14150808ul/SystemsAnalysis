//Robert O'Driscoll ---- 14150808
//CS4125 - Systems Analysis & Design
//Traffic Simulator
//SubjectMock.java
//Used for testing purposes
package gui;
import java.util.ArrayList;

public class SubjectMock {

    private ArrayList<ObserverMock> observers = new ArrayList<ObserverMock>();
    private int car_position;

    public int getCarPosition() {
        return car_position;
    }

    public void start() throws Exception{
        car_position = -100;
        while(true) {
            if(car_position > 800) {
                car_position = -100;
            } else {
                car_position += 20;
            }

            notifyAllObservers();
            Thread.sleep(30);
        }
    }

    public void attach(ObserverMock observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (ObserverMock observer : observers) {
            observer.update();
        }
    }
}
