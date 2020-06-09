package design;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
362. Design Hit Counter
https://leetcode.com/problems/design-hit-counter/

esign a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

Example:

HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301); 

Solution:
 I use a queue to record the information of all the hits. 
 Each time we call the function getHits( ), we have to delete the elements which hits beyond 5 mins (300). The result would be the length of the queue

 */

public class HitCounter {
	private Queue<Integer> q;
    
    /** Initialize your data structure here. */
    public HitCounter() {
 
	    q = new LinkedList<Integer>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). 
        */
    public void hit(int timestamp) {
    	q.offer(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). 
        */
    public int getHits(int timestamp) {
        while(!q.isEmpty() && timestamp - q.peek() >= 300) {
        	q.poll();
        }
        return q.size();
    }

    
  public static void main(String[] args) {
	  HitCounter counter = new HitCounter();
	  counter.hit(1);
	  counter.hit(2);
	  counter.hit(3);
	  
	  System.out.println(counter.getHits(4));  //3
	  counter.hit(300);
	  System.out.println(counter.getHits(300));  //4
	  System.out.println(counter.getHits(301));  //3
  }
}

