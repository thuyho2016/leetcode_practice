import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/*
379. Design Phone Directory

Design a Phone Directory which supports the following operations:

get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.

Example:

// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
PhoneDirectory directory = new PhoneDirectory(3);

// It can return any available phone number. Here we assume it returns 0.
directory.get();

// Assume it returns 1.
directory.get();

// The number 2 is available, so return true.
directory.check(2);

// It returns 2, the only number that is left.
directory.get();

// The number 2 is no longer available, so return false.
directory.check(2);

// Release number 2 back to the pool.
directory.release(2);

// Number 2 is available again, return true.
directory.check(2);

 */

public class PhoneDirectory {
	private Set<Integer> assigned = new HashSet<>();  // hold all numbers assigned
    private LinkedList<Integer> availables;
    int max = 0;
    int maxNumbers;  //maximum numbers that can be stored in the phone directory
	    
    //constructor
    public PhoneDirectory(int maxNumbers) {
        this.maxNumbers = maxNumbers;   
        availables = new LinkedList<Integer>();
        availables.add(0);
    }
    
   // @return - Return an available number. Return -1 if none is available. */
    public int get() {
    	//List doesn't have available number
    	if(availables.isEmpty())
            return -1;
    	
    	int removed = availables.removeFirst(); //remove first node
    	if(availables.isEmpty() && max < maxNumbers - 1) {
    		max++;
    		availables.add(max);
    	}
    	
    	assigned.add(removed);
    	return removed;
    }	
    
    // Check if a number is available or not. 
    public boolean check(int number) {
        return !assigned.contains(number); //return true
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(number != -1 && assigned.contains(number)) {
            assigned.remove(number);
            availables.push(number);
        }
    }
    	
}
