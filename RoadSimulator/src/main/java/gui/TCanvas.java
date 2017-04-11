package gui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferStrategy;
import java.awt.Image;
import java.awt.Color;
import driver.*;

public class TCanvas extends Canvas
{
    private Image road_image;
    private ArrayList<Image>  car_images = new ArrayList<>();
    private ArrayList<Image> car_images_crashed = new ArrayList<>();
    private ArrayList<Driver> drivers;

    TCanvas(ArrayList<Driver> d)
    {
        drivers = d;
        try
        {
            road_image = ImageIO.read(new File(getClass().getResource("/road.png").getPath()));
            for(int i = 0; i < 3; i++)
            {
                car_images.add(ImageIO.read(new File(getClass().getResource("/car"+ i +".png").getPath())));
                car_images_crashed.add(ImageIO.read(new File(getClass().getResource("/car"+ i +"_crashed.png").getPath())));
            }
        }
        catch (NullPointerException | IOException e)
        {
            e.printStackTrace();
        }
        setBackground(new Color(76, 124, 27));
    }

    public void paint(Graphics g)
    {
        BufferStrategy buffer = getBufferStrategy();

        if(buffer == null) createBufferStrategy(2);

        else
        {
            g =  buffer.getDrawGraphics();

            g.drawImage(road_image, 0, 120, null);
            g.drawImage(road_image, 1000, 120, null);

            for (Driver driver : drivers)
            {
                int pic = driver.getVehicle().getCarImage();

                if (driver.isCrashed()) g.drawImage(car_images_crashed.get(pic), driver.getX(), driver.getY(), null);

                else g.drawImage(car_images.get(pic), driver.getX(), driver.getY(), null);
            }
            buffer.show();
            g.dispose();
        }
    }

    public void update(Graphics g) {
        paint(g);
    }
}
