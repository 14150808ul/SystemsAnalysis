package road;
 
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import collision.Collision;
import driver.Driver;
import pattern.Observer;
import sense.Sense;
import statistics.StatsSubject;

public class StraightRoad extends Road{

	StatsSubject statistics;
	Collision collision;

	public StraightRoad(StatsSubject statistics){
		this.statistics = statistics;
		collision = new Collision(driver_list, statistics);
		roadDistance = 1000;
	}
	
	public StraightRoad(int laneDistance){
		roadDistance = laneDistance;
	}

	@Override
	public void updateVehicles() {
		ArrayList<Driver> driverList = getDriver_list(); 
		int size = driverList.size();
		if( size > 0 ) {
			for(int i = 0; i < size; i++){
				try {
					Driver eachDriver = driverList.get(i);
					eachDriver.drive( Sense.getDistanceFromCarInFront(eachDriver, driver_list) ,
					          Sense.canChangeLane(eachDriver, driver_list, eachDriver.getOvertakingGap() ));
					collision.checkForCollision();	 
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			//Current need.
			// Don't use Iterator - > unexcepted Error.
//			for(Iterator<Driver> iterator = driver_list.iterator();
//					iterator.hasNext(); ) {
//					try{
//						Driver eachDriver = iterator.next();
//						eachDriver.drive( Sense.getDistanceFromCarInFront(eachDriver, driver_list) ,
//								          Sense.canChangeLane(eachDriver, driver_list, eachDriver.getOvertakingGap() ));
//						collision.checkForCollision();	 
//					}
//					catch(Exception e) {System.out.print(e.toString());}
//				}
		} 
	}

	
}
