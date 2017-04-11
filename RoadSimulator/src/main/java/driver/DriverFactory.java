package driver;
import road.Road;
import vehicle.Vehicle;

public class DriverFactory
{
    private static int [] LANES = { 100 , 148 };

    public Driver createDriver_withSpecificLane(Road road, int lane, int random_car)
	{
        double random_acceleration = (Math.random() * 0.3);

        switch(random_car)
        {
           case 1:
               return new Driver(road, new Vehicle (4.4, random_acceleration, 1), new YoungDriver(new YoungDriver(new YoungDriver(new ConcreteBehavior()))), -60, LANES[lane], 2.7, lane);

            case 2:
               return new Driver(road, new Vehicle (4.4, random_acceleration, 0),new YoungDriver(new ConcreteBehavior()), -60, LANES[lane], 2.7, lane);

            default:
               return new Driver(road, new Vehicle (4.4, random_acceleration, 2),new YoungDriver(new ConcreteBehavior()), -60, LANES[lane], 2.7, lane);
        }
    }
}
