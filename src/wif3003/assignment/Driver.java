
package wif3003.assignment;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.ui.RefineryUtilities;

public class Driver {

    static final AtomicBoolean running = new AtomicBoolean(false);
    static int fail;
    static String threadName;
    
    public static void main(String[] args) throws InterruptedException {
        
        Scanner input = new Scanner(System.in);
        boolean inputAccepted = false;
        int n = 0;
        int t = 0;
        int m = 0;
    
        /**
         * This section of the code is for accepting user input for the n,t and m values
         * Validation is performed to ensure the right input values are accepted
         */
        
        while (!inputAccepted) {
            try {
                System.out.println("Please enter the number of coordinates : ");
                n = Integer.valueOf(input.nextLine());
                inputAccepted = true;
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number.");
            }
        }
        
        inputAccepted = false;
        
        while (!inputAccepted) {
            try {
                System.out.println("Please enter the number of threads (has to be smaller than number of coordinates) : ");
                t = Integer.valueOf(input.nextLine());
                boolean k = compare(n,t);
                if(k != true){
                    inputAccepted = true;
                }else{
                    System.out.println("Not a valid number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number.");
            }
        }
          
        
        inputAccepted = false;
        
        while (!inputAccepted) {
            try {
                System.out.println("Please enter the time limit :");
                m = Integer.valueOf(input.nextLine());
                inputAccepted = true;
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number.");
            }
        }
        
     
       
        
        Game game = new Game(n,m);
        game.generatePoints();

        //Rename the threads created
        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicLong threadIndex = new AtomicLong(0);

            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("Thread-" + threadIndex.getAndIncrement());
                return thread;
            }
        };
        
        //Create t number of threads and 1 thread for timer
        ExecutorService thread = Executors.newFixedThreadPool(t + 1, threadFactory);

        running.set(true);
        //To store a list of lines created by each thread
        ArrayList<ArrayList<Line>> lines = new ArrayList<>();
        
        //Execute thread function which is to create lines
        for (int i = 0; i < t; i++) {

            ArrayList<Line> edges = new ArrayList<>();
            threadName = "";
            fail = 0;

            thread.execute(new Runnable() {

                @Override
                public void run() {
                    
                    while (running.get()==true) {

                        ArrayList<Point> temp = new ArrayList<>();
                        temp = game.getPoints();
                        if (temp.size() > 1) {
                            double x1 = temp.get(0).getX();
                            double y1 = temp.get(0).getY();
                            double x2 = temp.get(1).getX();
                            double y2 = temp.get(1).getY();
                            
                            //isConnected means that the point is in use
                            if (temp.get(0).isConnected() == true || temp.get(1).isConnected() == true) {
                                fail++;

                            } else if (temp.get(0).isConnected() == false && temp.get(0).isConnected() == false) {
                                temp.get(0).connect();
                                temp.get(1).connect();
                                Line currentLine = new Line(x1, y1, x2, y2);
                                edges.add(currentLine);
                            }
                        } else {
                            System.out.println(" failure in assigning points");
                        }

//                         if(fail==20 && edges.isEmpty()){
//                             System.out.println("Game ended because " + Thread.currentThread().getName() + " fails to create a line after 20 attempts");
//                             running.set(false);
//                            shutdownAndAwaitTermination(thread,1);  
//                         }
                    }
                    System.out.println(Thread.currentThread().getName() + " : " + "lines created " + edges.size());

                    synchronized(this){
                    lines.add(edges);
                    }
                }
            });
        }
        
        
        //Execute the thread for timer with m as the timeout limit
        Future<String> future = thread.submit(new MyTimer(m));
         try {
            System.out.println("The game has begun.");
            System.out.println(future.get(m, TimeUnit.SECONDS));
            running.set(false);
//            System.out.println("Finished!");
            thread.shutdown();
        } catch (TimeoutException e) {
            future.cancel(true);
//            System.out.println("Terminated");
            running.set(false);
            thread.shutdown();
        } catch (ExecutionException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
        
         
        while (!thread.isTerminated()) {
            
        }
        
        System.out.println("The game has ended.");
        //Generate graph for the lines created
        DrawLines chart = new DrawLines("Chart", "Chart", lines,t);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
    
    
   public static void shutdownAndAwaitTermination(ExecutorService pool,int m) {
   pool.shutdown(); // Disable new tasks from being submitted
   try {
     // Wait a while for existing tasks to terminate
     if (!pool.awaitTermination(m, TimeUnit.SECONDS)) {
       pool.shutdownNow(); // Cancel currently executing tasks
       // Wait a while for tasks to respond to being cancelled
       if (!pool.awaitTermination(m, TimeUnit.SECONDS))
           System.err.println("Pool did not terminate");
     }
   } catch (InterruptedException ie) {
     // (Re-)Cancel if current thread also interrupted
     pool.shutdownNow();
     // Preserve interrupt status
     Thread.currentThread().interrupt();
   }
   }
     
    public static int convertToInt(String x){
        
        int y = Integer.parseInt(x);       
        
        return y;
    }
    
    public static boolean compare(int n, int t){
        
        return t > n;
        
    }
       
}
