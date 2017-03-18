//Robert O'Driscoll ---- 14150808
//CS4125 - Systems Analysis & Design
//Traffic Simulator
//TWindow.java
package gui;
import javax.swing.JFrame;
import java.io.IOException;
import java.awt.Graphics;
import pattern.Observer;
import pattern.Subject;

public class TWindow extends JFrame implements Observer {

    private Subject subject;

    private TCanvas canvas;
    public TWindow(Subject subject) throws IOException {

        super("Traffic Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 400);
        this.setResizable(false);

        this.subject = subject;
        this.subject.attach(this);

        canvas = new TCanvas(subject.getDriverAll());
        this.add(canvas);
        setVisible(true);

    }

    public void update() {
        Graphics g = canvas.getGraphics();
        canvas.update(g);
    }
}
