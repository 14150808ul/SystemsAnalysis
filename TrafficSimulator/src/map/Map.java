package map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Timer;

import driver.Driver;
import pattern.*;
import road.Lane;
import road.StraightLane;
import vehicle.Vehicle;
  
import driver.*;

public class Map implements Subject{

	///TODO:Refactor Lane as Road.
	private Lane lane;
	private ArrayList<Driver> driver_list = new ArrayList<Driver>(1); //refactor 1 as random later.
	private Timer timer;
	public Map(){
		lane = new StraightLane();
		
		generateDriver();
		
		timer = new Timer(globalContract.TimeControl.TIME_UNIT, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Iterator<Driver> iterator = driver_list.iterator();
					iterator.hasNext(); 
						){
					Driver eachDriver = iterator.next();
					
					//change car's position
					double deltaX = tsf_Util.Formula.getDeltaDisplacement(eachDriver);
					int carPosX = (int)( eachDriver.getX() + deltaX) ;
					
					if(carPosX < 780 && carPosX >-150)
						eachDriver.setX_pos((int)( eachDriver.getX() + deltaX) );
					else
						eachDriver.setX_pos(-150);
						
					//System.out.println( (int) ( deltaX) );
					
					//change car's volecity

					double volecity = eachDriver.getVehicle().getVelocity() ;
					
					double deltaVolecity = 0;
					if( volecity >= eachDriver.getVehicle().getMax_speed()){
						double acc = eachDriver.getVehicle().getAcceleration();
						if(acc > 0)
							eachDriver.getVehicle().setAcceleration(eachDriver.getVehicle().getAcceleration()*-1);
					}
					if(volecity <= -2){
						eachDriver.getVehicle().setAcceleration(0);
					}
					deltaVolecity = tsf_Util.Formula.getDeltaVolecity(eachDriver);
					
					eachDriver.getVehicle().setVelocity(volecity + deltaVolecity );

					//System.out.print("DDDDDDD:"+deltaVolecity);
					
					
					//System.out.println( (int) (volecity + deltaVolecity) );
					// !!NOTICE: The Car's position should be updated firstly.
				} 
					notifyObservers();
			}
		});
		//timer.start(); TOOOOOOOO EARY TO START-> CONSENQUENCE IS TWindow.update(){ canvas.getGraphics() Cannot perform...};
	} 
	/// TODO: Refactor Here After iteration 1.
	/// NOTICE: the method modefier is public?
	public void generateDriver(){
		//generate Driver in here! 
		driver_list.add(new Driver(new Vehicle(310 /*Max speed*/,0.0 /*Accelerate*/,010 /*initial Velocity*/), new Behavior() {
			@Override
			public void drive() {
				// TODO Auto-generated method stub
			}
		}, 0, 170) );
	}
	
	public Lane getLane() {
		return lane;
	}

	public void setLane(Lane lane) {
		this.lane = lane;
	}
 

	@Override
	public void attach(Observer observer) {
		observersList.add(observer);
	}

	@Override
	public ArrayList<Driver> getDriverAll() {
		return driver_list;
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