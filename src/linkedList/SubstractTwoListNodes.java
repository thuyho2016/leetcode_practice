package linkedList;


/* Substract two linked lists:
 * Input1: (1 -> 2 -> 4) - (5 -> 6 -> 7) = 4 -> 4 -> 3 -> null
 * 
 * Input2: (1 -> 2 -> 3)  - (6 -> 7) 
       
      
 Reversed:  3 -> 2 -> 1
            7 -> 6 -> 0    ( list 2 is lesser, so add new node

  Substract: 6 -> 5 -> 0 -> null
  Reversed: 0 -> 5 -> 6 -> null

 
 * https://www.geeksforgeeks.org/sum-of-two-linked-lists/
 * 
 * Steps:
 * If len2 < len 1, add new node
 * 2. Reverse both two lists

2 Cases:
If sizes not are same, then append zeros in smaller linked list.
      if lengths  is differ - List 2 is lesser list 1 , add 0 ...
 
If size are same, then follow below steps:
-  Find the smaller valued linked list.
- One by one subtract nodes of smaller sized linked list from larger size. Keep track of borrow while subtracting.
 
 3. Substract
  
     if  n1 < n2,  borrow the number from previous digit. Add 10 to n1 and set borrow = true.
     // case 6 < 9 . Add 10 + 6 = 16, so 16 - 9 = 7

     when reach next node, if borrow is true, reduce by 1:  n1--
      3 -> 2 -> 1
      7 -> 6 -> 0
 */


public class SubstractTwoListNodes {
	public static Node substractTwoLists(Node l1, Node l2) {
	    int n1 = 0, n2 = 0;
	    int sub = 0;
	    boolean borrow = false;
	    
	    int len1 = getLength(l1);
	    int len2 = getLength(l2);
	    
		Node head = new Node(0); //initialize node with val = 0
	    Node cur = head; //set head node to pointer
	    
	    while(l1 != null || l2 != null){
	    	
	    	// if lengths is differ - append zeroes 0
	        n1 = (l1 != null ? l1.val : 0);
	        n2 = (l2 != null ? l2.val : 0);
	        
	        // if both len are equal, but list 1 is smaller than list 2 
	        //  1 -> 2 -> 4 -> null
	        // 5 -> 6 -> 7 -> null
	        if (len1 == len2) {
	        	n1 = l1.val < l2.val ? l2.val : l1.val;
	        	n2 = l1.val < l2.val ? l1.val : l2.val;	        
	        }
	        
	        if (borrow) { // reduce n1 by 1 if you have given the value to next digit 
	        	n1--;
	        	borrow = false;
	        }
	        
	        // n1 < n2, then borrow the number from previous digit. Add 10 to n1
	        if (n1 < n2) { // case 16 - 9 , 6 < 9 . so 10 + 6 = 16
	        	n1 = n1 + 10;
	        	borrow = true;
	        }
	       
	        // Substract  digit from list 1 with digit from list 2 
	        sub = n1 - n2; // Math.abs(n1 - n2);
           
            // Create a new node with sub value and then connect it to cur if this is not the first node,
            
            cur.next = new Node(sub); // cur.next = 5. 
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
	    
	  
	    return head.next; 
	}
		
	private static int getLength(Node head) {
		int size = 0;
		while (head != null) {
			head = head.next;
			size++;
		}
		return size;
		
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
	
	//li is larger, l2 is smaller
	public static Node substractHelper(Node l1, Node l2) {
		Node head = new Node(0); //initialize node with val = 0
	    Node cur = head; //set head node to pointer
	
	    int n1 =0, n2 =0;
	    int sub = 0;
	    boolean borrow = false;
	     
        if (borrow) { // reduce n1 by 1 if you have given the value to next digit 
        	n1--;
        	borrow = false;
        }
        
        // n1 < n2, then borrow the number from previous digit. Add 10 to n1
        if (n1 < n2) { // case 16 - 9 , 6 < 9 . so 10 + 6 = 16
        	n1 = n1 + 10;
        	borrow = true;
        }
       
        // Substract  digit from list 1 with digit from list 2 
        sub = Math.abs(n1 - n2);
       
        // Create a new node with sub value and then connect it to cur if this is not the first node,
        
        cur.next = new Node(sub); // cur.next = 5. 
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
    
        return head.next;
	}
	
	//link all nodes together
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
		//loop each element till end of the list
		//last node point to null
		while (current !=null) {
			System.out.print(current.val + " -> ");  //print current node with data
		    //move to next node/element
			current = current.next;
		}
		System.out.println(current); //current is null node
	}
	
	
	public static void main (String[] agrs) {
		//Assum, lists already reversed
		
		//Input2: Same length - List 1 is larger than list 2
		//create linkedlist2
		Node head2 = new Node(4);
		Node nextNode  = appendNodes(head2 , 5);
		nextNode = appendNodes(head2 , 6);
		//display Linkedlist with all nodes
		display(head2);
		
		//create linkedlist1
		Node head1 = new Node(1);
		nextNode = appendNodes(head1 , 2);
		nextNode = appendNodes(head1 , 4);		
		//display Linkedlist with all nodes
		display(head1);
		
		
		System.out.println("Subtracted: ");		
		Node newList = substractTwoLists(head2, head1);  //3 -> 3 -> 2 ->  null		
		display(newList);
		
		//Input2: Same length - List 1 is small than list 2
		//(1 -> 2 -> 3) - (5 -> 6 -> 7) 
		//create linkedlist2
		Node head3 = new Node(5);
		nextNode = appendNodes(head3 , 6);
		nextNode = appendNodes(head3 , 7);
				
		System.out.println("List 1 is smaller than list 2:");
		//display Linkedlist with all nodes;
		display(head1);
		display(head3);
		
		System.out.println("Substracted: ");
		newList = substractTwoLists(head1, head3);  
		display(newList);
		
		
		//Input3: //Length differ - list 2 is lesser list 1
		//(1 -> 2 -> 3) + (6 -> 7) 
		
		//create linkedlist1
		Node head4 = new Node(1);
		nextNode = appendNodes(head4 , 2);
		nextNode = appendNodes(head4 , 3);
		
		//create linkedlist2
		Node head5 = new Node(6);
		nextNode = appendNodes(head5 , 7);
		System.out.println("List 2 is lesser list 1");
		display(head4);
		display(head5);
		
		head4 = reverseList(head4);
		head5 = reverseList(head5);
		
		System.out.println("Substract two Lists:");
		newList = substractTwoLists(head4, head5);
		display(newList);   //6 -> 5 -> 0 -> null
		reverseList(newList);
			
	}
}

	  
	