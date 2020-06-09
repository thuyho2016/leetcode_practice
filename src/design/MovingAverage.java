package design;

import java.util.LinkedList;
import java.util.Queue;

/*
346.  Moving Average from Data Stream ( easy level)
https://leetcode.com/problems/moving-average-from-data-stream/

Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Example:

MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3

 */

public class MovingAverage {
	private Queue<Integer> queue;
	private int maxSize;
	private double sum;
     
    /** Initialize your data structure here. */
    public MovingAverage (int size) {
  
	    queue = new LinkedList<Integer>();
	    maxSize = size;
	  	sum = 0.0;
    }
    
    //every time queue has val, add to sum and return
    public double next(int val) {
    	if (queue.size() == maxSize) {   // if we hits the max size of queue, remove it from queue and reduce from sum
    		sum = sum - queue.remove();
    	}
    	
    	queue.add(val);
    	sum += val;
    	return sum / queue.size();
    	
    }
    


    
  public static void main(String[] args) {
	  HitCounter counter = new HitCounter();
	  counter.hit(1);
	  counter.hit(2);
	  counter.hit(3);
	  
	  System.out.println(counter.getHits(4));  //3
	  System.out.println(counter.getHits(300))
	  ;  //3
  }
}

