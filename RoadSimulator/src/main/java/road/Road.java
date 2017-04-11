package road;

import java.util.ArrayList;
import collision.Collision;
import driver.Driver;
import driver.DriverFactory;
import pattern.Observer;
import pattern.Subject;
import sense.Sense;
import statistics.StatsSubject;

public abstract class Road implements Observer
{
	Collision collision;
	ArrayList<Driver> driver_list ;
	protected StatsSubject statistics;
	public static final double width = 60; // pix.
	public static final int leftLane = 0;
	public static final int rightLane = 1;
	private static int timeCounter = 0;
	
	public abstract void updateVehicles();

	public static boolean isTimeToGeneratorDriver()
	{
		Road.timeCounter = Road.timeCounter + globalContract.TimeControl.TIME_UNIT;
		return (Road.timeCounter >= globalContract.TimeControl.TIME_GENERATE_CAR);
	}

	Road()
	{
		this.driver_list = new ArrayList<>();
	}
	
	Road(StatsSubject statistics)
	{
		this.statistics = statistics;
		this.driver_list = new ArrayList<>();
	}

	public void generateDriver()
	{
		int random_attributes = (int) (Math.random() * 3 + 1);
		if(Math.random() > 0.5)
		{
			if(Sense.isLaneClear(this.driver_list, Road.leftLane))
				this.addDriver(new DriverFactory().createDriver_withSpecificLane(this, Road.leftLane, random_attributes));
		}
		else
		{
			if(	Sense.isLaneClear(this.driver_list, Road.rightLane) )
				this.addDriver(new DriverFactory().createDriver_withSpecificLane(this, Road.rightLane, random_attributes));
		}
		Road.timeCounter = 0;
	}
	
	
	private void addDriver(Driver driver){
		this.driver_list.add(driver);
	}

	private void removeDriver(Driver driver){
		this.driver_list.remove(driver);
	}
	
	public ArrayList<Driver> getDriver_list() {
		return driver_list;
	}

	
	@Override
	public void update(Subject subject)
	{
		if(subject instanceof Driver)
		{
			try
			{
				this.removeDriver((Driver) subject);
				subject.remove(this);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
}

