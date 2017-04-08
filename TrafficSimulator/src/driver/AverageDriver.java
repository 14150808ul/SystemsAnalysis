package driver;

public class AverageDriver implements Behavior {

    public int getPreferredDistance(){
        return 200;
    }

    public double getPreferredSpeed(){
        return 4;
    }

    public boolean likesToChangeLane(){
        return true;
    }

	@Override
	public double getPreferredAcc() {
		return 0.4;
	}

	@Override
	public double getPreferredDcc() {
		return -5.7;
	}

    @Override
    public int getOvertakingGap() {
        return 170;
    }

	@Override
	public double getSlamBrakeDcc() { 
		return -2.8;
	}
}