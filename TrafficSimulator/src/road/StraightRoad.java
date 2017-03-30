package road;
 
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;

import collision.Collision;
import driver.Driver;
import sense.Sense;

public class StraightRoad extends Road{
	int distance_from_car_in_front = 158;

	public StraightRoad(){
		System.out.print(distance_from_car_in_front);
		roadDistance = 1000;
		try {	roadImage = ImageIO.read(new File("resource/road.png"));	} catch (IOException e) {e.printStackTrace();}
	}
	
	public StraightRoad(int laneDistance){
		System.out.print(distance_from_car_in_front);
		this.roadDistance = laneDistance;
		try {	roadImage = ImageIO.read(new File("resource/road.png"));	} catch (IOException e) {e.printStackTrace();}
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
