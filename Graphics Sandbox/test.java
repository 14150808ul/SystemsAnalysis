import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class test {
	
	public static void main(String[] args) throws Exception {
		
		//Create Window
        JFrame frame = new JFrame("Title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 200);
        
        
        JPanel jp = new JPanel();
        jp.setLayout(null);
        jp.setBackground(Color.DARK_GRAY);
        frame.add(jp);
        
        ImageIcon Ambulance=new ImageIcon(test.class.getResource("Ambulance.png"));
        ImageIcon Audi=new ImageIcon(test.class.getResource("Audi.png"));
        ImageIcon Black_viper=new ImageIcon(test.class.getResource("Black_viper.png"));
        ImageIcon Car=new ImageIcon(test.class.getResource("Car.png"));
        ImageIcon Mini_truck=new ImageIcon(test.class.getResource("Mini_truck.png"));
        ImageIcon Mini_van=new ImageIcon(test.class.getResource("Mini_van.png"));
        ImageIcon Police=new ImageIcon(test.class.getResource("Police.png"));
        ImageIcon taxi=new ImageIcon(test.class.getResource("taxi.png"));
        ImageIcon truck=new ImageIcon(test.class.getResource("truck.png"));
        
        ImageIcon[] vehicles = new ImageIcon[9];
        vehicles[0] = Ambulance;
        vehicles[1] = Audi;
        vehicles[2] = Black_viper;
        vehicles[3] = Car;
        vehicles[4] = Mini_truck;
        vehicles[5] = Mini_van;
        vehicles[6] = Police;
        vehicles[7] = taxi;
        vehicles[8] = truck;
        
        

        JPanel [] rectangle = new JPanel[30];
        
        for(int i = 0; i < rectangle.length; i++){
        	

        	rectangle[i] = new JPanel();
        	JLabel j = new JLabel();
        	j.setIcon(vehicles[(int)(Math.random() * 9)]);
        	rectangle[i].add(j);
        	rectangle[i].setBackground( Color.DARK_GRAY);
        	jp.add(rectangle[i]);
        	rectangle[i].setBounds((int)(i * 50), (i % 3)* 50, 50, 50);
        }
        
        
        frame.setVisible(true);
        
        
        
        
        while(true){
        for(int i = 0; i < 80; i++){
        	Thread.sleep(3);
        	for(int j = 0; j < rectangle.length ; j++){
        		if(rectangle[j].getX() > 850){
        			rectangle[j].setBounds(-300, rectangle[j].getY() , 50, 50);
        		}
        		else{
        	rectangle[j].setBounds(rectangle[j].getX() + 2, rectangle[j].getY() , 50, 50);
        		}
        	}
        }
      
        }
        
		
	}
	
}

//Code for separating cars into multiple lanes

/*        	if(i % 2 == 0){
          // rectangle[i].setBackground( Color.BLUE);
            rectangle[i].setBounds(50 * i, 10, 50, 50);

        	}
        	else if(i % 3 == 0){
    //   rectangle[i].setBackground( Color.RED);
        rectangle[i].setBounds(50 * i, 300, 50, 50);
        
        	}

        	else{
            //    rectangle[i].setBackground( Color.GREEN);
                rectangle[i].setBounds(50* i, 500, 50, 50);
        	}
*/
