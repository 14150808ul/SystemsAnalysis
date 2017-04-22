package pattern;

import java.util.ArrayList;

import driver.Driver;

public interface MapSubject extends Subject {
	void start();  // to control timer 'thread' ( i dnuny if it is a thread or not... actully).
    ArrayList<Driver> getDriverAll();
}
