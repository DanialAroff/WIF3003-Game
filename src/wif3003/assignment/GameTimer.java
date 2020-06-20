
package wif3003.assignment;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private int secondPassed;
    private int limit;
    Timer timer = new Timer();
    
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            secondPassed++;
            System.out.println("Second passed: " + secondPassed);
            if (secondPassed >= limit) {
                timerTask.cancel();
                timer.cancel();
//                System.exit(0);
            }
        }   
    };
    
    GameTimer(int limit) {
        secondPassed = 0;
        this.limit = limit;
    }
    
    public void start() {
        timer.schedule(timerTask, 1000, 1000);
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public boolean isTimeUp() {
        return secondPassed >= limit;
    }
    public int getSecondPassed() {
        return secondPassed;
    }
    
    public static void main(String[] args) {
        GameTimer gt = new GameTimer(5);
        gt.start();
    }
}
