package collision;

import driver.Driver;
import statistics.StatsSubject;
import vehicle.Vehicle;
 
import java.awt.*;   
import java.util.ArrayList;

	

public class Collision { //Could use Observer Pattern
    private ArrayList<Driver> drivers;
    private StatsSubject statsSubject;
    
   public Collision(ArrayList<Driver> d, StatsSubject statsSubject){
       this.statsSubject = statsSubject;
        drivers = d;
    }

   //T(n) = O(n^2)
    public void checkForCollision(){
        for(int i = 0; i < drivers.size(); i++){
            for(int j = (i+1); j < drivers.size(); j++){
                checkCollision(i, j);
            }
        }
    }

    private void checkCollision(int i, int j){

        Driver d1 = drivers.get(i);
        Driver d2 = drivers.get(j);

        if(!(d1.isCrashed() && d2.isCrashed())) {
            Rectangle r1 = new Rectangle(d1.getX(), d1.getY(), Vehicle.length , Vehicle.width );
            Rectangle r2 = new Rectangle(d2.getX(), d2.getY(), Vehicle.length , Vehicle.width  );

            if (r1.intersects(r2)) {
                statsSubject.setNumber_of_crashes(statsSubject.getNumber_of_crashes() + 1);
                d1.crashed();
                d2.crashed();
            }            
        }
    }
 
}
