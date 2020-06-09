/*
 * You’re given data extracted from the mall’s door detectors. Each data point is represented as an integer array whose size is 3. 
 * The values at indices 0, 1 and 2 are the timestamp, the count of visitors, and whether the visitors entered or exited the mall
 * (0 for exit and 1 for entrance).
 * 
 * Given an array, data, of data points, write a function findBusiestPeriod that returns the time at which the mall reached its busiest moment last year. 
 * The return value is the timestamp, e.g. 1480640292. Note that if there is more than one period with the same visitor peak, return the earliest one.

  Assume that the array data is sorted in an ascending order by the timestamp. Explain your solution and analyze its time and space complexities.
 input:  data = [ [1487799425, 14, 1], 
                 [1487799425, 4,  0],
                 [1487799425, 2,  0],
                 [1487800378, 10, 1],
                 [1487801478, 18, 0],
                 [1487801478, 18, 1],
                 [1487901013, 1,  0],
                 [1487901211, 7,  1],
                 [1487901211, 7,  0] ]

output: 1487800378

Steps:
0 = exiting , 1 = entering
create 3 variable - people, maxTime, maxPeople

 */

public class BusiestTimeInMall { 	

	static int findBusiestPeriod1(int[][] data) {
	  if (data.length == 0) { return 0; }
	    int max = Integer.MIN_VALUE;  //max people
	    int curTime = data[0][0];
	    int cur_people = 0;
	    
	    for (int i = 0; i < data.length; i++) {
	      if (data[i][2] == 1)      //if people enter the mall, add
	        cur_people += data[i][1];
	      else                      // if people leave, subtract
	        cur_people -= data[i][1];
	      
	      //different version 1 and version2
	      if (i < data.length - 1 && data[i][0] == data[i + 1][0]) {
	    	System.out.println( data[i][0] + " == " + data[i + 1][0]);
	        continue;
	      } 
	      
	      if (cur_people > max) {  // if people in mall is greater max people documented at once,
	        max = cur_people;      //then update max people to equal cur_people = 8, 18
	        curTime= data[i][0];  //update time
	      }
	    }
	    return curTime;
	  }

	    // Prefer this version
	    static int findBusiestPeriod2(int[][] data) {
	    if (data.length == 0) { return 0; }
	    int max = Integer.MIN_VALUE;
	    int curTime = data[0][0];
	    int cur_people = 0;
	    
	    for (int i = 0; i < data.length; i++) {
	      if (data[i][2] == 1)
	    	  cur_people += data[i][1];
	      else 
	    	  cur_people -= data[i][1];
	      
	      //different version 1 and version2
	      if (i == data.length - 1 || data[i][0] != data[i + 1][0]) {
	        if (cur_people > max) { 
	          max = cur_people;
	          curTime = data[i][0];
	        }
	      }
	      
	    }
	    return curTime;

    }   
	    
	public static void main(String[] args) {
	  int[][] data = new int[][]
		  {{1487799425, 14, 1}, 
          {1487799425, 4,  0},
          {1487799425, 2,  0},
          {1487800378, 10, 1},
          {1487801478, 18, 0},
          {1487801478, 18, 1},
          {1487901013, 1,  0},
          {1487901211, 7,  1},
          {1487901211, 7,  0} }; 
    //      int res = findBusiestPeriod1(data);
    //      System.out.println(res);	   // output: 1487800378

          int res2 = findBusiestPeriod2(data);
          System.out.println(res2);	   // output: 1487800378
	  
	}
	
} 
