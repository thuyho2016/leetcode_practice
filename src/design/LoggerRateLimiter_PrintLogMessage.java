package design;
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

public class LoggerRateLimiter_PrintLogMessage {

	private final HashMap<String, Integer> lastSeen; // to store message along with timestamp
   // private final Object lock;
    
    public LoggerRateLimiter_PrintLogMessage() {
    	lastSeen = new HashMap<String, Integer>();
    	//lock = new Object();
    }
	
	/** 
	 * Way 1:
	Create a hashMap to store the message and timestamp.
	    
    The message is eligible to be printed with two conditions:  
	case 1). we have never seen the message before. ( mean check the Hashmap doesn't have the message
	case 2). we have seen the message before, and it was printed more than 10 seconds ago.

    In both of the above cases, we would then update the entry that is associated with the message in the hashmap with the latest timestamp.
    Otherwise, do nothing and return false .
    
	Time Complexity: O(1). The lookup and update of the hashMap takes a constant time.
    Space Complexity: O(M) where M is the size of all incoming messages. 
    Over the time, the hashMap would have an entry for each unique message that has appeared.
    
   ( Returns true if the message should be printed in the given timestamp, otherwise returns false.
    If this method returns false, the message will not be printed.)
     */
	
    public boolean shouldPrintMessage(int timestamp, String message) {
    	
    	//first time the message is seen, return true
        if (!lastSeen.containsKey(message)) {  // if massage has not seen, add to hashMap. 
            lastSeen.put(message, timestamp);
            return true;
        } 
        else { // message was seen, then check it timestamp to b make sure is not seen in last 10 secs.       
	        int diff = timestamp - lastSeen.getOrDefault(message,timestamp);
	        if (diff >= 10) {  //if message has not seen in 10 secs, return true. ( Means the message should print)
	            lastSeen.put(message, timestamp);
	            return true;
	        }
        }
        return false;
    
    }
    

    //Way 2: synchronized - after 10 seconds, print message
  /*  public boolean shouldPrintMessage2(int timestamp, String message) {
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
	*/    
	
	public static void main(String[] args) {
		LoggerRateLimiter_PrintLogMessage log = new LoggerRateLimiter_PrintLogMessage();
		// logging string "foo" at timestamp 1
		System.out.println(log.shouldPrintMessage(1, "foo")); // returns true; 

		// logging string "bar" at timestamp 2
		System.out.println(log.shouldPrintMessage(2,"bar")); //returns true;

		// logging string "foo" at timestamp 3
		System.out.println(log.shouldPrintMessage(3,"foo")); // returns false;
		
		
		
	/*	System.out.println(log.shouldPrintMessage2(1, "code")); // returns true;
		// logging string "bar" at timestamp 2
		System.out.println(log.shouldPrintMessage2(2,"design")); //returns true;

		// logging string "foo" at timestamp 3
		System.out.println(log.shouldPrintMessage2(3,"code")); // returns false;
		*/
	}

}
