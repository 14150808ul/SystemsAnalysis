package vehicle;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Truck extends Vehicle{

	public Truck(double max_speed, double acceleration, double velocity) {
		super(max_speed, acceleration, velocity);
		// TODO Auto-generated constructor stub
	}
//	public Truck() {
//		 try {
//			vehicle_Img = ImageIO.read(new File("resource/truck.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
