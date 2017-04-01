package map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

import pattern.*;
import road.Road; 
import road.StraightRoad;
import vehicle.Car;
import vehicle.Truck;
import vehicle.Vehicle;
  
import driver.*;

public class Map implements Subject{

	private Road road;
	private Timer timer;
	
	public Map(){
		road = new StraightRoad();
		generateDriver();
		timer = new Timer(globalContract.TimeControl.TIME_UNIT, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				road.updateVehicles();
				notifyObservers();
			}
		});
		//timer.start(); TOOOOOOOO EARLY TO START-> CONSENQUENCE IS TWindow.update(){ canvas.getGraphics() Cannot perform...};
	}
	
	
	/** TODO: Refactor Here After iteration 1.
	**NOTICE: 		The method modefier is public?
	**Attention: 	There are lots of way to generate cars:
	** 				This method should be allocated into a interface which used to polymorphismly generate cars
	***/
	public void generateDriver(){		
		//generate Driver in here!
		//Should create DriverFactory that takes in two numbers and returns a driver object

		Driver driver = new Driver(new Vehicle(3, 0, 0),
				new YoungDriver(), 510, 148, 4, Road.rightLane);
		driver.setAcceleration(0.00);
		
		road.addDriver( driver );
		
  

		Driver driverXX = new Driver(new Vehicle(3, 0, 0),
				new YoungDriver(), 70, 148, 4, Road.rightLane);
		driver.setAcceleration(0.01);
		
		road.addDriver( driverXX );
//
		Driver driver2 = new Driver(new Car(),
				new OldDriver(), 200, 100, 4, 0);
		driver2.setAcceleration(0);

		road.addDriver( driver2 );

		Driver driver3 = new Driver(new Truck(),
				new OldDriver(), 400, 100, 4, 0);
		driver3.setAcceleration(0);

		road.addDriver( driver3 );

		Driver driver4 = new Driver(new Car(),
				new AverageDriver(), 600, 148, 4, 0);
		driver4.setAcceleration(0);

		road.addDriver( driver4 );
	}
	
	@Override
	public void attach(Observer observer) {
		observersList.add(observer);
	}

	@Override
	public ArrayList<Driver> getDriverAll() {
		return road.getDriver_list();
	}

	@Override
	public void notifyObservers() {
		for(Observer o: observersList){
			o.update();
		}
	}
	@Override
	public void start() {
		timer.start();
	}
}