package thread;
import java.util.concurrent.*;

/**
 * ConcurrentScheduledTasksExample.java
 *
 * This program demonstrates how to schedule multple tasks to execute after
 * a given delay, and execute periodically after a fixed delay.
 *
 * @author www.codejava.net
 */
 public class ConcurrentScheduledTasksExample {
 
    public static void main(String[] args) {
 
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
 
        CountDownClock clock1 = new CountDownClock("A");
        CountDownClock clock2 = new CountDownClock("B");
        CountDownClock clock3 = new CountDownClock("C");
 
        scheduler.scheduleWithFixedDelay(clock1, 3, 10, TimeUnit.SECONDS);
        scheduler.scheduleWithFixedDelay(clock2, 3, 15, TimeUnit.SECONDS);
        scheduler.scheduleWithFixedDelay(clock3, 3, 20, TimeUnit.SECONDS);
 
    }
}