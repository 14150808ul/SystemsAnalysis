package driver;


class YoungDriver extends BehaviorDecorator
{
    private Behavior behavior;

    YoungDriver(Behavior behavior) {
        this.behavior = behavior;
    }

    @Override
    double getPreferredAcc() {
        return behavior.getPreferredAcc() + .6;
    }

    @Override
    double getPreferredDcc() {
        return behavior.getPreferredDcc() + -2;
    }

    @Override
    int getPreferredDistance() {
        return behavior.getPreferredDistance() - 4;
    }

    @Override
    double getPreferredSpeed() {
        return behavior.getPreferredSpeed() + 12;
    }

    @Override
    int getOvertakingGap() {
        return behavior.getOvertakingGap() + 100;
    }
}
