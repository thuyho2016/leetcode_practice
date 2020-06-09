package linkedList;

/*
 * 19. Remove Nth Node From End of List
 Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.

Time complexity : O(L).
Space complexity : O(1)

 */

public class RemoveNthNodeFromEnd {
	//prefer to this solution
	public static Node removeNthFromEnd(Node head, int n) {
		
		//use 2 pointers
        Node cur = head;
        Node p = head;
        int count = 1;
        
        while (cur.next != null) {
            count += 1;     // increment count from 1 to 2, 3, 4, 5
            cur = cur.next; // move forward next node 1->2->3->4-> 5
            if (count > n + 1) // count = 4 > 2 + 1 = 3
                p = p.next; // count = 4: p -> node 2 , count = 5, p-> node 3
        }
        
        if (count == n)
            return head.next;
        else { // count = 5
            p.next = p.next.next; //p.next point to 5, p.next.next is null
            return head ;
        }
    }

	//Way 2:  dont use counter 
	public static Node removeNthFromEnd2(Node head, int n) {
		
		//use 2 pointers
        Node fast = head;
        Node slow = head;
   
        
        for ( int i = 0; i < n; i++) 
        	fast = fast.next;  // fast will point to 3 
        
        if (fast == null) {
        	return head.next;
        }
        while  (fast.next != null) { // when fast points to 5, fast.next == null. dont go inside while loop
            fast = fast.next;  // fast points to 4, fast points to 5
            slow = slow.next;   // slow points to 2, move slow point to 3
        }
        
        slow.next = slow.next.next; // slow is pointing to 3, to remove 4, move slow.next to slow.next.next
        
        return head ;
        
    }

	
	//problem: find nth node from end of LL
	public static Node findNthFromEnd(Node head, int n) {
		
		//use 2 pointers
        Node cur = head;
        Node p = head;
        
        int count = 1;
        
        while (count <= n -1 ) { //
            cur = cur.next; // the cur pointer moves forward next node
            count += 1; // increment count by 1
           
        }
        
        while (cur.next != null) {
        	cur = cur.next;
            p = p.next;
        }
        return p;
    }
	
	//link all nodes together to create Linked List
	public static Node append(Node head, int data) {
		if (head == null)
			return head = new Node(data);  // create a head node
		else {
			if (head.next == null) {   
				return head.next = new Node(data);  // create next node after head
			} else {
				//use recursive
				return append(head.next, data);  
			}
		}
	}


    //2. Given a Node, print all nodes	
	//loop each element till end of the list, cur.next to move to next node
   //last node point to null
	public static void display(Node head) {
		if (head ==null) {
			return;
		}
		Node current = head;   // create temporary current node to point to head node
		
		while (current !=null) {
			System.out.print(current.val + " -> ");  //print current node with data
			current = current.next;      // move to next node
		}
		System.out.println(current); //last node is null 
	}
	
	public static void main (String[] agrs) {
		//create a link list
		Node head1 = new Node(1);
		Node nextNode = append(head1 , 2);
		nextNode = append(head1 , 3);		
		nextNode = append(head1 , 4);
		nextNode = append(head1 , 5);
		
		display(head1); //display Linkedlist with all nodes

		
		removeNthFromEnd(head1, 2) ;
		System.out.print("After deletion 2nd node from end: ");
		display(head1);
		
		
		Node nth = findNthFromEnd(head1, 2) ;
		System.out.print("Find Nth node from end: ");
		display(nth);

	}	
	
}
