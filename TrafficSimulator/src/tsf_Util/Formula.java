package tsf_Util;
import globalContract.* ;
import road.Road; 
import driver.Driver;

public class Formula {

	// This just used to describe  uniformly accelerated rectilinear motion in X Coordinate
	public static double getDeltaDisplacement(Driver eachDriver) {
		double a = eachDriver.getAcceleration();
		double v = eachDriver.getVelocity();
		double t = ScaleControl.TIME_ScaleRatio;
		double dispalcement = a * t * t / 2 + v * t;
		if(dispalcement < 0)
			return 0;
		return dispalcement;
	}

	public static double getDeltaVelocity(Driver eachDriver) {
		return eachDriver.getAcceleration() * (ScaleControl.TIME_ScaleRatio);
	}
	
	public static double getDeltaVelocity_Decrease(Driver eachDriver) {
		return -1 * eachDriver.getAcceleration() * (ScaleControl.TIME_ScaleRatio);
	}
	
	public static double getDisplacement_LaneChange( Driver driver ){
		if( !driver.isChangingLane() ){
			return 0;
		}
		double width_LaneChange =  Road.width * 4; 
		double dt_afterCL = driver.getDuration_AfterChangeLane();
		int laneEnd = driver.getEndLane();	 
		int laneStart = driver.getStartLane();
		double T = driver.getDurationLaneChange(); 
		double 	acc_ChangeLane = 0;
		int direction  = laneEnd - laneStart;
		if(dt_afterCL < 0.5 * T){
			acc_ChangeLane = 4*width_LaneChange/(T * T);
		}
		//first half way should acc, last half way should decc.
		else if(dt_afterCL < T){
			acc_ChangeLane = -4*width_LaneChange/(T * T);
		}
		else{			
			driver.setDuration_AfterChangeLane(0);
			driver.setStartLane(laneEnd);
			driver.setEndLane(laneStart);
			driver.setChangingLane(false);	
		}
		double dt =   globalContract.TimeControl.TIME_UNIT /* globalContract.ScaleControl.TIME_ScaleRatio */;//why Time 5 I also do not know...
		double v_LC = driver.getVelocity_changeLane();
		double deltaDisplacement_LaneChange = acc_ChangeLane*dt*dt/2 + v_LC * globalContract.TimeControl.TIME_UNIT;
		driver.setVelocity_changeLane(  (dt_afterCL >= T) ?  0 : acc_ChangeLane + v_LC );
		return  deltaDisplacement_LaneChange * direction;
	}
	
	public static double getNeededDcc(double volecity , double distance_from_car_in_front){

		//System.out.println(1.5 * (volecity * volecity ) / distance_from_car_in_front);;
		return 1.5 * (volecity * volecity ) / distance_from_car_in_front  ;
	}

}
