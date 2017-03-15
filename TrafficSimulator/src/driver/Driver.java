package driver;
import vehicle.Vehicle;

public class Driver
{
    private Vehicle vehicle;
    private Behavior behavior; //this is an interface or abstract class not sure yet
    private int x_pos;
    private int y_pos;
    //private double currentVolecity;
    
    public Driver(Vehicle vehicle, Behavior behavior, int x_pos, int y_pos)
    {
        this.vehicle = vehicle;
        this.behavior = behavior;
        this.x_pos = x_pos;
        this.y_pos = y_pos;
    }

    public int getX() {
		return x_pos;
	}

	public void setX_pos(int x_pos) {
		this.x_pos = x_pos;
	}

	public int getY() {
		return y_pos;
	}

	public void setY_pos(int y_pos) {
		this.y_pos = y_pos;
	}

//	public double getCurrentVolecity() {
//		return currentVolecity;
//	}
//
//	public void setCurrentVolecity(double currentVolecity) {
//		if(currentVolecity < this.vehicle.getMax_speed() )
//			this.currentVolecity = currentVolecity;
//	}

	public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    //can change its behavior at runtime maybe? shouldn't br able to get it I think
    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }
 
}
