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
		// TODO Auto-generated method stub
		//update each driver
		for(Iterator<Driver> iterator = driver_list.iterator();
			iterator.hasNext(); 
				) {
				Driver eachDriver = iterator.next();
				eachDriver.drive( Sense.getDistanceFromCarInFront(eachDriver, driver_list) );
				collision.checkForCollision();
			}
		}

	
}
