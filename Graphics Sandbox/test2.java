import java.awt.*;
import java.util.ArrayList;

import javax.swing.JFrame;

class myCanvas extends Canvas{
	
	private ArrayList<Rectangle> shapes;
	
	myCanvas(){
		shapes = new ArrayList<Rectangle>();
	}
	
	public void addShape(Rectangle r){
		shapes.add(r);
	}
		
    public void paint(Graphics g){
        g.setColor(Color.BLACK);      
    	for( int i = 0; i < shapes.size(); i++){
            g.fillRect(shapes.get(i).x, shapes.get(i).y, shapes.get(i).height, shapes.get(i).width);
    	}
    }
    
    public void update(Graphics g) 
    { 
         paint(g); 
    } 
    
  
};

public class test2{

    public static void main(String[] args) throws InterruptedException{

        myCanvas canvas = new myCanvas(); 
        
        JFrame frame = new JFrame("Title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        
        //add the canvas
        frame.add(canvas);
        frame.setVisible(true);
        
        Graphics g = canvas.getGraphics();
        
        Rectangle r1 = new Rectangle(10, 10, 10, 10);
        Rectangle r2 = new Rectangle(20, 20, 10, 10);
        
        canvas.addShape(r1);
        canvas.addShape(r2);
        
        while(true){
        	Thread.sleep(30);
        	if(r1.x > 800){
        		r1.setLocation(0, r1.y +10);
        	}
        	r1.setLocation(r1.x + 10, r1.y);
        	r2.setLocation((int)(Math.random() * 800), (int)(Math.random() * 600));
            canvas.update(g);
        }       
    }
}