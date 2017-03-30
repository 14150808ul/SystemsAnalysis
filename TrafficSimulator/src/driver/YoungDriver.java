package driver;


public class YoungDriver implements Behavior {
	
	public int changeLaneDuration = 1000;

    public int getPreferredDistance(){
        return 95;
    }

    public double getPreferredSpeed(){
        return 10;
    }

}
