package driver;
import road.Road;
import vehicle.Vehicle;

public class Driver  {
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
	public double all = 0; // test
	protected int changeLaneDuration = 1000	/*millisecond*/;
	protected int responseTime = 10;            //When the collision occured, the time to react/brake.
	//	& when the car in front of it begin to drive, the response time to start off.
	
	protected double overtakingProbability = 1.0;


	public Driver(Vehicle vehicle, Behavior behavior, int x, int y, double velocity, int startlane) {
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
			double max_speed = this.vehicle.getMax_speed();
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
		double max_acc = this.vehicle.getMax_acceleration();
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
		vehicle.setMax_speed(0);
		crashed = true;
	}

	public boolean isCrashed(){
		return crashed;
	}
	
	public void drive(int distance_from_car_in_front) {
		double deltaX = tsf_Util.Formula.getDeltaDisplacement(this);
		int carPosX = (int) (x + deltaX);
		if (carPosX < 1000)
			setX((int) (getX() + deltaX));
		else
			setX(-95);

		double deltaVelocity = 0;
		if (velocity >= vehicle.getMax_speed() || velocity >= behavior.getPreferredSpeed()) {
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
		setY(  this.y +  deltaY );  

		if(this.isChangingLane){ 
			setDuration_AfterChangeLane(getDuration_AfterChangeLane() + globalContract.TimeControl.TIME_UNIT);
		} 
		if (distance_from_car_in_front > behavior.getPreferredDistance() || distance_from_car_in_front == -1) {
			setAcceleration(behavior.getPreferredAcc());
		}
		else {
			setAcceleration(behavior.getPreferredDcc());
			if(behavior.likesToChangeLane()){
				changeLane();
			}
		}
	}
 
}
