package map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

import driver.Driver;
import pattern.*;
import road.Road; 
import road.StraightRoad;
import vehicle.Vehicle;
  
import driver.*;

public class Map implements Subject{

	///TODO:Refactor Lane as Road.
	private Road road;
	//private ArrayList<Driver> driver_list = new ArrayList<Driver>(1); //refactor 1 as random later.
	private Timer timer;
	public Map(){
		road = new StraightRoad();
		generateDriver();
		timer = new Timer(globalContract.TimeControl.TIME_UNIT, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//update each driver 
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
		Driver driver = new Driver(new Vehicle(310,0), new Behavior() {
			@Override
			public void drive() {
				// TODO Auto-generated method stub
			}
		}, 0, 116, 2, 0);
		driver.setAcceleration(0.00);
		
		road.addDriver( driver );
		
		//driver.changeLane();
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