
package wif3003.assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Threads implements Runnable {
  
   ArrayList<Point> points = new ArrayList<>();
   
   private final int n;
   private final AtomicBoolean running = new AtomicBoolean(false);
   ArrayList<ArrayList<Line>> threadList = new ArrayList<>();
      
    Threads(ArrayList<Point> points,int n){
        this.points = points;
        this.n = n;
        
    }
  
    public void stopThread() {
        running.set(false);
    }
    
    
    @Override
    public void run() {
                
                int fail = 0;
                Thread t = Thread.currentThread();  
                RunThread x = new RunThread(points,n);
                running.set(true);
                ArrayList<Line> edges = new ArrayList<>();
                
                while(running.get()){
                     ArrayList<Point> temp = new ArrayList<>();
                     temp = x.getPoints();
                     if(points.size()>1){
                      double x1 = points.get(0).getX();
                      double y1 = points.get(0).getY();
                      double x2 = points.get(1).getX();
                      double y2 = points.get(1).getY();
                if(points.get(0).isConnected() == true || points.get(1).isConnected() == true){
                fail++;
            
            }else if(points.get(0).isConnected() == false && points.get(0).isConnected() == false){
                points.get(0).connect();
                points.get(1).connect();
                edges.add(new Line(x1,y1,x2,y2));
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
                     //i dont know how to link timer to this
                     if(fail == 100){
                         stopThread();
                     }
                }
        threadList.add(edges);
        System.out.println(t.getName() + " has failed " + fail + " times.");
        System.out.println(t.getName() + " has created " + edges.size()/2 + " edges");
                }
     
          
    }



class RunThread{

    
     ArrayList<Point> points = new ArrayList<>();
     private final int n;
    
    public RunThread(ArrayList<Point> points,int n) {
         this.points = points;
          this.n = n;
    }

     public ArrayList getPoints(){
        //To store two points
        ArrayList<Point> line = new ArrayList<>();
        
        //To generate random number count to get a & b
       ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<=n; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        int a = list.get(0);
        int b = list.get(n-1);
        
        //Retrieve and set value of a & b
        for(int k = 0; k < points.size();k++){
            if(k==a){
                    line.add(points.get(k));  
//                System.out.println("a : " + points.get(k).toString());
            }else if(k==b){ 
                    line.add(points.get(k));    
//                System.out.println("b : " + points.get(k).toString());
            }
        }
        
        return line;

    }
    
    
    
}