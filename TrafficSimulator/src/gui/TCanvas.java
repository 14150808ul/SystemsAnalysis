//Robert O'Driscoll ---- 14150808
//CS4125 - Systems Analysis & Design
//Traffic Simulator
//TCanvas.java
package gui;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Image;
import driver.*;

public class TCanvas extends Canvas {

    private Image background_image;
    private Image road_image;
    private Image car_image;

    private BufferStrategy buffer;

    ArrayList<Driver> drivers;
    TCanvas(ArrayList<Driver> d) throws IOException{
        
        drivers = d;

        try { //Intellij
            background_image = ImageIO.read( new File(getClass().getResource("./images/backgroundGrass.jpg").getPath()));
            road_image = ImageIO.read(new File(getClass().getResource("./images/road.png").getPath()));
            car_image = ImageIO.read(new File(getClass().getResource("./images/car-top-view.png").getPath()));
        } catch (NullPointerException e) { // default
            background_image = ImageIO.read( new File("./images/backgroundGrass.jpg"));
            road_image = ImageIO.read( new File("./images/road.png"));
            car_image = ImageIO.read( new File("./images/car-top-view.png"));
        }
    }

    public void paint(Graphics g) {

        buffer = getBufferStrategy();
        if(buffer == null) {
            createBufferStrategy(2);
            return ;
        }

        g =  buffer.getDrawGraphics();

        g.drawImage(background_image, 0, 0, null);
        g.drawImage(road_image, 0, 175, null);

        for( int i = 0; i < drivers.size(); i++ ) {
          g.drawImage(car_image, (int) drivers.get(i).getX(), (int) drivers.get(i).getY(), null);
        }

        buffer.show();
        g.dispose();
    }

    public void update(Graphics g) {
        paint(g);
    }
}
