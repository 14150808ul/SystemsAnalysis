package test;
import map.Map;
import gui.*;

public class TestHarness {

    public static void main(String [] args) throws Exception
    {
        Map map = new Map();
        TWindow window = new TWindow(map);
        map.start();
    }
}