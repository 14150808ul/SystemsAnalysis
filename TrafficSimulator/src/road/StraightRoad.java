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

public class StraightRoad extends Road{

	public StraightRoad(){
		roadDistance = 1000;
	}
	
	public StraightRoad(int laneDistance){
		roadDistance = laneDistance;
	}

	@Override
	public void updateVehicles() {
		// TODO Auto-generated method stub
		//update each driver
		Collision collision = new Collision(driver_list);
		for(Iterator<Driver> iterator = driver_list.iterator();
			iterator.hasNext(); 
				) {
				Driver eachDriver = iterator.next();
				eachDriver.drive( Sense.getDistanceFromCarInFront(eachDriver, driver_list) );
				collision.checkForCollision();
			}
		}

	
}
