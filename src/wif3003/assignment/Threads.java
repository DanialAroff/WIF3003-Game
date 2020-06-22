/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wif3003.assignment;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Threads implements Runnable {
  
   ArrayList<Point> points = new ArrayList<>();
   ArrayList<Point> edges = new ArrayList<>();
    
     
    Threads(ArrayList<Point> points){
        this.points = points;
        Graph graph = new Graph(466.14,369.74,411.45,429.79);
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

        try {
            Thread.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
        }
        }  
}
}
