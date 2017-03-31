package driver;


public class YoungDriver implements Behavior {

    public int getPreferredDistance(){
        return 95;
    }

    public double getPreferredSpeed(){
        return 10;
    }

    public boolean likesToChangeLane(){
        return true;
    }
}
