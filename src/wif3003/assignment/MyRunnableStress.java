package wif3003.assignment;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;


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
