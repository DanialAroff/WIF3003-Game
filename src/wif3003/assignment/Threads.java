
package wif3003.assignment;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Threads implements Runnable {
  
   ArrayList<Point> points = new ArrayList<>();
   ArrayList<Point> edges = new ArrayList<>();
    
     
    Threads(ArrayList<Point> points){
        this.points = points;
        
    }
    
    
    @Override
    public void run() {
        
        Thread t = Thread.currentThread();   
        if(points.size()>1){
            
            System.out.println(" Points for " + t.getName() + " : " + points.get(0)+ " , " + points.get(1) );
        
            double x1 = points.get(0).getX();
            double y1 = points.get(0).getY();
            double x2 = points.get(0).getX();
            double y2 = points.get(0).getY();
        
            if(points.get(0).isConnected() == true || points.get(1).isConnected() == true){
                System.out.println(t.getName() + " has failed.");
            
            }else if(points.get(0).isConnected() == false && points.get(0).isConnected() == false){
                points.get(0).connect();
                points.get(1).connect();
                System.out.println(t.getName() + " has successfully created a line.");
            }
        
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        else{
            System.out.println(t.getName() + " : failure in assigning points (not retrieving value of points in getPoints)");
        }
    }
}
