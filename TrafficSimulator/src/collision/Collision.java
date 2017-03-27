package collision;

import driver.Driver;

import java.awt.*;
import java.util.ArrayList;

public class Collision { //Could use Observer Pattern
    private ArrayList<Driver> drivers;

   public Collision(ArrayList<Driver> d){
        drivers = d;
    }

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
        Rectangle r1 = new Rectangle(d1.getX(), d1.getY(), 90, 50);
        Rectangle r2 = new Rectangle(d2.getX(), d2.getY(), 90, 50);

        if(r1.intersects(r2)){
d1.crashed();
d2.crashed();
        }
    }
}
