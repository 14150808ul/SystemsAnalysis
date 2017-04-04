package driver;
import road.Road;
import vehicle.Vehicle;

public class DriverFactory {
    private static Behavior [] BEHAVIOR = { new YoungDriver(), new AverageDriver(), new OldDriver()};
    private static int [] LANES = { 100 , 148 };

    public Driver createDriver(Road road) {
        int random_image = (int) (Math.random() * 3);
        int random_lane = (int) (Math.random() * 2);
        return new Driver(  road,
			        		new Vehicle (10, (Math.random() * .02), random_image), 
			        		BEHAVIOR[(int) (Math.random() * 3)],
			        		-60, // must be 0 -> otherwise cause problem
			        		LANES[random_lane],
			        		3,
			        		random_lane);
    }
}
