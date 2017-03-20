package tsf_Util;
import globalContract.* ;
import road.Road;
import vehicle.Vehicle;
import driver.Driver;


public class Formula {

	// This just used to describe  uniformly accelerated rectilinear motion in X Coordinate
	public static double getDeltaDisplacement(Driver eachDriver) {
		double a = eachDriver.getAcceleration();
		double v = eachDriver.getVelocity();
		double t = ScaleControl.TIME_ScaleRatio;
		double dispalcement = a * t * t / 2 + v * t;
		return dispalcement;
	} 

	public static double getDeltaVolecity(Driver eachDriver) {
		return eachDriver.getAcceleration() * (ScaleControl.TIME_ScaleRatio);
	}
	
	public static double getDeltaVolecity_Decrease(Driver eachDriver) {
		return -1 * eachDriver.getAcceleration() * (ScaleControl.TIME_ScaleRatio);
	}
	
	public static double getDisplacement_LaneChange( Driver driver ){
		if( !driver.isChangingLane() ){
			return 0;
		}
		double width_LaneChange =  Road.width ; 
		double dt_afterCL = driver.getDuration_AfterChangeLane();
		int laneEnd = driver.getEndLane();	 
		int laneStart = driver.getStartLane();
		double T = driver.getDurationLaneChange(); 
		double 	acc_ChangeLane = laneEnd - laneStart;
		
		if(dt_afterCL < 0.5 * T){
			acc_ChangeLane *= (4*width_LaneChange/(T * T));
		}
		else if(dt_afterCL < T){
			acc_ChangeLane *= -4*width_LaneChange/(T * T);
		}
		else{
			acc_ChangeLane = 0;
			driver.setDuration_AfterChangeLane(0);
			driver.setStartLane(laneEnd);
			driver.setChangingLane(false);
		}
		//duration_AfterChangeLane = 0;
		double dt = globalContract.TimeControl.TIME_UNIT  * globalContract.TimeControl.TIME_UNIT /* globalContract.ScaleControl.TIME_ScaleRatio */;//why Time 5 I also do not know...
		//(dt_afterCL < 0.5 * T) ? dt_afterCL : T - dt_afterCL;
		double v_LC = driver.getVelocity_changeLane();
		//first half way should acc, last half way should decc.
		double deltaDisplacement_LaneChange = acc_ChangeLane*dt*dt/2 + v_LC * dt;
		driver.setVelocity_changeLane(  (dt_afterCL > T) ?  0 : acc_ChangeLane + v_LC );
		//double deltaDisplacement_LaneChange = (dt_afterCL < 0.5*T) ? acc_ChangeLane*dt*dt/2 : width_LaneChange/4-acc_ChangeLane*dt*dt/2;
		//System.out.println((dt_afterCL > T) ? 0 /*laneEnd*/ : /*laneStart +*/ deltaDisplacement_LaneChange *(laneEnd-laneStart) );
		driver.laneChangeAll += deltaDisplacement_LaneChange;

		return deltaDisplacement_LaneChange;
		//																	     				 determind direction
		//return (dt_afterCL > T) ? 0 /*laneEnd*/ : /*laneStart +*/ deltaDisplacement_LaneChange *(laneEnd-laneStart);
	}

}
