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

	public StraightRoad(){}
	
	public StraightRoad(StatsSubject statistics){
		super(statistics);
		collision = new Collision(driver_list, statistics);
	}
	
	@Override
	public void updateVehicles() {
		ArrayList<Driver> driverList = getDriver_list(); 
		int size = driverList.size();
		statistics.setNumber_of_cars( size );
		if( size > 0 ) {
			// Don't use Iterator - > unexcepted Error.
			for(int i = 0; i < size; i++){
				try {
					Driver eachDriver = driverList.get(i);
					eachDriver.drive( 	Sense.getDistanceFromCarInFront(eachDriver, driver_list) ,
										Sense.canChangeLane(eachDriver, driver_list, eachDriver.getOvertakingGap() ));
					collision.checkForCollision();	 
				} catch (Exception e) {
					//e.printStackTrace();
				}	
			}
		} 
	}

	
}
