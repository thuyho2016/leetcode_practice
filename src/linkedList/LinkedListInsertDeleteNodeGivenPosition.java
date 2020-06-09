package linkedList;


/* 1. Give a head node, create Nodes in LinkedList:  10-->8-->1-->11--> null
 * 
 * 2. Given a Node, print all elements(nodes) it hold
 * 3. Insert a new node at position 3 and display new linkedlist
 * 4. Delete a new node from LinkedList at a given position
 * 5. delete node by given value
 * 6. Find length of link list
 * 7. Search for a node in a linked list
 */

public class LinkedListInsertDeleteNodeGivenPosition {
	
	Node head;
	private static class Node {
		private int data;
		private Node next; // to point to next node in the list
		
		public Node() {
			this.data = 0;
			this.next = null;
		}
		
		public Node (int data) {
			this.data = data;
			this.next = null;
		}
	}

	//1. link all nodes together
	public void appendNodes(int data) {
		if (head == null) {
			head = new Node(data);
			return;
		}
		Node current = head;
		while (current.next !=null) {
			current = current.next;
		}
		current.next = new Node (data);
	}	
	  
	//2. Given a Node, print all elements it hold
	public void display(Node head) {
		if (head ==null) {
			return;
		}
		Node current = head;   // create temporary current to point to head node
		//loop each element till end of the list
		//last node point to null
		while (current !=null) {
			System.out.print(current.data + " --> ");  //print current node with data
		    //move to next node/element
			current = current.next;
		}
		System.out.println(current); //here current is null node
	}
	
	//3. Insert a new node in LinkedList at a given position
	public Node insertNode( Node head, int data, int position) {
		//check position is in boundary
		System.out.println("Size of LinkedList: " +  length(head));
		
		Node newNode = new Node(data);  //create new node with data integer 15
		
		if (position < 1) {
			System.out.println("invalid position");
			return head;
		}
		
		else if (position ==1) {
			newNode.next = head;
			return newNode;
		} else {
			Node prev = head;
			int count =1;
			while (count < position - 1) {
				prev = prev.next;
				count++;
			}
			Node current = prev.next; // temporary node current
			newNode.next = current;
			prev.next = newNode;
			return head;
		}
	}
	
	/* 4. Delete a new node from LinkedList at a given position
	 10 --> 8 --> 15 --> 11 --> null , 
	 delete node of 15 at position 3 ==> Final list:  10 --> 8 --> 11
	 prev node is  head node 
	*/
	public Node deleteNode(Node head, int position) {
		//check position is in boundary
	    int size = length(head);
	    System.out.println("Size of LinkedList: " +  size);
	    
		if (position > size || position < 1) {
			System.out.println("cannot delete - invalid position");
			return head;
		}
		
		if (position == 1) {  //delete first node that is head
			Node temp = head;
		    head = head.next;
			temp.next = null; //remove head from list
			return temp;
			
		} else {
			Node prev = head;  //create prev node that points to  head node
			int count = 1; //keep track prev node
			while (count < position - 1) { // count = 1 < 3-1 = 2,
				prev = prev.next;    // prev is 8 
				count++;
			}
			Node current = prev.next; // temporary node current  is 15
			prev.next = current.next; // point to 11
			current.next = null; //delete 15
			return current;
		}
	}
	
	//5. delete node by given value
	public void deleteWithValue( int data) {
		if (head ==null) return;
		//update head data
		if (head.data ==data) {
			head = head.next;
			return;
		}
		Node current = head;
		while (current !=null) { //go each node
			if ( current.next.data == data) {
			current.next = current.next.next;  // skip next node, move to next.next
			return;
			}
			current = current.next; //move on to next element
			
		}
	}
	
	//6. Given a Node head, find out length of linked list
	public int length(Node head) {
		if (head ==null) {
			return 0;
		} 
		int count = 0; //create a count variable to hold length
		Node current = head; //point current node to head
		while (current != null)  { //loop each element until list ends
			count++;
			//move to next node
			current = current.next;
		}
		return count;
	}

	//7. Search for an element in a linked list
	public Node searchNode( Node head, int data) {
		if(head == null) { return null; }
		Node current = head;
		while (current !=null) {
			if (data == current.data) {
				return current;
			}
		}
		return current;
		
	}
	
	public static void main (String[] agrs) {
		
		//1. create a linked list without method
		//initialize the head Node
		Node head = new Node(10);  //create first node with data '10'. It is called a Head node.
		//create second node
		Node second = new Node(8); 
		Node third = new Node(1);
		Node fourth = new Node(11);
		
		// attach nodes together to become a linked list 10--->8-->1-->11--> null
		head.next = second; //10 --> 8
		second.next = third; //10 --> 8 --> 1
		third.next = fourth; //10 --> 8 --> 1--> 11 --> null
	
		
		//create object of CreateLinkedList class
		LinkedListInsertDeleteNodeGivenPosition singleLinkedList = new LinkedListInsertDeleteNodeGivenPosition();
		System.out.println("Print all node from LinkedList");
		singleLinkedList.display(head);
		 
		//1. link all nodes to become a linked list
	/*	Node head2 = new Node(10);
		singleLinkedList.appendNodes(10);
		singleLinkedList.appendNodes(8);
		singleLinkedList.appendNodes(1);
		singleLinkedList.appendNodes(11); */
		
		//2. Print all nodes from LinkedList that it hold
		System.out.println("Print all node from LinkedList");
		singleLinkedList.display(head);
			
		//3. insert a new node at position 3 and display new linkedlist after insertion
		Node newList = singleLinkedList.insertNode(head, 15, 3);
		System.out.print("Insert 15 - New List after insertion: ");
		singleLinkedList.display(newList); //10 --> 8 --> 15 --> 1 --> 11 --> null
		
		//4. Delete a new node from LinkedList at a given position
		Node deleteNode = singleLinkedList.deleteNode(head, 2);
		System.out.println("Delete node at position 2:  "+ deleteNode.data);
		System.out.print("After deletion: ");
		singleLinkedList.display(head);
		
		Node n = singleLinkedList.searchNode(head,8);  //not working
		System.out.println("Search node 8:  " + n.data);
		
	}
}

	  
	