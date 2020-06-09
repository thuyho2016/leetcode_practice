package linkedList;
/*
 * 24. Swap Nodes in Pairs
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 * 
 * Given a linked list, swap every two adjacent nodes and return its head.
 
Example:

Given 1->2->3->4, you should return the list as 2->1->4->3.

Explain:
 Swap 1 & 2, we need to delete the link between 2 and 3
 before deleting the link between 2 and 3, we need to store the address (3 -> 4) to temp variable to avoid the lost address of 3
 		temp point to 3
   1->2  3->4  5 - > 6
   change pointer 2 to 1
   1 <- 2  3 -> 4
   |_ _ _ _ _ _ _|
   
    temp point to 5   
     _ _ _ _ _ _
    |          |        
    1 <-2  3<- 4  5 <- 6 null
           |_ _ _ _ _ _|
    
    temp point to null
    
     >_ _ _ _ _    _ _ _ _>
    |          |  |       |
    1 <-2  3<- 4  5 <- 6 null
           |_ _ _ _ _ _|
 */  


public class SwapNodesInPairs {
	
	//This solution is easy to understand 
    public static Node swapPairs(Node head){
    	if (head == null || head.next  == null)  return head;
    	
    	Node cur = head;
    	int temp;
        while (cur != null && cur.next != null){
        	//swap values
        	
        	temp = cur.val;
        	cur.val = cur.next.val;
        	cur.next.val = temp;
        	
        	cur = cur.next.next;
        }
        return head;
      
    }
    
    public static Node swapPairs2(Node head) {
	   if(head == null || head.next == null) return head;
	
	
	    Node current =new Node(0);
        current.next= head;
        int i = 0;
        
        while(current != null && current.next != null && current.next.next != null){
            current = swap(current); 
            
            if(i == 0){
                head = current.next;
            }
            
            current = current.next.next;
            i++;
        }
        
        return head;
    }
 
    public static Node swap(Node n){
        
        Node temp = n.next;
        n.next = n.next.next;
       
        temp.next = n.next.next;
        n.next.next = temp;
        return n;        
    }
    
  
    
    public static void printNodes(Node head){
    	if (head == null) {
    		return;
    	}
    	Node cur = head;
    	while ( cur != null) {
    		System.out.print(cur.val + "->");
    		cur = cur.next;
    	}
    	System.out.println(cur); 
    }

    public static void main(String[] args) {
    	// [1,2,3,4]
    	Node head = new Node(1);
		head.next = new Node(2);	
		head.next.next= new Node(3);
		head.next.next.next = new Node(4);
		printNodes(head);
		
  		Node h = swapPairs(head);
        printNodes(h);  //2->1->4->3.
    }
}