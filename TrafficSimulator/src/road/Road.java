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
import sense.Sense;
import statistics.StatsSubject; 
import vehicle.Vehicle; 

public abstract class Road implements Observer{
	
	public static final double width = 60; // pix.
	public static final int leftLane = 0;
	public static final int rightLane = 1;
	private static int timeCounter = 0;
	
	protected ArrayList<Driver> driver_list ;
	protected StatsSubject statistics;
	protected Collision collision;
	
	public abstract void updateVehicles();

	public static boolean isTimeToGeneratorDriver(){ 
		Road.timeCounter = Road.timeCounter + globalContract.TimeControl.TIME_UNIT;
		return (Road.timeCounter >= globalContract.TimeControl.TIME_GENERATE_CAR);
	}

	public Road( ) {
		this.driver_list = new ArrayList<Driver>();
	}
	
	public Road(StatsSubject statistics) {
		this.statistics = statistics;
		this.driver_list = new ArrayList<Driver>();
	}
	
	//DriverFactory !!!
	public void generateDriver(){ 
		if(Math.random() > 0.5){ // two block should execute one either not both.
			if(	Sense.isLaneClear(this.driver_list, Road.leftLane) ) {
				this.addDriver(new DriverFactory().createDriver_withSpecificLane(this, Road.leftLane));
			}
		}
		else{
			if(	Sense.isLaneClear(this.driver_list, Road.rightLane) ) {
				this.addDriver(new DriverFactory().createDriver_withSpecificLane(this, Road.rightLane));
			}
		}
		Road.timeCounter = 0;
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
			catch (Exception e) { }
		}
	}
	
}

