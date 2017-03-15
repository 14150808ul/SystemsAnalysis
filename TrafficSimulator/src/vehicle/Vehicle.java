package vehicle;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;

//Generic vehicle at the moment, later on make it abstract
public class Vehicle
{
    private double max_speed; //max speed
    private double acceleration; //change of speed;
    private double velocity; //current speed and direction
    private static Image car_image;

    public Vehicle(double max_speed, double acceleration, double velocity /*, Image car_image*/)
    {
        this.max_speed = max_speed;
        this.acceleration = acceleration;
        this.velocity = velocity;
    }


    //max speed of car
    public double getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(double max_speed) {
        this.max_speed = max_speed;
    }

    // increase or decrease speed
    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getVelocity() {
        return velocity;
    }

    ///TODO: Attention the current speed should be check if it reach the max speed.
    public void setVelocity(double velocity) {
    	if(velocity < max_speed)
    		this.velocity = velocity;
    	else
    		this.velocity = max_speed;
    }

    public Image getCar_image() {
        return car_image;
    }


	public double getAcceleration() {
		return acceleration;
	}
    
}
