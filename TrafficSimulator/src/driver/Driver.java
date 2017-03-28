package driver;
import jdk.nashorn.internal.scripts.JO;
import vehicle.Vehicle;

import javax.swing.*;

public class Driver
{
    private Vehicle vehicle;
    private Behavior behavior; 	//this is an interface or abstract class not sure yet
    private double velocity; 	//current speed and direction
    private double acceleration;//current acceleration
    private int x;
    private int y;
    
    
    private int startLane = 0;			// important atttribute to change lane
    private int endLane = 1 ;
    private double duration_AfterChangeLane = 0;//############!!!!!!!!!!!!!!!!!!!!
    
    private boolean isChangingLane = false;
    public double laneChangeAll = 0;//used to tested for tsf_Util.formula
    private double velocity_changeLane = 0;
    protected int changeLaneDuration = 1000	/*millisecond*/ ;
	protected int responseTime = 10;			//When the collision occured, the time to react/brake.
												//	& when the car in front of it begain to drive, the response time to start off.
	protected int accelerationGradient = 1;   //boy rider's G(acc) bigger than the elder's G(acc): G(acc) is the rate of change of the acceleration.(acceleration is the rate of change of the volecity)
	protected double overtakingProbability = 1.0;
	
    
    
    public Driver(Vehicle vehicle, Behavior behavior, int x, int y, double velocity, int lane) {
        this.vehicle = vehicle;
        this.behavior = behavior;
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        //this.startLane = this.endLane = lane;
        this.duration_AfterChangeLane = 0;
    }

    public void changeLane(){
    	this.setChangingLane(true);
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
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDurationLaneChange(){
		return this.changeLaneDuration;
	}
	
	public void setVelocity(double velocity) {
		if(velocity > 0) {
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

    public double getAcceleration(){
    	return this.acceleration;
    }
    
    public void setAcceleration(double acceleration){
    	this.acceleration = acceleration;
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

	public void crashed(){
    	velocity = 0;
    	acceleration = 0;
		vehicle.setMax_speed(0);
	}
}
