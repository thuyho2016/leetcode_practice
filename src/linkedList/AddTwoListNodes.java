package linkedList;

/* Add two linked lists:
 * Input1: (1 -> 2 -> 4) + (4 -> 5 -> 6) = 5 -> 7 -> 0 -> 1   ( carry 1 in the end)
 * 
 * Input2: (1 -> 2 -> 3) 
 *       + (6 -> 7) 
 *       
 * Reversed: 3 -> 2 -> 1
 *           7 -> 6 -> 0    ( list 2 is lesser, so add new node
 *  Add      0 -> 9 -> 1 (3 + 7 + carry 1 = 9) 
 * 
 * https://www.geeksforgeeks.org/sum-of-two-linked-lists/
 * 
 * Steps:
 * If len2 < len 1, add new node
 * 2. Reverse both two lists
 * 3. Add
 *   - addition = 10
 *   - divide_by_10 = 10/10 = 1
 *   
 *   If divide_by_10 = 1, do two steps below
 *     - remainder = 5  (example 15/10 = 5)
 *     - carry = 1
 *     
 *   When divide_by_10 is not 0, new node is remainder
 *   when divide_by_10 is 0,  new node is addition 
 *   
 *   Add 3 + 7: 
 *     addition = 10. 10/10 = 1. so, new node is remainder = 0 , carry = 1
 *   Add 2 + 6
 *     addition = 8. 8/10 = 0. so, new node = 8 , but carry exist above = 1
 *     so add carry with addition = 1 + 8 = 9
 *     
 *     Addition = 9. divide_by_10 = 9/10 = 0, new node = 9
 *     
 *   Now, 1 + 0 
 *      addition = 1. 1/10 = 0 . so new node = 1
 *   
 *    
 *   4. Reverse 0 -> 9 -> 1 to 1 -> 9 -> 0
 *  
 *   
 *   Case: In the end of last node 1 + 9
 *   Addition = 10, 10/10 = 1, remainder = 0, carry = 1
 *   new node is 0, but you have to create new node to carry 1
 *   
 */
public class AddTwoListNodes {
	public static Node addTwoLists(Node l1, Node l2) {
	    Node head = new Node(0); //initialize node with val = 0
	    Node cur = head;         //set head node to pointer
	    int carry = 0;
	    int n1 =0, n2 =0;
	    int sum = 0;
	    
	    while(l1 != null || l2 != null){
	    	
	        n1 = (l1 != null ? l1.val : 0);
	        n2 = (l2 != null ? l2.val : 0);
	        
	        // Calculate sum = carry +  digit from list 1 + digit from list 2 
	        sum = n1 + n2 + carry;
	       
	        carry =  sum / 10; // if sum is > 9, carry = 1, else carry = 0  // carry = (sum > 9) ? 1 : 0;

	    
            sum = sum % 10;  // update sum if it is greater than 10, // 15 % 10 = 5 
            
            // Create a new node with sum as data and then connect it to cur if this is not the first node,
            
            cur.next = new Node(sum); // cur.next = 5. 
            cur = cur.next;            //set cur = 5 , cur.next = null. E.g 5->null , 7-> null
	        
	        // Move l1 and l2 pointers to next nodes
	        if(l1 != null)
	        {	
	        	l1 = l1.next; // node 2
	        }
	        if(l2 != null)
	        {
	        	l2 = l2.next;
	        }
	    }
	    
	  //Carry is non zero in the end Case: 6 -> 8 -> 1 -> 1 -> null
	    if (carry > 0) {
	        cur.next = new Node(carry);
	    }
	    
	    return head.next; 
	}
		
	
	//link all nodes together use recursive
	public static Node appendNodes(Node head, int data) {
		if (head == null)
			return head = new Node(data);  // create a head node
		else {
			if (head.next == null) {   // create next node after head
				return head.next = new Node(data);
			} else {
				//use recursive
				return appendNodes(head.next, data);  
				
				// or while loop to go next node
			/*	while (head.next != null) { //to move to the last node
					head = head.next;
				}
				head.next = new Node(data);	 // assign data to the last node 
				return head; */
			}
		}
	}


    //2. Given a Node, print all nodes it hold 	
	public static void display(Node head) {
		if (head ==null) {
			return;
		}
		Node current = head;   // create temporary current to point to head node
		//loop each element till end of the list. Last node point to null
		while (current !=null) {
			System.out.print(current.val + " -> ");  //print current node with data
			current = current.next;  //move to next node
		}
		System.out.println(current); //current is null node
	}
	
	public static Node reverseList(Node head) {
	    Node prev = null;
	 
	    // head = A --> B --> C --> null , In 2nd round, head  = B ->C. In 3rd round, head = C->null
	    while (head != null) {
	        Node nextTemp = head.next; // nextTemp = B --> C --> null, 2nd round, nextTemp = C -> null
	        //swap node
	        head.next = prev; // prev = null, so head.next = null. 2nd round, head.next = A -> null. 3rd, head.next = B->A
	        prev = head;      // prev = A --> null because head.next = null above, 2nd round, prev = B-> A , C->B->A
	        head = nextTemp;  // head = B --> C --> null, C-> null , null
	    }
	    System.out.print("Reversed: " );
		display(prev);
	    return prev;
	}
	
	public static void main (String[] agrs) {
		// Assum, list already reversed
		
		//create linkedlist1
		Node head1 = new Node(1);
		Node nextNode = appendNodes(head1 , 2);
		nextNode = appendNodes(head1 , 4);		
		//display Linkedlist with all nodes
		display(head1);
		
		//create linkedlist2
		Node head2 = new Node(4);
		nextNode  = appendNodes(head2 , 5);
		nextNode = appendNodes(head2 , 6);
		//display Linkedlist with all nodes
		display(head2);
		
		System.out.println("After Add two LinkedList:");		
		Node newList = addTwoLists(head1, head2);  //5 --> 7 --> 9 --> null		
		display(newList);
		
		//Input2: carry in the end, need to create new node
		//(1 -> 2 -> 3) + (5 -> 6 -> 7) 
		//create linkedlist2
		Node head3 = new Node(5);
		nextNode = appendNodes(head3 , 6);
		nextNode = appendNodes(head3 , 7);
				
		System.out.println("Two Lists:");
		//display Linkedlist with all nodes;
		display(head1);
		display(head3);
		
		System.out.println("After Add two Lists:");
		newList = addTwoLists(head1, head3);  //6 -> 8 -> 0 -> 1 --> null
		display(newList);
		
		
		//Input3: list 2 is lesser list 1
		//(1 -> 2 -> 3) + (6 -> 7) 
		
		//create linkedlist1
		Node head4 = new Node(1);
		nextNode = appendNodes(head4 , 2);
		nextNode = appendNodes(head4 , 3);
		
		//create linkedlist2
		Node head5 = new Node(6);
		nextNode = appendNodes(head5 , 7);
		System.out.println("Two Lists:");
		display(head4);
		display(head5);		
		
		head4 = reverseList(head4);
		head5 = reverseList(head5);
		
		System.out.println("Add two Lists:");
		newList = addTwoLists(head4, head5);
		display(newList);   // 0 -> 9 -> 1 --> null
		
		reverseList(newList);
		
	}
}

	  
	