//Robert O'Driscoll ---- 14150808
//CS4125 - Systems Analysis & Design
//Traffic Simulator
//GraphicsDriver
//Used for testing purposes
package gui;
import java.io.IOException;

public class GraphicsDriver {

    public static void main(String [] args) throws Exception {

        SubjectMock subject = new SubjectMock();
        TWindow window = new TWindow(subject);
        subject.start();

    }
}
