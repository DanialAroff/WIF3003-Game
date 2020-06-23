
package wif3003.assignment;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;


public class Graph extends JPanel{
    
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    //margin value
    private final int mar = 50;
    
   public Graph(double x1, double y1, double x2, double y2){ 
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;

    JFrame frame =new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new Graph(x1,y1,x2,y2,true));
    frame.setSize(1000,1000);
    frame.setLocation(200,200);
    frame.setVisible(true); 
    }
   
   Graph(double x1, double y1, double x2, double y2,boolean t){
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
   }
   
    @Override
    protected void paintComponent(Graphics g){
        
        super.paintComponent(g);
        Graphics2D g1=(Graphics2D)g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int width=getWidth();
        int height=getHeight();
        g1.draw(new Line2D.Double(mar,mar,mar,height-mar));
        g1.draw(new Line2D.Double(mar,height-mar,width-870,height-mar));
        double scale=(double)(height-2*mar)/1000;
        g1.setPaint(Color.MAGENTA);        
        
        System.out.println("x1 : " + x1 +" y1 : " + y1 + " x2 : " + x2 + " y2 : " + y2);
        
        //draw points
        g1.fill(new Ellipse2D.Double(mar+x1,height-mar-scale*y1,4,4));
        g1.fill(new Ellipse2D.Double(mar+x2,height-mar-scale*y2,4,4));
 
        //draw lines between two points
        Shape l = new Line2D.Double(mar+x1, height-mar-scale*y1, mar+x2, height-mar-scale*y2);
        g1.draw(l);




    }
      
    
    
}
