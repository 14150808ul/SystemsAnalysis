package road;

import java.awt.Image;

//import java.awt.Image;

public abstract class Lane{
	public static final int width = 40; // meters.
	protected Image laneImage;
	protected int laneDistance;

	public int getLaneDistance() {
		return laneDistance;
	}
	public void setLaneDistance(int laneDistance) {
		this.laneDistance = laneDistance;
	}
	public Image getLaneImage() {
		return laneImage;
	}
	public void setLaneImage(Image laneImage) {
		this.laneImage = laneImage;
	}
}
