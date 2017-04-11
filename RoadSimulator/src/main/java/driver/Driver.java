package driver; 

import gui.TWindow; 
import pattern.Observer;
import pattern.Subject;
import road.Road;
import vehicle.Vehicle;

public class Driver implements Subject
{
	private Vehicle vehicle;
	private Behavior behavior;
	private double velocity;
	private double acceleration;
	private int x;
	private double y;
	private boolean crashed;
	private int start_lane;
	private int endLane;
	private double duration_AfterChangeLane = 0;
	private boolean isChangingLane = false;
	private double velocity_changeLane = 0;

	public Driver(Road road, Vehicle vehicle, Behavior behavior, int x, int y, double velocity, int start_lane)
	{
		this.vehicle = vehicle;
		this.behavior = behavior;
		this.x = x;
		this.y = y;
		this.velocity = velocity;
		this.start_lane = start_lane;
		this.endLane = ( start_lane == Road.leftLane ? Road.rightLane : Road.leftLane);
		this.duration_AfterChangeLane = 0;
		this.crashed = false;
		attach(road);
	}
	
	private void changeLane() {
		this.isChangingLane = true ;
	}

	private void stopChangeLane(){
		this.isChangingLane = false;
		this.duration_AfterChangeLane = 0;
		this.velocity_changeLane = 0;
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

	private void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return  (int) Math.round(y); // 2.3 return 2; while 2.5 return 3 -> without this cannot change lane.
	}

	private void setY(double y) {
		this.y = y;
	}

	public static int getDurationLaneChange() {
		return 1000;
	}

	private void setVelocity(double velocity)
	{
		if (velocity > 0)
		{
			double max_speed = this.vehicle.getMaxSpeed();

			if (velocity < max_speed) this.velocity = velocity;

			else this.velocity = max_speed;
		}
	}

	public double getVelocity() {
		return velocity;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public double getAcceleration() {
		return this.acceleration;
	}

	private void setAcceleration(double acceleration)
	{
		double max_acc = this.vehicle.getMaxAcceleration();

		if (acceleration <= max_acc) this.acceleration =  acceleration;

		else this.acceleration = max_acc;
	}

	public double getDuration_AfterChangeLane() {
		return duration_AfterChangeLane;
	}

	public void setDuration_AfterChangeLane(double duration_AfterChangeLane) {
		this.duration_AfterChangeLane = duration_AfterChangeLane;
	}

	public int getStartLane() {
		return start_lane;
	}

	public void setStartLane(int start_lane) {
		this.start_lane = start_lane;
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
	
	public void drive(int distance_from_car_in_front, boolean can_change_lane)
	{
		
		if(!crashed)
		{
			double deltaX = tsf_Util.Formula.getDeltaDisplacement(this);
			double deltaY = tsf_Util.Formula.getDisplacement_LaneChange(this);
			double deltaVelocity = tsf_Util.Formula.getDeltaVelocity(this);
			double y_coordinate = this.y + deltaY;

			if ((int) (x + deltaX) < TWindow.WINDOW_LENGTH) setX((int) (getX() + deltaX));

			else notifyObservers();

			y_coordinate = ( y_coordinate < TWindow.LEFTWANE_Y_Coordinate ) ? TWindow.LEFTWANE_Y_Coordinate : y_coordinate;
			y_coordinate = ( y_coordinate > TWindow.RIGHTWANE_Y_Coordinate ) ? TWindow.RIGHTWANE_Y_Coordinate : y_coordinate;
			setY( y_coordinate );

			if(velocity > 0 && velocity >= vehicle.getMaxSpeed() || velocity >= behavior.getPreferredSpeed())
				setAcceleration(behavior.getPreferredDcc());

			else setAcceleration(behavior.getPreferredAcc());

			setVelocity(velocity + deltaVelocity);

			if (this.isChangingLane)
				setDuration_AfterChangeLane(getDuration_AfterChangeLane() + globalContract.TimeControl.TIME_UNIT);


			if (distance_from_car_in_front > behavior.getPreferredDistance() || distance_from_car_in_front == -1)
				setAcceleration(behavior.getPreferredAcc());

			else
			{
				if(tsf_Util.Formula.getNeededDcc(velocity, distance_from_car_in_front) < this.behavior.getPreferredDcc())
					setAcceleration(behavior.getPreferredDcc());

				else
				{
					if(this.velocity == 0 ) changeLane();

					else
					{
						if(Math.random() >0.5)
						{
							setAcceleration(-1*tsf_Util.Formula.getNeededDcc(velocity, distance_from_car_in_front));

							if(distance_from_car_in_front < Vehicle.LENGTH + Vehicle.MARGIN)
							{
								this.velocity = 0;
								this.acceleration = 0;
							}
						}
						else
						{
							setAcceleration(this.behavior.getPreferredAcc());

							if (behavior.likesToChangeLane() && can_change_lane && can_change_lane) changeLane();

							else stopChangeLane();
						}
					}
				}

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
