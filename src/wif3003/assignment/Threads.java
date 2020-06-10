/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wif3003.assignment;
import java.util.Scanner;
/**
 *
 * @author User
 */
public class Threads {
     public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
        int input =0;
        
        MyRunnableStress runnable = new MyRunnableStress();
        Thread thread = new Thread(runnable);
        
        System.out.println("Enter number of Threads: ");
        thread.start();
        input = scan.nextInt();    
        
        System.out.println("Your number of threads was: " + input);
        
        runnable.terminate();
    }
    
}