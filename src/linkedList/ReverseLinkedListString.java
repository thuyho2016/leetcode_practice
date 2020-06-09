package linkedList;

/*
Create LinkedList : A -> B -> C ->null
then, reverse LinkedList: C->B->A->null
*/

public class ReverseLinkedListString {
	
  //create a LinkedList to link all nodes by insert last
  // a.next = b;  //A->B
  // b.next = c; // A -> B -> C
  public static NodeString appendNodes(NodeString head, String data) {
	  if (head == null)
		  return new NodeString(data);  // create a head node with data
	  else {  //heaad !=null
		  if (head.next == null) {
			  head.next = new NodeString(data); //create next node after head node
		  } else {  
			  //using recursive  
			  appendNodes(head.next, data);  // using recursive  A -> B  (head.next is B)
			  
			  //or using while loop 
		/*	  NodeString current = head;  // create a temporary node to point to head ( A-> B)
			  while (current.next != null) {    //current.next is B
				  current = current.next;
			  } // when current.next reach out in the end, it will exit while loop
			  
			  current.next = new NodeString(data); // assign data to the last node   A->B->C
			  */
		  } 
		  return head;
	  }
  }
  
  // reverse the Linkedlist
  //1. B ->C -> A
  //2. C ->B -> A
  /**
   * 
   * Second while loop: curr is B--> C --> null
   *    nextTemp is C --> null
   *    curr.next is A--> null  ( because prev is A--> null in the first loop)
   *    prev is B --> A --> null ( because curr is B -> A-> null above)
   *    curr is C --> null
   *    
   *    third while loop: curr is C--> null
   *    nextTemp is null
   *    curr.next is B->A-> null
   *    prev is C->B->A->null
   *    curr is null , then exit while loop
   */
  public static NodeString reverseList(NodeString head) {
	    NodeString prev = null;
	 
	    //1st round: head = A-> B -> C -> null , 
	    //In 2nd round:, head = B -> C -> null 
	    //In 3rd round: head = C-> null
	    //4th round: head = null , so dont go inside while loop
	    while (head != null) {
	        NodeString nextTemp = head.next; //nextTemp = B -> C -> null. 2nd round: nextTemp = C -> null. 3rd round: nextTemp = null
	        
	        //swap node
	        head.next = prev; // head.next = null because prev = null (so, head = A -> null). 2nd round: head.next = A -> null. 3rd round: head.next = B->A->null
	        prev = head;      // prev = A -> null above. 2nd round: prev = B-> A->null. 3rd round: C->B->A->null
	        head = nextTemp;  // head = B -> C -> null; 2nd round: head = C-> null. 3rd round: head =null
	    }
	   
	    return prev;
	}  

  
  //print all nodes from linkedlist
  public static void print(NodeString head) {
	  NodeString p = head;   // create temporary p to point to head node
	  while (p != null) {
	    System.out.print(p.info + " --> ");
	    p = p.next;
	  }
	  System.out.println(p); // this line for print "null"
	}
  
  public static void main (String[] agrs) {
		
	  //create 3 nodes with string data
	  NodeString head = new NodeString("A");  //head node is the first node
	  appendNodes(head, "B"); //second node
	  appendNodes(head, "C");
	  
	  //display Linkedlist with all nodes
	  print(head);
	  
	  //reverse all nodes
	  NodeString h = reverseList(head);
	
	  System.out.print("After reverse, return prev: ");
	  print(h);
  	}
}

