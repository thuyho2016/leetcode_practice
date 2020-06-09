
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
Implement a rate limiter to prevent abuse of the service (DOS attacks).

Implement a RateLimiter Class with an isAllow method. Every request comes in with a unique clientID, 
deny a request if that client has made more than 100 requests in the past second.

 */

public class RateLimiter_100requests
{	
	 /**
	 * use a hash table lookup for each clientID. Then each clientID will have a queue of 100 max size. 
	 * The queue will hold timestamps.
	 * HashMap with Client ID as key and value as Queue.
	 * this way we can keep a count of the number of requests and also the time of the oldest request
	 * 
	 * 
	 */	
	    
    public class HitCounter {
		private Queue<Long> queue; //hold timestamps
		
		public HitCounter() {
			queue = new LinkedList<Long>();	
		}
	
		public boolean hit(long timestamp) {
			/* when a timestamp hit, we should poll all the timestamp before TIME_LIMIT*/
			while (!queue.isEmpty() && timestamp - queue.peek() >= TIME_LIMIT) 
				queue.poll();
			
			if (queue.size() < REQUEST_LIMIT) {
				queue.add(timestamp); 
				return true;
			}
			return false;
		}
    }	    
	 
   
    private int REQUEST_LIMIT = 3; //100;
    private Long TIME_LIMIT = 1000L;   //1 sec
    
    private HashMap<String, HitCounter> clientHitMap = new HashMap<>();
    
	public boolean isAllow(String clientId) {
		HitCounter h =  clientHitMap.get(clientId);
		
		long currTime = System.currentTimeMillis();
		
		if (h == null) {
			h = new HitCounter();
			clientHitMap.put(clientId,  h);
		} 
		
		return h.hit(currTime); 		
	}
	
	
	public static void main(String[] args) {
		RateLimiter_100requests limiter = new RateLimiter_100requests();
		
		System.out.println("test1 " + limiter.isAllow("test1"));
        System.out.println("test1 " +limiter.isAllow("test1"));
        System.out.println("test1 " +limiter.isAllow("test1"));
        System.out.println("test1 " +limiter.isAllow("test1")); //false
        System.out.println("test2 " +limiter.isAllow("test2"));
        System.out.println("test2 " +limiter.isAllow("test2"));
        System.out.println("test2 " +limiter.isAllow("test2")); 
        System.out.println("test2 " +limiter.isAllow("test2")); //false
        System.out.println("test1 " +limiter.isAllow("test1")); //false
        System.out.println("Sleeping for 1 second");
        try {
            java.lang.Thread.sleep(1000); 
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        System.out.println("test1 " + limiter.isAllow("test1")); //true
        System.out.println("test1 " +limiter.isAllow("test1"));
        System.out.println("test1 " +limiter.isAllow("test1"));
        System.out.println("test1 " +limiter.isAllow("test1")); //false
        System.out.println("test2 " +limiter.isAllow("test2"));
        System.out.println("test2 " +limiter.isAllow("test2"));
        System.out.println("test2 " +limiter.isAllow("test2"));
        System.out.println("test2 " +limiter.isAllow("test2"));  //false
        System.out.println("test1 " +limiter.isAllow("test1"));  //false

	}

}
