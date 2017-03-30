package driver;

public class OldDriver implements Behavior {
	
	public int changeLaneDuration = 8000;

    public int getPreferredDistance(){
        return 100;
    }

    public double getPreferredSpeed(){
        return 2;
    }
}
