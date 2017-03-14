package pattern;

import java.util.ArrayList;
public interface Subject {

	ArrayList< Observer >observersList = new ArrayList< Observer >();
    public ArrayList< Driver > getDriverAll();
	public abstract void notifyObservers()();
	public abstract void attach(Observer observer);
}