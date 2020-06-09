package linkedList;

public class DoubleLinkList_Add_DeleteNode {
	Node head;
	private static class Node {
		private int data;
		private Node next; // to point to next node in the list
		private Node before;
		
				
		public Node (int data) {
			this.data = data;
			this.next = null;
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
    
	public Node addAtFront(Node head, int data){
        Node newNode = new Node(data);
        if(head == null){
            return newNode;
        }
        newNode.next = head;
        head.before = newNode;
        return newNode;
    }
    
    public void print(Node head){
        while(head != null){
            System.out.print(head.data + " ");
            head = head.next;
        }
    }
    

    public void printFrontBack(Node head){
        Node prev = null;
        while(head != null){
            System.out.print(head.data + " ");
            prev = head;
            head = head.next;
        }
        System.out.println();
        while(prev != null){
            System.out.print(prev.data + " ");
            prev = prev.before;
        }
    }
    
    //delete first element from a LinkedList
	  public Node deleteFirst(Node node) {
		  if (node != null)
		    node = node.next;
		  return node;
	 }
	  
	  
    //find specific node
    public Node find(Node head, int data){
        while(head != null){
            if(head.data == data){
                return head;
            }
            head = head.next;
        }
        return null;
    }
    
    public static void main(String args[]){
        DoubleLinkList_Add_DeleteNode dll = new DoubleLinkList_Add_DeleteNode();
        Node head = null;
        head = dll.addNode(head,1);
        head = dll.addNode(head,2);
        head = dll.addNode(head,3);
        head = dll.addNode(head,4);
        head = dll.addNode(head,5);
        dll.print(head);
        
        Node l = dll.addAtFront(head, 6);
        System.out.println("\nAdd new node 6");
        dll.print(l);
        
        //delete first node 6
        Node l2 = dll.deleteFirst(l);
        System.out.println("\nAfter deletion node 6");
        dll.print(l2);
    }
}
