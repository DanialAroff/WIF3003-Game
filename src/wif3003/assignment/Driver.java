
package wif3003.assignment;

import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        
        
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the number of coordinates : ");
        String x = input.next();
        int n = convertToInt(x);
        
        System.out.println("Please enter the number of threads (has to be smaller than number of coordinates) : ");
        String y = input.next();
        int t = convertToInt(y);
        boolean k = compare(n,t);
        
        while(k == true){
            System.out.println("Invalid number");
            System.out.println("Please re-enter the number of threads (has to be larger than number of coordinates) : ");
            y = input.next();
            t = convertToInt(y);
            k = compare(n,t);
        }
        
        System.out.println("Please enter the time limit :");
        String z = input.next();
        int m = convertToInt(y);
        
        
        
        Game game = new Game(n, t, m);
        game.generate1();
//        game.generate2();
    }
    
    
    public static int convertToInt(String x){
        
        int y = Integer.parseInt(x);       
        
        return y;
    }
    
    public static boolean compare(int n, int t){
        
        if(t > n){
            return true;
        }else{
            return false;
        }
        
    }
    
}
