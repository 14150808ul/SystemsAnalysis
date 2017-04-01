package sense;

import driver.Driver;
import gui.TWindow;

import java.util.ArrayList;

public class Sense {
    public static int getDistanceFromCarInFront(Driver driver, ArrayList<Driver> list) {

      int next_car_distance = -1;
      int min_car_x = -1;
      
      for(int i = 0; i < list.size(); i++){

          if(list.get(i).getY() == driver.getY() ) {

              if (list.get(i).getX() > driver.getX()) {
                  int distance = list.get(i).getX() - driver.getX();
                  if (distance < next_car_distance || next_car_distance == -1) {
                      next_car_distance = distance;
                  }
              } else {
                  int distance = list.get(i).getX() + (TWindow.WINDOW_LENGTH - driver.getX());
                  if (distance < min_car_x || min_car_x == -1) {
                      min_car_x = distance /*+ 95*/; // why plus 95 here?
                  }
              }
          }
      }

      if(next_car_distance != -1){
          return next_car_distance;
      }
      else{
          return min_car_x;
      }
      
    }
}
