package design;

import java.util.TreeMap;

/* 715. Range Module    (hard level)
https://leetcode.com/problems/range-module/

A Range Module is a module that tracks ranges of numbers. Your task is to design and implement the following interfaces in an efficient manner.

addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. 
  Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.

queryRange(int left, int right) Returns true if and only if every real number in the interval [left, right) is currently being tracked.

removeRange(int left, int right) Stops tracking every real number currently being tracked in the interval [left, right).


Example 1:
addRange(10, 20): null
removeRange(14, 16): null
queryRange(10, 14): true (Every number in [10, 14) is being tracked)
queryRange(13, 15): false (Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
queryRange(16, 17): true (The number 16 in [16, 17) is still being tracked, despite the remove operation)

 */

public class RangeModule {
	

	private TreeMap<Integer, Integer> map = new TreeMap<>(); 
	
    /** Initialize your data structure here. */
	public RangeModule() {
        
    }    

    public void addRange(int left, int right) {
        if (left >= right) return;
        Integer start = map.floorKey(left);
        
        if (start == null) start = map.ceilingKey(left);
       
        while (start != null && start <= right) {
            int end = map.get(start);
           
            if (end >= left) {
                map.remove(start);
                if (start < left) left = start;
                if (end > right) right = end;
            }
            start = map.ceilingKey(end);
        }
        map.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        Integer floor = map.floorKey(left);
        return floor != null && map.get(floor) >= right;
    }

    //same with addRange, but differ , line 73, 74
    public void removeRange(int left, int right) {
        if (left >= right) return;
        Integer start = map.floorKey(left);
        
        if (start == null) start = map.ceilingKey(left);
        
        while (start != null && start < right) {
            int end = map.get(start);
            
            if (end >= left) {
                map.remove(start);
                if (start < left) map.put(start, left);
                if (end > right) map.put(right, end);
            }
            start = map.ceilingKey(end);
        }
    }
    
    

    
  public static void main(String[] args) {
	  RangeModule obj = new RangeModule();
	  
	  obj.addRange(10, 20);
	  obj.removeRange(14, 16);
	  
	  System.out.println(obj.queryRange(10, 14));  // true
	  System.out.println(obj.queryRange(13, 15));  //false
	  System.out.println(obj.queryRange(16, 17));  //true
  }
}

