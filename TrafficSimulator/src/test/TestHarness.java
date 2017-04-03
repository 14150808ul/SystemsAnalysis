package test;
import map.Map;
import gui.*;
import statistics.Statistics;

public class TestHarness {

    public static void main(String [] args) throws Exception
    {
        Statistics statistics = new Statistics();
        Map map = new Map(statistics);
        TWindow window = new TWindow(map, statistics);
        map.start();
    }
}