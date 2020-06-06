
package wif3003.assignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Game {
    
    private int n;
    private int t;
    private int m;
    private ArrayList<Point> points;
    
    public Game(int n, int t, int m){
        this.n = n;
        this.t = t;
        this.m = m;
        points = new ArrayList<>();
    }
    
    public void generate1() {
        long startTime = System.nanoTime();
        
        this.generatePoints(n);
        
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        try {
            File f = new File("./algo_1.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
            bw.append("Total time for algo 1 (n=" + n + "): " + String.valueOf(totalTime / 
                    Math.pow(10, 9)) + "\n");
            bw.close();
        } catch (IOException e) {
            System.out.println("Cannot read file");
        }
        System.out.println("\n" + (totalTime / Math.pow(10, 9)));
    }
    public void generate2() {
        long startTime = System.nanoTime();
        
        this.generatePoints2(n);
        
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        try {
            File f = new File("./algo_1.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
            bw.append("Total time for algo 2 (n=" + n + "): " + String.valueOf(totalTime / 
                    Math.pow(10, 9)) + "\n");
            bw.close();
        } catch (IOException e) {
            System.out.println("Cannot read file");
        }
        System.out.println("\n" + (totalTime / Math.pow(10, 9)));
    }

    private void generatePoints(int n) {
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
        for (int j = 0; j < this.n; j++) {
            int indexForX = new Random().nextInt(listOfNumbers.size());
            int indexForY = new Random().nextInt(listOfNumbers.size());
            double x = listOfNumbers.get(indexForX);
            double y = listOfNumbers.get(indexForY);
//            System.out.printf("(%d, %d)%n", indexForX, indexForY);
            System.out.println(new Point(x, y).toString());
            
            points.add(new Point(x, y));
        }
    }
    
    private void generatePoints2(int n) {
        int rightLimit = 500;
        int leftLimit = -500;
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
        
        for (int j = 0; j < this.n; j++) {
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
}
