
package design;
/*
 * 981. Time Based Key-Value Store
 https://leetcode.com/problems/time-based-key-value-store/
 
 Create a timebased key-value store class TimeMap, that supports two operations.

1. set(string key, string value, int timestamp)

Stores the key and value, along with the given timestamp.
2. get(string key, int timestamp)

Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
If there are multiple such values, it returns the one with the largest timestamp_prev.
If there are no values, it returns the empty string ("").
 

Example 1:

Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
Output: [null,null,"bar","bar",null,"bar2","bar2"]
Explanation:   
TimeMap kv;   
kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1   
kv.get("foo", 1);  // output "bar"   
kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"   
kv.set("foo", "bar2", 4);   
kv.get("foo", 4); // output "bar2"   
kv.get("foo", 5); //output "bar2"   

Example 2:

Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
Output: [null,null,null,"","high","high","low","low"]


Time Complexity: O(1) for each set operation, and O(logN) for each get operation, where N is the number of entries in the TimeMap.

Space Complexity: O(N)
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TimeBasedKeyValue_BS {
	class TimeVal{
        int time;
        String value;
        
        TimeVal(int _time, String _value)
        {
            time = _time;
            value = _value;
        }
    }
	
	HashMap<String,List<TimeVal> > hm;

     //HashMap + TreeMao or Binary Search
	public TimeBasedKeyValue_BS() {
		 hm = new HashMap<String, List<TimeVal> >();
    }
    
	public void set(String key, String value, int timestamp) {
		TimeVal curr = new TimeVal(timestamp, value);
        
		List<TimeVal> l = hm.getOrDefault(key, new ArrayList<TimeVal>());
        l.add(curr);
        hm.put(key,l);
        return;
    }
    
    public String get(String key, int timestamp) {
        List<TimeVal> l = hm.get(key);
        
        int left = 0, right = l.size()-1;
        
        while(left <= right)
        {
            int mid = left + (right-left)/2;
            int currTime = l.get(mid).time;
            
            if(currTime == timestamp)
                return l.get(mid).value;
            else if(currTime < timestamp) // right side
                left = mid +1;
            else
                right = mid -1;
        }
        
        if(right < 0)
            return "";
        return l.get(right).value;
    }
    
    public static void main(String[] args) { 
    	TimeBasedKeyValue_BS o = new TimeBasedKeyValue_BS();
    	o.set("foo", "bar", 1);          
    	System.out.println(o.get("foo", 1));  // output "bar"   
    	System.out.println(o.get("foo", 3)); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"   
    	o.set("foo", "bar2", 4);   
    	System.out.println(o.get("foo", 4)); // output "bar2"   
    	System.out.println(o.get("foo", 5)); //output "bar2"
    
    }
}

