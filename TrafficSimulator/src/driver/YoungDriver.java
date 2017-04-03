package driver;


public class YoungDriver implements Behavior {

    public int getPreferredDistance(){
        return 125;
    }

    public double getPreferredSpeed(){
        return 10;
    }

    public boolean likesToChangeLane(){
        return true;
    }

	@Override
	public double getPreferredAcc() {
		return 0.48;
	}

	@Override
	public double getPreferredDcc() {
		return -0.5;
	}

    @Override
    public int getOvertakingGap() {
        return 100;
    }
}
