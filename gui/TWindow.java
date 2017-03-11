//Robert O'Driscoll ---- 14150808
//CS4125 - Systems Analysis & Design
//Traffic Simulator
//TWindow.java
package gui;
import javax.swing.JFrame;
import java.io.IOException;
import java.awt.Graphics;

public class TWindow extends JFrame implements ObserverMock {

    private SubjectMock subject;

    private TCanvas canvas;
    TWindow(SubjectMock subject) throws IOException {

        super("Traffic Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);

        canvas = new TCanvas();
        this.add(canvas);
        setVisible(true);

        this.subject = subject;
        this.subject.attach(this);
    }

    public void update() {
        canvas.setCarX(subject.getCarPosition());
        Graphics g = canvas.getGraphics();
        canvas.update(g);

    }
}
