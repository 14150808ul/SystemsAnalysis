package sense;

import driver.Driver;
import road.Road;
import vehicle.Vehicle;

import java.awt.*;
import java.util.ArrayList;

public class Sense
{
    public static int getDistanceFromCarInFront(Driver driver, ArrayList<Driver> list)
    {
      int next_car_distance = -1;

      int tolerance = (int) (Road.width / 2);

        for (Driver aList : list)
        {
            int difference = Math.abs(aList.getY() - driver.getY());

            if (difference < tolerance)
            {
                if (aList.getX() > driver.getX())
                {
                    int distance = aList.getX() - driver.getX();

                    if (distance < next_car_distance || next_car_distance == -1) next_car_distance = distance;
                }
            }
        }
      return next_car_distance;
    }

    public static boolean canChangeLane(Driver driver, ArrayList<Driver> list, int gap)
    {
        Rectangle space_needed = new Rectangle(driver.getX() - gap , driver.getY() - (int)(2 * Road.width), 2*gap , (int)(4 *Road.width) );

        for (Driver d : list)
        {
            if (d  != driver)
            {
                Rectangle other_car = new Rectangle(d .getX(), d .getY(), Vehicle.LENGTH, Vehicle.WIDTH);
                if (space_needed.intersects(other_car)) return false;
            }
        }
        return true;
    }

    public static boolean isLaneClear(ArrayList<Driver> driverArrayList, int lane)
    {
        Rectangle space_needed;

        int distance_needed = 100;

        if(lane == Road.leftLane) space_needed = new Rectangle(0, 100, distance_needed, (int)Road.width/2);

        else space_needed = new Rectangle(0, 148, distance_needed, (int)Road.width/2);

        for (Driver aDriverArrayList : driverArrayList)
        {
            Rectangle car = new Rectangle(aDriverArrayList.getX(), aDriverArrayList.getY(), Vehicle.LENGTH, Vehicle.WIDTH);

            if (space_needed.intersects(car)) return false;
        }
        return true;
    }
}
