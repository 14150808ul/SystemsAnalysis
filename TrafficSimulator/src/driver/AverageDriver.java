package driver;

public class AverageDriver implements Behavior {

    public int changeLaneDuration = 8000;

    public int getPreferredDistance(){
        return 180;
    }

    public double getPreferredSpeed(){
        return 3;
    }

    public boolean likesToChangeLane(){
        return true;
    }
}