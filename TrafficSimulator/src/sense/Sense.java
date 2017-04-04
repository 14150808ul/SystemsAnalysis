package sense;

import driver.Driver;
import gui.TWindow;
import road.Road;
import vehicle.Vehicle;

import java.awt.*;
import java.util.ArrayList;

public class Sense {
    public static int getDistanceFromCarInFront(Driver driver, ArrayList<Driver> list) {

      int next_car_distance = -1;

      for(int i = 0; i < list.size(); i++){

          if(list.get(i).getY() == driver.getY() ) {

              if (list.get(i).getX() > driver.getX()) {
                  int distance = list.get(i).getX() - driver.getX();
                  if (distance < next_car_distance || next_car_distance == -1) {
                      next_car_distance = distance;
                  }
              }
          }
      }

      return next_car_distance;
    }

    public static boolean canChangeLane(Driver driver, ArrayList<Driver> list, int gap){

        Rectangle space_needed = new Rectangle(driver.getX(), 0, gap , 400);

        for(int i = 0; i < list.size(); i++){
            if(list.get(i) != driver) {
                Rectangle other_car = new Rectangle(list.get(i).getX(), list.get(i).getY(), Vehicle.LENGTH, Vehicle.WIDTH);
                if (space_needed.intersects(other_car)) {
                    return false;
                }
            }
        }
        return true;
    }
}
