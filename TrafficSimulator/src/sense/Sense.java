package sense;

import driver.Driver;
import java.util.ArrayList;

public class Sense {
    public static int getDistanceFromCarInFront(Driver driver, ArrayList<Driver> list) {

      int next_car_distance = -1;
      for(int i = 0; i < list.size(); i++){
          if(list.get(i).getX() > driver.getX()){
              int distance = list.get(i).getX() - driver.getX();
              if(distance < next_car_distance || next_car_distance == -1) {
                  next_car_distance = distance;
              }
          }
      }
      return  next_car_distance;
    }
}
