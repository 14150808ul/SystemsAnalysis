package driver;

public class OldDriver implements Behavior {

    public int getPreferredDistance(){
        return 100;
    }

    public double getPreferredSpeed(){
        return 2;
    }

    public boolean likesToChangeLane(){
        return false;
    }
}
