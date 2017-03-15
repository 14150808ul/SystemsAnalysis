package road;
 
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StraightLane extends Lane{

	public StraightLane(){
		laneDistance = 1000;
		try {	laneImage = ImageIO.read(new File("resource/road.png"));	} catch (IOException e) {e.printStackTrace();}
	}
	
	public StraightLane(int laneDistance){
		this.laneDistance = laneDistance;
		try {	laneImage = ImageIO.read(new File("resource/road.png"));	} catch (IOException e) {e.printStackTrace();}
	}
	
	
}
