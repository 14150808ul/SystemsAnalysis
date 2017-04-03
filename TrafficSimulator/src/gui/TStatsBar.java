package gui;

import statistics.StatsObserver;
import statistics.StatsSubject;

import javax.swing.*;

public class TStatsBar extends JPanel implements StatsObserver{

    private StatsSubject statsSubject;

    private JLabel number_of_crashes_label;

    TStatsBar(StatsSubject subject){

        statsSubject = subject;

        subject.attach(this);

        number_of_crashes_label = new JLabel("Number of crashes: " + statsSubject.getNumber_of_crashes());
        number_of_crashes_label.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(number_of_crashes_label);

        }

    @Override
    public void update() {
        number_of_crashes_label.setText("Number of crashes: " + statsSubject.getNumber_of_crashes());
    }
}
