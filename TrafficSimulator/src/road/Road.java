package road;

/** Question: How to dispaly Vehicle and Road on the map?
 * */

import java.awt.Image;
import java.util.ArrayList;

import driver.Driver; 

public abstract class Road {
	protected int laneNum;	// if(laneNum = 2) the inner lane = 1, the outer lane is 0;
	protected static int roadDistance;
	protected ArrayList<Driver> driver_list ;
	public static final double width = 60; // pix.
	
	public static final int leftLane = 0;
	public static final int rightLane = 1;
	public abstract void updateVehicles();
	
	
	// rfc
	public Road(int laneNum) {
		super();
		this.laneNum = laneNum;
		this.driver_list = new ArrayList<Driver>();
	}

	public Road() {
		super();
		this.driver_list = new ArrayList<Driver>();
	}
	
	public void addDriver(Driver driver){
		this.driver_list.add(driver);
	}
	
	public ArrayList<Driver> getDriver_list() {
		return driver_list;
	}

	public void setDriver_list(ArrayList<Driver> driver_list) {
		this.driver_list = driver_list;
	}

	public int getLaneNum() {
		return laneNum;
	}
	
	public void setLaneNum(int laneNum) {
		this.laneNum = laneNum;
	}
	
	public static int getRoadDistance() {
		return roadDistance;
	}
	
	public static void setRoadDistance(int roadDistance) {
		Road.roadDistance = roadDistance;
	}
	
}

