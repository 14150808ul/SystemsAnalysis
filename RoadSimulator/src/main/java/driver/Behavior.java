package driver;

abstract class Behavior
{
    abstract double getPreferredAcc();
    abstract double getPreferredDcc();
    abstract int getPreferredDistance();
    abstract double getPreferredSpeed();
    abstract boolean likesToChangeLane();
    abstract int getOvertakingGap();
}
