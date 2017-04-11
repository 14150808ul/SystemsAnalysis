package gui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import pattern.MapSubject;
import pattern.Observer;
import pattern.Subject;
import statistics.StatsSubject;

public class TWindow extends JFrame implements Observer
{
    private TCanvas canvas;
    private static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_LENGTH = 1300;
    public static final int LEFTWANE_Y_Coordinate =  100;
    public static final int RIGHTWANE_Y_Coordinate = 148;

    public TWindow(MapSubject subject, StatsSubject statsSubject)
    {
        super("Traffic Simulator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(WINDOW_LENGTH, WINDOW_WIDTH);
        this.setResizable(false);
        subject.attach(this);
        setLayout(new BorderLayout());
        TStatsBar statsBar = new TStatsBar(statsSubject);
        statsBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.add(statsBar, BorderLayout.SOUTH);
        statsBar.setPreferredSize(new Dimension(this.getWidth(), 20));
        statsBar.setLayout(new BoxLayout(statsBar, BoxLayout.X_AXIS));
        statsBar.startTimer();
        canvas = new TCanvas(subject.getDriverAll());
        this.add(canvas);
        setVisible(true);
    }

    public void update(Subject subject)
    {
        Graphics g = canvas.getGraphics();
        canvas.update(g);
    }
}
