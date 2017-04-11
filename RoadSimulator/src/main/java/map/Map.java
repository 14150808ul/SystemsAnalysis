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
	
	public Map(){}
	public Map(StatsSubject statistics){
		
		road = new StraightRoad(statistics);
		
		timer = new Timer(globalContract.TimeControl.TIME_UNIT, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Road.isTimeToGeneratorDriver()){
					road.generateDriver();
				}
				road.updateVehicles();
				notifyObservers();				
			}
		});
		//timer.start(); TOOOOOOOO EARLY TO START-> CONSENQUENCE IS TWindow.update(){ canvas.getGraphics() Cannot perform...};
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