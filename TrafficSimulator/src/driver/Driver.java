package driver;
import vehicle.Vehicle;

public class Driver
{
    private Vehicle vehicle;
    private Behavior behavior; //this is an interface or abstract class not sure yet
    private double current_velocity;
    private int x;
    private int y;
    
    public Driver(Vehicle vehicle, Behavior behavior, int x, int y)
    {
        this.vehicle = vehicle;
        this.behavior = behavior;
        this.x = x;
        this.y = y;
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

	public double getCurrentVelocity() {
		return current_velocity;
	}

	public void setCurrentVelocity(double current_velocity) {
		if(current_velocity < this.vehicle.getMax_speed())
			this.current_velocity = current_velocity;
	}

	public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    //can change its behavior at runtime maybe? shouldn't br able to get it I think
    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }
}
