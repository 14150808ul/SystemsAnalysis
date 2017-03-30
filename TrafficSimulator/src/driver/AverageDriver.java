package driver;

public class AverageDriver implements Behavior {

    public int changeLaneDuration = 8000;

    public int getPreferredDistance(){
        return 170;
    }

    public double getPreferredSpeed(){
        return 4;
    }
}