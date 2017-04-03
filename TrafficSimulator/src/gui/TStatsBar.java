package gui;

import statistics.StatsObserver;
import statistics.StatsSubject;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TStatsBar extends JPanel implements StatsObserver{

    private StatsSubject statsSubject;

    private JLabel number_of_crashes_label;
    private JLabel number_of_cars_label;
    private JLabel time_label;

    private Timer timer;

    TStatsBar(StatsSubject subject){

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time_label.setText("Simulation time: " + statsSubject.getSimulationTime() + " seconds");
            }
        });

        statsSubject = subject;

        subject.attach(this);

        number_of_crashes_label = new JLabel("Number of crashes: " + statsSubject.getNumber_of_crashes());
        number_of_crashes_label.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(number_of_crashes_label);

        number_of_cars_label = new JLabel("Number of vehicles: " + statsSubject.getNumber_of_cars());
        number_of_cars_label.setHorizontalAlignment(SwingConstants.LEFT);

        number_of_cars_label.setBorder(new EmptyBorder(0,30,0,0));

        this.add(number_of_cars_label);

        time_label = new JLabel("Simulation time: " + 0);
        time_label.setHorizontalAlignment(SwingConstants.LEFT);

        time_label.setBorder(new EmptyBorder(0,30,0,0));

        this.add(time_label);
        }

    @Override
    public void update() {
        number_of_crashes_label.setText("Number of crashes: " + statsSubject.getNumber_of_crashes());
        number_of_cars_label.setText("Number of cars: " + statsSubject.getNumber_of_cars());
    }

    public void startTimer(){
        timer.start();
    }
}
