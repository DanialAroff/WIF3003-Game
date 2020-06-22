
package wif3003.assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
        int rightLimit = 500;
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
//            System.out.printf("(%d, %d)%n", indexForX, indexForY);
            System.out.println(new Point(x, y).toString());
            
            points.add(new Point(x, y));
        }
        
        
    }
    
    private boolean isInside(double[] arr, double key) {
        int low = 0;
        int high = arr.length - 1;
        int middle;

        while (low <= high) {
            middle = (low + high) / 2;
            if (Double.compare(key, arr[middle]) == 0) {
                return true;
            }
            else if (Double.compare(key, arr[middle]) < 0) {
                high = middle - 1;
            }
            else {
                low = middle + 1;
            }
        }
        return false;
    }  
    
      //Assign two random points for a thread
    /**
     * TODO i am not quite sure why but sometimes it is not able to get value of a/b 
     * 
     */
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
