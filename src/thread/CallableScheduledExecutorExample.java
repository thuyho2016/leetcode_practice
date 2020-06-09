package thread;
import java.util.concurrent.*;
 
/**
 *
 * This program demonstrates how to demonstrates how to schedule a Callable task that returns a value.
 *
 */
public class CallableScheduledExecutorExample {
	
    public static void main(String[] args) {
    	
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
 
        Callable<Integer> task = new Callable<Integer>() {
            public Integer call() {
                try {
                	Thread.sleep(5000); // 5 secs
                } catch (InterruptedException e) {
                	e.printStackTrace();
                }
                return 1000000;
            }
        };
 
        int delay = 5;
        
        Future<Integer> result = scheduler.schedule(task, delay, TimeUnit.SECONDS);
        
        try {
        	Integer value = result.get();
        	System.out.println("value = " + value);
        	
        } catch (InterruptedException | ExecutionException ex) {
        	ex.printStackTrace();
        }
        
        scheduler.shutdown();
    }
}