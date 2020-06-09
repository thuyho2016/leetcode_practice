import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/* 1090. Largest Values From Labels 
 * 

We have a set of items: the i-th item has value values[i] and label labels[i].

Then, we choose a subset S of these items, such that:

|S| <= num_wanted
For every label L, the number of items in S with label L is <= use_limit.
Return the largest possible sum of the subset S.

 

Example 1:

Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
Output: 9
Explanation: The subset chosen is the first, third, and fifth item.(5 + 3 + 1=9)

Example 2:

Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
Output: 12
Explanation: The subset chosen is the first, second, and third item. (5 + 4 + 3 = 12)

Example 3:

Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
Output: 16
Explanation: The subset chosen is the first and fourth item.

 */

public class LargestValueFromLabels {	
	class Item {
		int value;
		int label;
		
		public Item (int v, int l) {
			value = v;
			label = l;
		}
	}
	
	public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        List<Item> items = new ArrayList<>();
        
        for (int i = 0; i < labels.length; i++) {
        	items.add( new Item(values[i], labels[i]) ); // add Item object
        }
        
        PriorityQueue<Item> maxHeap = new PriorityQueue<>( (Item a, Item b) -> b.value - a.value );
        maxHeap.addAll(items);
        
        Map<Integer, Integer> counts = new HashMap<>();
        
        int value = 0;
        
        while (!maxHeap.isEmpty() && num_wanted > 0) {
        	Item current = maxHeap.remove();
        	counts.put(current.label,  counts.getOrDefault(current.label, 0) + 1);
        	
        	if (counts.get(current.label) <= use_limit) {
        		value += current.value;
        		num_wanted--;
        	}
        }
        return value;
    }
       
	
    public static void main(String args[])  
    {  
    	int[] values = {5,4,3,2,1};
    	int[] labels = {1,1,2,2,3};
    	
    	LargestValueFromLabels o = new LargestValueFromLabels();
        System.out.print(o.largestValsFromLabels(values,labels, 3, 1));  
    } 
}
