package driver;
import java.util.ArrayList;

import pattern.MapSubject;
import pattern.Observer;
import pattern.Subject;
import road.Road;
import vehicle.Vehicle;

public class Driver implements Subject  {
	private Vehicle vehicle;
	private Behavior behavior;    //this is an interface or abstract class not sure yet
	private double velocity;    //current speed and direction
	private double acceleration;//current acceleration
	private int x;
	private double y;	//this attribute must be double -> for it is the very little change of lane change displacement.

	private boolean crashed; //robert crash

	private int startLane ; // important attribute to change lane
	private int endLane ; 
	private double duration_AfterChangeLane = 0;

	private boolean isChangingLane = false;
	private double velocity_changeLane = 0;
	protected int changeLaneDuration = 780	/*millisecond*/;
	
	protected double overtakingProbability = 1.0;

	public Driver(Road road, Vehicle vehicle, Behavior behavior, int x, int y, double velocity, int startlane) {
		this.vehicle = vehicle;
		this.behavior = behavior;
		this.x = x;
		this.y = y;
		this.velocity = velocity;
		this.startLane = startlane;
		this.endLane = ( startlane == Road.leftLane ? Road.rightLane : Road.leftLane);
		this.isChangingLane = false;
		this.duration_AfterChangeLane = 0;
		this.crashed = false;
		attach(road);
	}
	
	public void changeLane() {
		this.isChangingLane = true ;
	}

	public boolean isChangingLane() {
		return isChangingLane;
	}

	public void setChangingLane(boolean isChangingLane) {
		this.isChangingLane = isChangingLane;
	}

	public double getVelocity_changeLane() {
		return velocity_changeLane;
	}

	public void setVelocity_changeLane(double velocity_changeLane) {
		this.velocity_changeLane = velocity_changeLane;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return  (int) Math.round(y); // 2.3 return 2; while 2.5 return 3 -> without this cannot change lane.
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getDurationLaneChange() {
		return this.changeLaneDuration;
	}

	public void setVelocity(double velocity) {
		if (velocity > 0) {
			double max_speed = this.vehicle.getMaxSpeed();
			if (velocity < max_speed)
				this.velocity = velocity;
			else
				this.velocity = max_speed;
		}
	}

	public double getVelocity() {
		return velocity;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public double getAcceleration() {
		return this.acceleration;
	}

	public void setAcceleration(double acceleration) {
		double max_acc = this.vehicle.getMaxAcceleration();
		if (acceleration <= max_acc)
			this.acceleration =  acceleration;
		else
			this.acceleration = max_acc;
	}

	//can change its behavior at runtime maybe? shouldn't br able to get it I think
	public void setBehavior(Behavior behavior) {
		this.behavior = behavior;
	}

	public double getDuration_AfterChangeLane() {
		return duration_AfterChangeLane;
	}

	public void setDuration_AfterChangeLane(double duration_AfterChangeLane) {
		this.duration_AfterChangeLane = duration_AfterChangeLane;
	}

	public int getStartLane() {
		return startLane;
	}

	public void setStartLane(int startLane) {
		this.startLane = startLane;
	}

	public int getEndLane() {
		return endLane;
	}

	public void setEndLane(int endLane) {
		this.endLane = endLane;
	}

	public void crashed() {
		velocity = 0;
		acceleration = 0;
		crashed = true;
	}

	public boolean isCrashed(){
		return crashed;
	}
	
	public void drive(int distance_from_car_in_front, boolean can_change_lane) {
		
		if(crashed) return;
		
		double deltaX = tsf_Util.Formula.getDeltaDisplacement(this);
		int carPosX = (int) (x + deltaX);
		if (carPosX < globalContract.ScaleControl.WINDOW_WIDTH )
			setX((int) (getX() + deltaX));
		else
			notifyObservers();

		double deltaVelocity = 0;
		if (velocity >= vehicle.getMaxSpeed() || velocity >= behavior.getPreferredSpeed()) {
			double acc = getAcceleration();
			if (acc > 0)
				setAcceleration(getAcceleration() * -1);
		}
		deltaVelocity = tsf_Util.Formula.getDeltaVolecity(this);
		setVelocity(velocity + deltaVelocity);
		// !!NOTICE: The Car's position should be updated firstly.

		//change y coordinate
		double deltaY = tsf_Util.Formula.getDisplacement_LaneChange(this);
		//here must use this.y , cannot use getY(). otherwise cannot change lane.
		setY(this.y + deltaY);

		if (this.isChangingLane) {
			setDuration_AfterChangeLane(getDuration_AfterChangeLane() + globalContract.TimeControl.TIME_UNIT);
		}
		if (distance_from_car_in_front > behavior.getPreferredDistance() || distance_from_car_in_front == -1) {
			setAcceleration(behavior.getPreferredAcc());
		} else {
			setAcceleration(behavior.getPreferredDcc());
			if (behavior.likesToChangeLane() && can_change_lane) {
				changeLane();
			}
		}
		
	}

	public int getOvertakingGap() {
		return behavior.getOvertakingGap();
	}


	@Override
	public void notifyObservers() {
		for(Observer observer: observersList){
			observer.update(this);
		}
	}

	@Override
	public void attach(Observer observer) {
		observersList.add(observer);
	}

	@Override
	public void remove(Observer observer) {
		observersList.remove(observer);
	}
	

 
}
