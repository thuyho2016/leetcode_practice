package thread;
import java.util.concurrent.*;
 
/**
 *
 * This program demonstrates how to schedule a task to execute after a given delay.
 * This program simply prints the message “Hi!” after a delay of 5 seconds, and then terminates.
 *
 */
public class SingleThreadScheduledExecutorExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler
                            = Executors.newSingleThreadScheduledExecutor();
 
        Runnable task = new Runnable() {
            public void run() {
                System.out.println("Hi!");
            }
        };
 
        int delay = 5;
        scheduler.schedule(task, delay, TimeUnit.SECONDS);
        scheduler.shutdown();
    }
}