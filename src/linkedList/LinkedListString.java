package linkedList;

import java.util.HashSet;

/*
 * 1. Create ListNode 
 * 2. display LinkedList by HashSet -  store all nodes from linked list to HashSet
 */

public class LinkedListString {
    private LinkedListString next;
    public String val;
    
    public LinkedListString (String s) {  //construction
    	   val = s;
    	   next = null;
    	   
    }

    //use HashSet to store all nodes from linked list
	public LinkedListString display(LinkedListString a) {
        HashSet<LinkedListString> s = new HashSet<LinkedListString>();
	    while (a != null) {
            if(!s.contains(a)) {
                s.add(a);
            }
            else {
                //cycle detected.
                break;
            }

            System.out.print(a.val + "->");
	        a = a.next;
	    }
	    System.out.print(a); // null
	    return a;
	}
	
	public LinkedListString display2(LinkedListString a) {
		if (a == null) {
			return a;
		}
	    while(a != null) {
        	System.out.print(a.val + "->");
        	a = a.next;
	    }
        System.out.print(a); // null	    
	    
	    return a;
	}
	
	public static void main (String[] agrs) {
		
			LinkedListString a = new LinkedListString("A");
			LinkedListString b = new LinkedListString("B");
			LinkedListString c = new LinkedListString("C");
			LinkedListString d = new LinkedListString("D");
			
			a.next = b;
			b.next = c;
			c.next = d;
			d.next = null;
			
			//display all nodes
			a.display(a);
			System.out.print("\nDisplay a linklist: ");
			a.display2(a);
		
	}
}


