package vehicle;
import javax.imageio.ImageIO;
import java.awt.Image; 

//Generic vehicle at the moment, later on make it abstract
public class Vehicle
{
    private double max_speed; //max speed
    private double max_acceleration; //change of speed;
    private int car_image;

    public Vehicle(double max_speed, double max_acceleration, int car_image)
    {
        this.max_speed = max_speed;
        this.max_acceleration = max_acceleration;
        this.car_image = car_image;
    }
    
    //max speed of car
    public double getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(double max_speed) {
        this.max_speed = max_speed;
    }

    // increase or decrease speed
    public void setMax_acceleration(double max_acceleration ) {
        this.max_acceleration  = max_acceleration ;
    }

    public int getCar_image() {
        return car_image;
    }


	public double getMax_acceleration() {
		return max_acceleration ;
	}
    
}
