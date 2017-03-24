package sense;

import driver.Driver;
import java.util.ArrayList;

public class Sense {

    //Assumes cars are in order of position
    public static int getDistanceFromCarInFront(Driver driver, ArrayList<Driver> list) {
        int index = list.indexOf(driver);
        if(index != -1 && list.size() > index + 1){
            Driver nextDriver = list.get(index + 1);
            int distance = nextDriver.getX() - driver.getX();
            if(distance > 0) {
                return distance;
            }
        }

        return -1;
    }
}
