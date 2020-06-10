/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wif3003.assignment;

/**
 *
 * @author User
 */
class MyRunnableStress implements Runnable {
    private volatile boolean alive = true;
    
    public void terminate() {
        this.alive = false;
    }
    
    @Override
    public void run() {
        while(alive) {
             System.out.println("Thread running");
              try { Thread.sleep(5000); } catch(InterruptedException ie) {}
            }
        }
    }
