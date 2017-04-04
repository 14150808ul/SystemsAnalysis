package map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

import pattern.*;
import road.Road; 
import road.StraightRoad;
import statistics.StatsSubject; 

  
import driver.*;

public class Map implements MapSubject{

	private Road road;
	private Timer timer;

	//private StatsSubject statistics;
	
	public Map(StatsSubject statistics){
		//this.statistics = statistics;
		
		road = new StraightRoad(statistics);
		
		timer = new Timer(globalContract.TimeControl.TIME_UNIT, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Road.getTimeCounter() >= globalContract.TimeControl.TIME_GENERATE_CAR){
					road.generateDriver();
					//statistics.setNumber_of_cars(statistics.getNumber_of_cars() + 1);
					Road.setTimeCounter(0);
				}
				Road.setTimeCounter( Road.getTimeCounter() + globalContract.TimeControl.TIME_UNIT);
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
			o.update(this);
		}
	}
	
	@Override
	public void start() {
		timer.start();
	}

	@Override
	public void remove(Observer observer) {
		observersList.remove(observer);
	}
}