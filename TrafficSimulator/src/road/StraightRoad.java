package road;
 
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;

import driver.Driver;
import sense.Sense;

public class StraightRoad extends Road{

	public StraightRoad(){
		roadDistance = 1000;
		try {	roadImage = ImageIO.read(new File("resource/road.png"));	} catch (IOException e) {e.printStackTrace();}
	}
	
	public StraightRoad(int laneDistance){
		this.roadDistance = laneDistance;
		try {	roadImage = ImageIO.read(new File("resource/road.png"));	} catch (IOException e) {e.printStackTrace();}
	}

	@Override
	public void updateVehicles() {
		// TODO Auto-generated method stub
		//update each driver 
		for(Iterator<Driver> iterator = driver_list.iterator();
			iterator.hasNext(); 
				) {
			Driver eachDriver = iterator.next();
//change car's position
				//change x coordinate
				double deltaX = tsf_Util.Formula.getDeltaDisplacement(eachDriver);
				int carPosX = (int) (eachDriver.getX() + deltaX);
				if (carPosX < 1000)
					eachDriver.setX((int) (eachDriver.getX() + deltaX));
				else
					eachDriver.setX(-300);
				//System.out.println((int)(  deltaX));
				//change car's volecity
				double volecity = eachDriver.getVelocity();

				double deltaVolecity = 0;
				if (volecity >= eachDriver.getVehicle().getMax_speed()) {
					double acc = eachDriver.getAcceleration();
					if (acc > 0)
						eachDriver.setAcceleration(eachDriver.getAcceleration() * -1);
				}
				if (volecity <= -2) {
					eachDriver.setAcceleration(0);
				}
				deltaVolecity = tsf_Util.Formula.getDeltaVolecity(eachDriver);
				eachDriver.setVelocity(volecity + deltaVolecity);
				// !!NOTICE: The Car's position should be updated firstly.

				//change y coordinate
				double deltaY = tsf_Util.Formula.getDisplacement_LaneChange(eachDriver);
				eachDriver.setY((int) (eachDriver.getY() + deltaY));


				eachDriver.setDuration_AfterChangeLane(eachDriver.getDuration_AfterChangeLane() + globalContract.TimeControl.TIME_UNIT);

				//System.out.println(Sense.getDistanceFromCarInFront(eachDriver, driver_list));
				if (Sense.getDistanceFromCarInFront(eachDriver, driver_list) > 200 || Sense.getDistanceFromCarInFront(eachDriver, driver_list) == -1) {
					eachDriver.setAcceleration(.02);
				}
				else if (Sense.getDistanceFromCarInFront(eachDriver, driver_list) < 100) {
					eachDriver.setVelocity(0);
				}
				else if (eachDriver.getVelocity() > 0) {
					eachDriver.setAcceleration(-.01);
				}

			}
		}
	
}
