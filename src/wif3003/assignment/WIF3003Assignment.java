
package wif3003.assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class WIF3003Assignment {

    public static void main(String[] args) {
        
        int rightLimit = 1000;
        int leftLimit = -1000;       
        ArrayList<Double> listOfNumbers = new ArrayList<>();
        
        for (int i = 0; i < 50; i++) {
            double generatedFloat = 0;
            while (listOfNumbers.contains(generatedFloat)) {
                generatedFloat = leftLimit + new Random().nextFloat() * (rightLimit-(leftLimit));
                generatedFloat = Math.round(generatedFloat * 100.00) / 100.00;
            }
            listOfNumbers.add(generatedFloat);
        }
        
        Collections.shuffle(listOfNumbers);
        for (int j = 0; j < 10; j++) System.out.println(listOfNumbers.get(j));
        
        /**
         * Plan
         * 
         * 1. Generate lots of floating numbers and add it inside a list
         * 2. Shuffle it
         * 3. Assign two random value from the list to a Point
         * 4. Shuffle it again before assigning new values to another Point
        */

    }
    
}
