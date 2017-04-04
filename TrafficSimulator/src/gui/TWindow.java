//Robert O'Driscoll ---- 14150808
//CS4125 - Systems Analysis & Design
//Traffic Simulator
//TWindow.java
package gui;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.IOException;

import pattern.MapSubject;
import pattern.Observer;
import pattern.Subject;
import statistics.StatsSubject;

public class TWindow extends JFrame implements Observer {

    private Subject subject;
    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_LENGTH = 995;
    
    private TCanvas canvas;
    private TStatsBar statsBar;

    public TWindow(MapSubject subject, StatsSubject statsSubject) throws IOException {

        super("Traffic Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_LENGTH, WINDOW_WIDTH);
        this.setResizable(false);

        this.subject = subject;
        this.subject.attach(this);

        setLayout(new BorderLayout());

        statsBar = new TStatsBar(statsSubject);
        statsBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.add(statsBar, BorderLayout.SOUTH);
        statsBar.setPreferredSize(new Dimension(this.getWidth(), 20));
        statsBar.setLayout(new BoxLayout(statsBar, BoxLayout.X_AXIS));

        statsBar.startTimer();

        canvas = new TCanvas(subject.getDriverAll());
        this.add(canvas);
        setVisible(true);

    }

    public void update(Subject subject) {
        Graphics g = canvas.getGraphics();
        canvas.update(g);
    }
}
