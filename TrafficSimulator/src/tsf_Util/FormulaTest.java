package tsf_Util;

import driver.Driver;
import driver.OldDriver;
import vehicle.Vehicle;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by rober on 16/03/2017.
 */
class FormulaTest {

    private Driver driver;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Vehicle vehicle = new Vehicle(10, 10, 1);
        OldDriver oldDriver = new OldDriver();
        driver = new Driver(vehicle, oldDriver, 10, 10, 1, 1);
    }

    @org.junit.jupiter.api.Test
    void getDeltaDisplacement() {
        double result = Formula.getDeltaDisplacement(driver);
        assertEquals(15, result);
    }

    @org.junit.jupiter.api.Test
    void getDeltaVolecity() {
        double result = Formula.getDeltaVolecity(driver);
        assertEquals(10, result);
    }

    @org.junit.jupiter.api.Test
    void getDeltaVolecity_Decrease() {
        double result = Formula.getDeltaVolecity_Decrease(driver);
        assertEquals(-10, result);
    }

}