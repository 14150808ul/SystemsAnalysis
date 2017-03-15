package pattern;

import driver.*;
import java.util.ArrayList;
public interface Subject {

	ArrayList< Observer >observersList = new ArrayList< Observer >();
    public abstract ArrayList< Driver > getDriverAll();
	public abstract void notifyObservers( );
	public abstract void attach(Observer observer);
	public abstract void start();  // to control timer 'thread' ( i dnuny if it is a thread or not... actully).
}