package test;
import map.Map;
import gui.*;
import statistics.Statistics;

public class TestHarness {

    public static void main(String [] args) throws Exception
    {
        Map map = new Map();
        Statistics statistics = new Statistics();
        TWindow window = new TWindow(map, statistics);
        map.start();
    }
}