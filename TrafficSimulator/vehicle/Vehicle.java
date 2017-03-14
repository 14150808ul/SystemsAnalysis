package vehicle;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;

//Generic vehicle at the moment, later on make it abstract
public class Vehicle
{
    private float max_speed; //max speed
    private float acceleration; //change of speed;
    private float velocity; //current speed and direction
    private static Image car_image;

    //this block will only get executed once, maybe not a good idea for inheritance??
    static
    {
        try
        {
            car_image = ImageIO.read(new File(("/src/resources/car.png")));
        }
        catch (Exception e)
        {
            System.out.println("Temp here sh-maybe?");
        }

    }

    public Vehicle(float max_speed, float acceleration, float velocity, Image car_image)
    {
        this.max_speed = max_speed;
        this.acceleration = acceleration;
        this.velocity = velocity;
    }


    //max speed of car
    public float getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(float max_speed) {
        this.max_speed = max_speed;
    }

    // increase or decrease speed
    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public Image getCar_image() {
        return car_image;
    }
}
