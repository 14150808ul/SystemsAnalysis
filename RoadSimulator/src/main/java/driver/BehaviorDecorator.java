package driver;

abstract class BehaviorDecorator extends Behavior
{
    abstract int getPreferredDistance();

    abstract double getPreferredSpeed();

    boolean likesToChangeLane() {
        return true;
    }
}
