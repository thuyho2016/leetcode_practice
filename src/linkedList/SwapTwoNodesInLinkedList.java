package linkedList;

/**
 * Swap two nodes in  link list. https://www.youtube.com/watch?v=bSORX7TcFvs
 * 3 Test cases
 * 1. Either one of the nodes is head node . A --> B --> C --> D ->null    A is head node and C is not 
 * 2. Either one of the nodes is last node.  A --> B --> C --> D ->null    Swap B and D. D is Last node
 * 3. Both are internal nodes.               A --> B --> C --> D --> E     B and D are internal nodes
 * 
 * Example swap A and D
 * prevX=null 	 pX //line 53, 54   
 *               A ---> B ---> C --> D --> E --> F --> NULL
 *
 *               p //line56
 * prevX=null    A ---> B ---> C --> D --> E --> F --> NULL
 * 
 *               prev   p  //line 60,61 - p became prev and p goes to p.next
 *               A ---> B ---> C ---> D --> E --> F --> NULL
 *                 
 *                      prev   p  //go to while loop again - line 60,61
 *               A ---> B ---> C ---> D --> E --> F --> NULL
 *               
 *  prevX=null   pX            prevY  pY  line 53,54  - when p.data ==d, not satisfy while loop condition  
 *  			 A ---> B ---> C ---> D --> E --> F --> NULL
 *  Now,  pX points to A and pY points to Y. We swap two nodes. 
 *   	  Before I delete the link between D and E, I need temp pointer to store E->F->null
 *   			A ---> B ---> C ---> D    E --> F --> NULL
 *   							          temp points to E //temp = pY.next;
 *   
 * prevX=null   pX     py.next will point to B. // pY.next = pX.next;
 *              		_ _ _ _ _ _ _
 *                     |             |
 *   			A ---> B ---> C ---> D    E --> F --> NULL
 *   			
 *  To have answer A point to E, I need px.next point to temp that has E
 *              A      B ---> C ---> D    E --> F --> NULL
 *              |_ _ _ _ _ _ _ _ _ _ _ _ _|   // pX.next = temp;
 *   
 *   Swap is done, but we don't know which node is new and is head. So, we need to check if prevX or prevX is null
 *   Before swap, pX is head and prevX == null. After swap, so pY became head  // pY = head;
 *   the link between C and D should go to A  prevY.next to A  //prevY.next = pX;
 *  			  _ _ _ _ _ _ _  
 *               |			   |
 *   			 A      B ---> C    D    E --> F --> NULL
 *   			 					head
 *   Answer:     D ---> B ---> C ---> A --> E --> F --> NULL  
 */

public class SwapTwoNodesInLinkedList {
	//Node head;
	private class Node {
		private int data;
		private Node next; // to point to next node in the list
		private Node before;
		
		public Node (int data) {
			this.data = data;
			this.next = null;
			this.before = null;
		}
	}
	
	//1. search two nodes in Linked List without swapping data -  code are for all test cases
	 public void search(int x, int y, Node head){
		 Node p;
		 Node prev;
		 p = head;
		 prev = null;
		 
		 while (p != null && p.data != x) { // when p.data == x so the condition is not satisfy, not go inside while loop
			 prev = p;
			 p = p.next;
		 }
		 Node px = p;
		 Node prevX = prev;
		 
		 p = head;
		 prev = null;
		 
		 while (p != null && p.data != y) { // when p.data != d so the condition is satisfy, go inside while loop
			 prev = p;
			 p = p.next;
		 }
		 Node py = p; 
		 Node prevY = prev;
		 
	 }
	
	 //swap 2 nodes
    public void swap(int x, int y, Node head){
        if(head== null ){
            throw new IllegalArgumentException();
        }
        
        //search (x, y, head);
        
        Node temp;
        
        temp = pY.next;
        pY.next = pX.next;
        pX.next = temp;
        
        if(prevX == null) { ///Case pX is head node - if prevX is null, pY should be head after swap
        	pY = head;
            prevY.next = pX;
        }
        
        if (prevY ==null) { //Case pY is head node -  if prevY is null, pX should be head
        	pX = head;
        	prev.next = prevY;
        }
        
        /** Case 2 and 3 - Swaps Internal/End nodes
          Swap b and E
          prevX  pX            prevY  pY
          A ---> B ---> C ---> D ---> E ---> F ---> NULL
          Delete The link between E and F, use temp point to F, pY point to C
          pX will point to temp.
          prevX pint to E and prevY point to pX that is B
          Ans: A ---> E ---> C ---> D ---> B ---> F ---> NULL
         */
        if (prevX != null && prevY != null) {
        	prevX->next = pY;
        	prevY->next = pX;
        }
        
        
    }
    
	//add in the end
    public Node addNode(Node head, int data){
    	Node newNode = new Node(data);
        if(head == null){
            return newNode;
        }
        
        Node current = head;
        while(current.next != null){
            current = current.next;
        }
        current.next = newNode;
        newNode.before = current;
        return head;
    }
    
    public void print(Node head){
        while(head != null){
            System.out.print(head.data + " ");
            head = head.next;
        }
    }
    
    public static void main(String args[]){
    	 SwapTwoNodesInLinkedList ll = new SwapTwoNodesInLinkedList();
         Node head = null;
         head = ll.addNode(head,1); 
         head = ll.addNode(head,2);
         head = ll.addNode(head,3);
         head = ll.addNode(head,4);
         head = ll.addNode(head,5);
         ll.print(head);
 
         ll.swap(1, 3, head);
         ll.print(head);
    }
}
