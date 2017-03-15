package tsf_Util;
import globalContract.* ;
import vehicle.Vehicle;
import driver.Driver;


public class Formula {

	// This just used to describe  uniformly accelerated rectilinear motion 
	public static final double getDeltaDisplacement(Driver eachDriver) {
		double a = eachDriver.getVehicle().getAcceleration();
		double v = eachDriver.getVehicle().getVelocity();
		double t = ScaleControl.TIME_ScaleRatio;
		double dispalcement = a * t * t / 2 + v * t;
		return dispalcement;
	} 

	public static final double getDeltaVolecity(Driver eachDriver) {
		return eachDriver.getVehicle().getAcceleration() * (ScaleControl.TIME_ScaleRatio);
	}
	
	public static final double getDeltaVolecity_Decrease(Driver eachDriver) {
		return -1 * eachDriver.getVehicle().getAcceleration() * (ScaleControl.TIME_ScaleRatio);
	}
}
