package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/* 621. Task Scheduler
 https://leetcode.com/problems/task-scheduler/

Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. 
Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.


Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8

Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.

Time complexity : O(n). Number of iterations will be equal to resultant time.

Space complexity : O(1). queue and temp size will not exceed O(26)
 */


public class TaskScheduler {
	
	public static int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> map = new HashMap<>();
        
        for (char c: tasks) {
        	map.put(c,  map.getOrDefault(c,0) + 1);
        }
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b -a);
        maxHeap.addAll(map.values());
        
        int cycles = 0; 
        
        while (!maxHeap.isEmpty()) {
        	
        	List<Integer> tmp = new ArrayList<>(); // store running A -> B -> idle
        	
        	for (int i = 0; i < n + 1; i++) {
        		
        		if (!maxHeap.isEmpty()) {
        			tmp.add(maxHeap.remove());
        		}
        	}
        	
        	for (int i: tmp) {
        		if (--i > 0) {
        	
        			maxHeap.add(i);  // add back to the heap
        		}
        	}
        	
        	cycles += maxHeap.isEmpty() ? tmp.size(): n + 1;
        }
		return cycles; // number of interval CPU will take to finish all tasks
    }
	
	public static void main(String[] args) {
		char[] tasks = {'A','A','A','B','B','B'};
		System.out.println(leastInterval(tasks, 2)); //8
	}

}
