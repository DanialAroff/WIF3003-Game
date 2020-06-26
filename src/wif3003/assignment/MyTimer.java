
package wif3003.assignment;

import java.util.concurrent.Callable;

/**
 *
 * @author Danial
 */
public class MyTimer implements Callable {
    
    private int timeLimit;
    private int timeLeft;
    
    public MyTimer(int timeLimit) {
        this.timeLimit = timeLimit;
        this.timeLeft = timeLimit;
    }

    @Override
    public String call() throws Exception {
        long sleepTime = timeLimit * 1000;
        Thread.sleep(sleepTime);
        return "Timeout!";
    }
    
    
}
