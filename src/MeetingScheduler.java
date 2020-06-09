import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * 1229. Meeting Scheduler
https://leetcode.com/problems/meeting-scheduler/

Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, 
return the earliest time slot that works for both of them and is of duration duration.

If there is no common time slot that satisfies the requirements, return an empty array.

The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.  

It is guaranteed that no two availability slots of the same person intersect with each other.
 That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.



Example 1:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]

Example 2:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []


Solution:
1. Merge the slots and sort based on the start time.
2. If there is an overlap, then those are from different users.
3. Check the size of the overlap and return if that is >= duration.

time complexity: O(N+M), where N and N are lengths of slots1 and slots2
space: O(1)
 */


public class MeetingScheduler {

	public static List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
		  Arrays.sort(slots1,(a, b) -> a[0]-b[0]);
	        Arrays.sort(slots2,(a, b) -> a[0]-b[0]);

	        int i=0,j=0;
	        while(i<slots1.length&&j<slots2.length) {
	            int start = Math.max(slots1[i][0],slots2[j][0]);
	            int end = Math.min(slots1[i][1],slots2[j][1]);
	            if (start - end < duration) {
	                if (slots1[i][1]<slots2[j][1]) {
	                    i++;
	                } else {
	                    j++;
	                }
	            } else {
	                return Arrays.asList(start, start + duration);
	            }
	        }
	        return new ArrayList<>();
    }
    
    public static void main (String[] agrs) {
    	int [][] slots1 = {{10, 50},{60, 120},{140, 210}};
    	int [][] slots2 = {{0, 15},{60, 70}};
    	
		System.out.println(minAvailableDuration(slots1, slots2, 8));		
	
    }
}