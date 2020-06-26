
package wif3003.assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * 
 * This class is used to generate the points 
 */

public class Game {
    
    private final int n;
    private final int m;
    private final ArrayList<Point> points;
    
    
    public Game(int n,int m){
        this.n = n;
        this.m = m;
        points = new ArrayList<>();
       
    }

    public void generatePoints() {
        int rightLimit = 1000;
        // int leftLimit = -500;
        int leftLimit = 0;
        ArrayList<Double> listOfNumbers = new ArrayList<>();
        
        for (int i = 0; i < 5000; i++) {
            double generatedFloat = 0;
            while (listOfNumbers.contains(generatedFloat)) {
                generatedFloat = leftLimit + new Random().nextFloat() * (rightLimit-(leftLimit));
                generatedFloat = Math.round(generatedFloat * 100.00) / 100.00;
            }
            listOfNumbers.add(generatedFloat);
        }
        
        Collections.shuffle(listOfNumbers);
        for (int j = 0; j < n; j++) {
            int indexForX = new Random().nextInt(listOfNumbers.size());
            int indexForY = new Random().nextInt(listOfNumbers.size());
            double x = listOfNumbers.get(indexForX);
            double y = listOfNumbers.get(indexForY);
            
            points.add(new Point(x, y));
        }
        
        
    }
    
    //Return list of generated points
    public ArrayList getList(){
        return points;
    }
     
    
      //Assign two random points for a thread
    public ArrayList getPoints(){
        
        //To store two points
        ArrayList<Point> line = new ArrayList<>();
        
        //To generate random number count to get a & b
       ArrayList<Integer> list = new ArrayList<Integer>();
       for (int i=0; i<n; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        int a = list.get(0);
        int b = list.get(n-1);
        
        //Retrieve and set value of a & b
        for(int k = 0; k < points.size();k++){
            if(k==a){
                    line.add(points.get(k));  
            }else if(k==b){ 
                    line.add(points.get(k));    
            }
        }
        
        return line;
        
        
    }
    
    
}
