package driver;
import vehicle.Vehicle;

public class Driver
{
    private Vehicle vehicle;
    private Behavior behavior; //this is an interface or abstract class not sure yet
    private float x_pos;
    private float y_pos;

    public Driver(Vehicle vehicle, Behavior behavior, float x_pos, float y_pos)
    {
        this.vehicle = vehicle;
        this.behavior = behavior;
        this.x_pos = x_pos;
        this.y_pos = y_pos;
    }

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

    public float getX() {
        return x_pos;
    }

    public void setX(float x_pos) {
        this.x_pos = x_pos;
    }

    public float getY() {
        return y_pos;
    }

    public void setY(float y_pos) {
        this.y_pos = y_pos;
    }
}
