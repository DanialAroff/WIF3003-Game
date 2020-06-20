/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wif3003.assignment;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Threads implements Runnable {

//    String name;
//    Threads(String name){
//        this.name = name;
//    }
    
    
    @Override
    public void run() {
        
        Thread t = Thread.currentThread();
        System.out.println("Thread : " + t.getName() );
        try {
            Thread.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}