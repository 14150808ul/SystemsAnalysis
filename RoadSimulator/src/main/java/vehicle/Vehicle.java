package vehicle;

public class Vehicle
{
    public static final int WIDTH  = 40;
    public static final int LENGTH = 85;
    public static final int MARGIN = 6;
    private double max_speed;
    private double max_acceleration;
    private int car_image;

    public Vehicle(double max_speed, double max_acceleration, int car_image)
    {
        this.max_speed = max_speed;
        this.max_acceleration = max_acceleration;
        this.car_image = car_image;
    }

    public double getMaxSpeed() {
        return max_speed;
    }

    public int getCarImage() {
        return car_image;
    }

	public double getMaxAcceleration() {
		return max_acceleration ;
	}
}
