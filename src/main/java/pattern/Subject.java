package pattern;

import driver.*;
import java.util.ArrayList;
public interface Subject
{
	ArrayList<Observer> observersList = new ArrayList<>();
	void notifyObservers();
	void attach(Observer observer);
	void remove(Observer observer);
}