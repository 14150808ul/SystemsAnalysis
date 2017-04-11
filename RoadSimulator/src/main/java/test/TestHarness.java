package test;
import map.Map;
import gui.*;
import statistics.Statistics;

public class TestHarness {

    public static void main(String [] args)
    {
        Statistics statistics = new Statistics();
        Map map = new Map(statistics);
        new TWindow(map, statistics);
        map.start();
    }
}