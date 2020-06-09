package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 635. Design Log Storage System 
 * https://leetcode.com/problems/design-log-storage-system/
 
You are given several logs that each log contains a unique id and timestamp. Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, 
for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.

Design a log storage system to implement the following functions:

void put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.

int[] retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the range from start to end. 
Start and end all have the same format as timestamp. However, granularity means the time level for consideration. 
For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.

Example 1:
put(1, "2017:01:01:23:59:59");
put(2, "2017:01:01:22:59:59");
put(3, "2016:01:01:00:00:00");
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], 
 because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
  
 
 Solution:
 
 
     
 */

public class LogStorageSystem {
	Map<Integer, String> map=new HashMap<>(); // string is timestamp
    public LogStorageSystem() {}
    
    public void put(int id, String timestamp) {
        map.put(id,timestamp);
    }
    
    public List<Integer> retrieve(String start, String end, String gra) {
        int x=0;
        switch (gra){
            case "Year":
                x=4; 
                break;
            case "Month":
                x=7;
                break;
            case "Day":
                x=10;
                break;
            case "Hour":
                x=13;
                break;
            case "Minute":
                x=16;
                break;
            case "Second":
                x=19;
                break;
        }
        start = start.substring(0,x);
        end = end.substring(0,x);
        
        List<Integer> ans=new ArrayList<>();
        for (Integer i:map.keySet())
        {
            String ss = map.get(i).substring(0,x);
            if (ss.compareTo(start)>=0 && ss.compareTo(end)<=0) 
            	ans.add(i);
        }
        return ans;
    }
  
    
	  public static void main(String[] args) {
		  LogStorageSystem o = new  LogStorageSystem();
		  o.put(1, "2017:01:01:23:59:59");
		  o.put(2, "2017:01:01:22:59:59");
		  o.put(3, "2016:01:01:00:00:00");
		  System.out.println(o.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year")); // return [1,2,3], because you need to return all logs within 2016 and 2017.
		  System.out.println(o.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour")); // return [1,2], 
	  }
}

