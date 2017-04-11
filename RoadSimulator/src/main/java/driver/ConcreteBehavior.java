package driver;


class ConcreteBehavior extends Behavior
{
    @Override
    double getPreferredAcc() {
        return .4;
    }

    @Override
    double getPreferredDcc() {
        return -1.5;
    }

    @Override
    int getPreferredDistance() {
        return 130;
    }

    @Override
    double getPreferredSpeed() {
        return 3.5;
    }

    @Override
    boolean likesToChangeLane() {
        return true;
    }

    @Override
    int getOvertakingGap() {
        return 100;
    }
}
