package driver;

public class OldDriver implements Behavior {

    public int getPreferredDistance(){
        return 300;
    }

    public double getPreferredSpeed(){
        return 2;
    }

    public boolean likesToChangeLane(){
        return false;
    }

	@Override
	public double getPreferredAcc() {
		 return 0.08;
	}

	@Override
	public double getPreferredDcc() {
		return -0.2;
	}

    @Override
    public int getOvertakingGap() {
        return 220;
    }
}
