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
import java.awt.Color;
import driver.*;

public class TCanvas extends Canvas {

    private Image background_image;
    private Image road_image;
    private ArrayList<Image> car_images;
    private ArrayList<Image> car_images_crashed;

    private BufferStrategy buffer;

    ArrayList<Driver> drivers;
    TCanvas(ArrayList<Driver> d) throws IOException{
        drivers = d;
        car_images_crashed = new ArrayList<Image>();
        car_images = new ArrayList<Image>();
        try { //Intellij
            background_image = ImageIO.read( new File(getClass().getResource("./images/backgroundGrass.jpg").getPath()));
            road_image = ImageIO.read(new File(getClass().getResource("./images/road.png").getPath()));
            for(int i = 0; i < 3; i++){
                car_images.add( ImageIO.read(new File(getClass().getResource("./images/car"+ i +".png").getPath())));
                car_images_crashed.add( ImageIO.read(new File(getClass().getResource("./images/car"+ i +"_crashed.png").getPath())));
            }

        } catch (NullPointerException e) { // default
            background_image = ImageIO.read( new File("./images/backgroundGrass.jpg"));
            road_image = ImageIO.read( new File("./images/road.png"));
            for(int i = 0; i < 3; i++){
                car_images.add( ImageIO.read(new File("./images/car"+ i +".png")));
                car_images_crashed.add( ImageIO.read(new File("./images/car"+ i +"_crashed.png")));
            }
        }
        setBackground(new Color(19, 124, 0));
    }

    public void paint(Graphics g) {

        buffer = getBufferStrategy();
        if(buffer == null) {
            createBufferStrategy(2);
            return ;
        }

        g =  buffer.getDrawGraphics();
        g.drawImage(road_image, 0, 120, null);

        for( int i = 0; i < drivers.size(); i++ ) {
            int pic = drivers.get(i).getVehicle().getCar_image();
            if(drivers.get(i).isCrashed()){
                g.drawImage(car_images_crashed.get(pic), drivers.get(i).getX(), drivers.get(i).getY(), null);
            }
            else {
                g.drawImage(car_images.get(pic), drivers.get(i).getX(), drivers.get(i).getY(), null);
            }
        }

        buffer.show();
        g.dispose();
    }

    public void update(Graphics g) {
        paint(g);
    }
}
