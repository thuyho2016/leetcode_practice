import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 359. Logger Rate Limiter
 * https://leetcode.com/problems/logger-rate-limiter/
 * 
Design a logger system that receive stream of messages along with its timestamps, 
each message should be printed if and only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, 
otherwise returns false.

It is possible that several messages arrive roughly at the same time.

Example:

Logger logger = new Logger();

// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true; 

// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;

// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;

// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;

// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;

// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;
 */

public class RateLimiter_PrintLogMessage {
	/**
	 * Use a map to store the message and timestamp.
	When the "shouldPrintMsg" is called, we check whether the map don't have the string or the previous timestamp is 10 seconds before.
	If yes, we update the map with the new record and return true.
	Otherwise, do nothing and return false 
	 */
	private final HashMap<String, Integer> lastSeen; //msgMap;
    private final Object lock;
    
    public RateLimiter_PrintLogMessage() {
    	lastSeen = new HashMap<String, Integer>();
    	lock = new Object();
    }
	
    //Way 1: synchronized - after 10 seconds, print message
    public boolean shouldPrintMessage(int timestamp, String message) {
        Integer ts = lastSeen.get(message);
        
        if (ts == null || timestamp - ts >= 10) {
        	
        	synchronized (lock) {
        		Integer ts2 = lastSeen.get(message);
        		if (ts == null || timestamp - ts2 >= 10) {
        			lastSeen.put(message, timestamp);
        			return true;
        		}
        	}
        } 

        return false;
    }	    
	    
	/** 
	 * Way 2:
	 * Returns true if the message should be printed in the given timestamp, otherwise returns false.
      If this method returns false, the message will not be printed.
     */
	
    public boolean shouldPrintMessage2(int timestamp, String message) {
    //	HashMap<String,Integer> lastSeen = new HashMap<>(); // to store message, timestamp
    	
    	//first time the message is seen, return true
        if (!lastSeen.containsKey(message)) {  // if mssage has not seen, add to hashMap. 
            lastSeen.put(message, timestamp);
            return true;
        } 
        else { // message was seen, so take out timestamp       
	        int diff = timestamp - lastSeen.getOrDefault(message,timestamp);
	        if (diff >= 10) {  //return true if message has not seen in 10 secs
	            lastSeen.put(message, timestamp);
	            return true;
	        }
        }
        return false;
    
    }
	
	public static void main(String[] args) {
		RateLimiter_PrintLogMessage log = new RateLimiter_PrintLogMessage();
		// logging string "foo" at timestamp 1
	/*	System.out.println(log.shouldPrintMessage(1, "foo")); // returns true; 

		// logging string "bar" at timestamp 2
		System.out.println(log.shouldPrintMessage(2,"bar")); //returns true;

		// logging string "foo" at timestamp 3
		System.out.println(log.shouldPrintMessage(3,"foo")); // returns false;
	*/	
		
		
		System.out.println(log.shouldPrintMessage2(1, "code")); // returns true;
		// logging string "bar" at timestamp 2
		System.out.println(log.shouldPrintMessage2(2,"design")); //returns true;

		// logging string "foo" at timestamp 3
		System.out.println(log.shouldPrintMessage2(3,"code")); // returns false;
	}

}
