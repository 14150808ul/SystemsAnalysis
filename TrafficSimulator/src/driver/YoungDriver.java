package driver;


public class YoungDriver implements Behavior {

    public int getPreferredDistance(){
        return 130;
    }

    public double getPreferredSpeed(){
        return 6;
    }

    public boolean likesToChangeLane(){
        return true;
    }

	@Override
	public double getPreferredAcc() {
		return 0.52;
	}

	@Override
	public double getPreferredDcc() {
		return -1.5;
	}

    @Override
    public int getOvertakingGap() {
        return 100;
    }

	@Override
	public double getSlamBrakeDcc() { 
		return -8.5;
	}
}
