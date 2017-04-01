package driver;


public interface Behavior
{
	double getPreferredAcc();
	double getPreferredDcc();
	int getPreferredDistance();
    double getPreferredSpeed();
    boolean likesToChangeLane();
}
