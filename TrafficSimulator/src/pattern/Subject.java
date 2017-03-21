package pattern;

import driver.*;
import java.util.ArrayList;
public interface Subject {

	ArrayList< Observer >observersList = new ArrayList< Observer >();
    ArrayList< Driver > getDriverAll();
	void notifyObservers();
	void attach(Observer observer);
	void start();  // to control timer 'thread' ( i dnuny if it is a thread or not... actully).
}