package driver;

public class OldDriver implements Behavior {

    public int getPreferredDistance(){
        return 240;
    }

    public double getPreferredSpeed(){
        return 3.5;
    }

    public boolean likesToChangeLane(){
        return false;
    }

	@Override
	public double getPreferredAcc() {
		 return 0.28;
	}

	@Override
	public double getPreferredDcc() {
		return -.45;
	}

    @Override
    public int getOvertakingGap() {
        return 300;
    }

	@Override
	public double getSlamBrakeDcc() { 
		return -3;
	}
}
