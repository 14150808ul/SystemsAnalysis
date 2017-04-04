package road;

/** Question: How to dispaly Vehicle and Road on the map?
 * */

import java.awt.Image;
import java.util.ArrayList;

import collision.Collision;
import driver.AverageDriver;
import driver.Driver;
import driver.DriverFactory;
import pattern.Observer;
import pattern.Subject;
import statistics.StatsSubject;
import vehicle.Car;
import vehicle.Vehicle; 

public abstract class Road implements Observer{
	protected ArrayList<Driver> driver_list ;
	public static final double width = 60; // pix.
	
	public static final int leftLane = 0;
	public static final int rightLane = 1;
	private static int timeCounter = 0;
	

	protected StatsSubject statistics;
	protected Collision collision;
	
	
	public abstract void updateVehicles();
	
	public static int getTimeCounter() {
		return timeCounter;
	}

	public static void setTimeCounter(int timeCounter) {
		Road.timeCounter = timeCounter;
	} 

	public Road() {
		super();
		this.driver_list = new ArrayList<Driver>();
	}
	
	//DriverFactory !!!
	public void generateDriver(){ 
		addDriver(new DriverFactory().createDriver(this)); 
	}
	
	public void addDriver(Driver driver){
		this.driver_list.add(driver);
	}
	
	public  void removeDriver(int driverIndex){
		this.driver_list.remove(driverIndex);
	}

	public  void removeDriver(Driver driver){
		this.driver_list.remove(driver);
	}
	
	public ArrayList<Driver> getDriver_list() {
		return driver_list;
	}

	public void setDriver_list(ArrayList<Driver> driver_list) {
		this.driver_list = driver_list;
	}

	
	@Override
	public void update(Subject subject) {
		Driver driver = null;
		if(subject instanceof Driver){
			driver = (Driver) subject;
			try{
				this.removeDriver(driver);
				subject.remove(this);
			}
			catch (Exception e){
				//e.printStackTrace();
		    	//System.out.println(driver_list.size());
			}
		}
	}
	
}

