
import java.util.Arrays;
import java.util.Comparator;

/*
 * Given an array of meeting time intervals consisting of start and end times [s1, e1], [s2, e2], ... , determine if a person could attend all meetings.

For example,
Given [ [0, 30], [5, 10], [15, 20] ],
return false.

 */
public class MeetingRooms1 {
	
	public static boolean canAttendMeetings2(Interval[] intervals) {
		
		// sort start of each interval
        Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);
        
        // i start from 1, compare if current interval overlap with previous one intervals[i - 1]
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i - 1].end)  // use < , not <= 
            	return false; 
        }
        return true;
    }
	
	public static boolean canAttendMeetings(Interval[] intervals) {
	    
		Arrays.sort(intervals, new Comparator<Interval>(){
	        public int compare(Interval a, Interval b){
	            return a.start - b.start;
	        }
	    });
	 
	    for(int i=0; i < intervals.length-1; i++){
	        if(intervals[i].end > intervals[i+1].start){
	            return false;
	        }
	    }
	 
	    return true;
	}
	
	//Sort Interval object.
/*	public class IntervalComparator implements Comparator<Object>{
        public int compare(Object o1, Object o2){
            Interval i1 = (Interval)o1;
            Interval i2 = (Interval)o2;
            return i1.start - i2.start;  //use compareTo if it is String
        }
        
        */
	
	public static void main (String[] agrs) {
		
		//[[0, 30],[5, 10],[15, 20]]
		Interval i1 = new Interval(0, 30);
		Interval i2 = new Interval(5, 10);
		Interval i3  = new Interval(15, 20);
		Interval[] input = {i1, i2, i3};
		
		//System.out.println(canAttendMeetings(input));
		
		System.out.println(canAttendMeetings2(input));  //false
	}
}

	