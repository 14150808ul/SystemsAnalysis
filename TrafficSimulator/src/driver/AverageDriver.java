package driver;

public class AverageDriver implements Behavior {

    public int getPreferredDistance(){
        return 160;
    }

    public double getPreferredSpeed(){
        return 3;
    }

    public boolean likesToChangeLane(){
        return true;
    }

	@Override
	public double getPreferredAcc() {
		return 0.2;
	}

	@Override
	public double getPreferredDcc() {
		return -0.2;
	}

    @Override
    public int getOvertakingGap() {
        return 200;
    }
}