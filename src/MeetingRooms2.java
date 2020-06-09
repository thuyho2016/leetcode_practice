import java.util.Arrays;
import java.util.PriorityQueue;
/*
 * 253. Meeting Rooms II
 * https://leetcode.com/problems/meeting-rooms-ii/
 * 
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
 * find the minimum number of conference rooms required.
Input: [[0, 30],[5, 10],[15, 20]]
Output: 2

Example 2:
Input: [[7,10],[2,4]]
Output: 1


Steps:
 - Create a priority queue
 - Sort the array 
 - Add End element of the first interval to the queue to initial value of queue
 - Go through the array , start i = 1 ( 2nd array)
    - add end of 2nd array, 3nd array,..
     - If start of each array >= end of queue, then remove element from queue or increase count
 
 When we have second meeting, we need to check if it conflict with previous meeting
 
 Time Complexity: O(NlogN),  One is sorting of the array that takes O(NlogN)
 Space Complexity: O(N) 
 
 */
public class MeetingRooms2 {
	
	public static int minMeetingRooms(int[][] intervals) {
	      
		if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]); //Arrays.sort(intervals, (int[] a, int[] b) -> a[0] - b[0]); 
        
        PriorityQueue<Integer> rooms = new PriorityQueue<Integer>((a, b) -> a - b);
        // start with the first meeting, so put it to a meeting room
        rooms.add(intervals[0][1]); //add end element of the first meeting   
               
        for (int i = 1; i < intervals.length; i++) {
            int endTime = rooms.peek(); // early meeting
            
            if (intervals[i][0] >= endTime ) { // if current.start time > end time of earl, increase count or remove element from queue
                rooms.poll();   //remove
              
            }
            rooms.add(intervals[i][1]); //add end of each interval to queue
            System.out.println("Add end of interval to queue: " + intervals[i][1]); 
        }
        return rooms.size();

    }
	
	// if Interval array is passing
	public static int minMeetingRooms2(Interval[] intervals ) {
	      
		if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
         Arrays.sort(intervals, ( a, b) -> a.start - b.start); 
        
         //Use a min heap to track the minimum end time of merged intervals
        PriorityQueue<Interval> queue = new PriorityQueue<>((a, b) -> a.end - b.end);
       
        // start with the first meeting,so put it to a meeting room
        queue.add(intervals[0]); 
        
       // start index 1;
        for (int i = 1; i < intervals.length; i++) {
        	Interval earliest = queue.poll();  // get the meeting room that finishes earliest
            
        	//if we do NOT  have a conflict , we dont need to add meeting rooms        
        	if (intervals[i].start >= earliest.end  ) { 
            	earliest.end = intervals[i].start;               
            }
        	else { //else this meeting needs a new room
        		queue.offer(intervals[i]);
        	}
        	
            queue.add(earliest); //add end of each interval to queue
            System.out.println("Add end of interval to queue: " + earliest); 
        }
        return queue.size();

    }

	public static void main (String[] agrs) {
		
		int [][] input = {{0, 30},{5, 10},{15, 20}};
		//int [][] input = {{7,10},{2,4}};
		int res = minMeetingRooms(input);
		System.out.println("rooms needed: " + res); //2
		
		//[[0, 30],[5, 10],[15, 20]]
		Interval i1 = new Interval(0, 30);
		Interval i2 = new Interval(5, 10);
		Interval i3  = new Interval(15, 20);
		Interval[] input2 = {i1, i2, i3};
		System.out.println("number of rooms: " + minMeetingRooms2(input2)); //2
	}
}