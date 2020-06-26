
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

    static boolean timer = true;
    static final AtomicBoolean running = new AtomicBoolean(false);
    static int fail;
    static String threadName;
    static int m = 0;
    
    public static void main(String[] args) throws InterruptedException {
        
        Scanner input = new Scanner(System.in);
        boolean inputAccepted = false;
        int n = 0;
        int t = 0;
        
        
       
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

        ArrayList<Point> points = game.getList();  
        Runnable a = new Threads(points,n);
//        GameTimer time = new GameTimer(m);
//        time.start();
//        if (timer.isTimeUp()) System.exit(0);
        
        //Generate number of threads based on user input
//        for(int i = 0; i < t;i++){
//           Thread temp = new Thread(a);
//           temp.setName("Thread " + Integer.toString(i));
//           temp.start();
//        }
        

        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicLong threadIndex = new AtomicLong(0);

            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("Thread-" + threadIndex.getAndIncrement());
                return thread;
            }
        };

        ExecutorService thread = Executors.newFixedThreadPool(t, threadFactory);
        
        Future<String> future = thread.submit(new MyTimer(m));
         try {
            System.out.println("Started..");
            System.out.println(future.get(3, TimeUnit.SECONDS));
            System.out.println("Finished!");
        } catch (TimeoutException e) {
            future.cancel(true);
            System.out.println("Terminated");
        } catch (ExecutionException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }

        running.set(true);
        ArrayList<ArrayList<Line>> lines = new ArrayList<>();
        for (int i = 0; i < t; i++) {

            ArrayList<Line> edges = new ArrayList<>();
            threadName = "";
            fail = 0;

            thread.execute(new Runnable() {

                @Override
                public void run() {
                    
                    outerloop:
                    while (running.get()==true && fail <= 20) {
                        ArrayList<Point> temp = new ArrayList<>();
                        temp = game.getPoints();
                        if (temp.size() > 1) {
                            double x1 = temp.get(0).getX();
                            double y1 = temp.get(0).getY();
                            double x2 = temp.get(1).getX();
                            double y2 = temp.get(1).getY();
                            if (temp.get(0).isConnected() == true || temp.get(1).isConnected() == true) {
                                fail++;

                            } else if (temp.get(0).isConnected() == false && temp.get(0).isConnected() == false) {
                                temp.get(0).connect();
                                temp.get(1).connect();
                                Line currentLine = new Line(x1, y1, x2, y2);
//                                edges.add(new Line(x1, y1, x2, y2));
                                edges.add(currentLine);
//                                lines.add(currentLine);
                            }
                        } else {
                            System.out.println(" failure in assigning points");
                        }

                         if (fail >= 20) {
//                            System.out.println("Fail " + fail);
                            running.set(false);
                            shutdownAndAwaitTermination(thread,m);
                            break outerloop;
                            
                        }

                    }
                    System.out.println(Thread.currentThread().getName() + " : " + "lines created " + edges.size() + " Failures : " + fail);
                    threadName = Thread.currentThread().getName();
//                    lines.clear();
                    
//                    for(int k = 0; k < edges.size(); k++){
//                    System.out.println("Line ("+Thread.currentThread().getName() +") " + edges.get(k).toString());   
//                    } 
                    
                    lines.add(edges);
                }
            });
        }
        
        while (!thread.isTerminated()) {
            
        }
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
