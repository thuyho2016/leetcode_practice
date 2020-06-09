package sort;
/* In a min heap, the parent node is always smaller than or equal to its child node
 * 
 * N is index of a parent node
 * 2N is left child node
 * 2N + 1 is right child node
 * 
 * array:  3, 1, 6, 5, 2, 4
 * Build Min Heap:    - swap 3 & 1, swap 3 & 2, swap 6 & 4
 *      1
 *   2     4
 * 5   3  6 
 *  
 */

public class MinHeap {

    public int heap_size;
    public int maxsize;
    private int[] heap;

    public MinHeap(int maxsize) {
        this.maxsize = maxsize;
        this.heap_size = 0;
        heap = new int[this.maxsize];
    }
    
    public int parent(int i) {
    		return i/2; // return parent node
    }
    
  //get index of left child of node at index i
    public int left(int i) {
		return (2 * i);  // return left child node
    }
    
    //get index of right child of node at index i
    public int right(int i) {
		return (2 * i + 1); //return right child node
    }
    
    //insert new element to the binary heap tree
    // if new element is smaller than parent, then swap
    public void insert(int e) {
    	heap[++heap_size] = e; // increase size to 1
    	int current = heap_size;
    	
    	while (heap[current] < heap[parent(current)]) {
    		swap(current, parent(current));
    		current = parent(current);
    	}
    	
    }
    public void minHeap() {
    	for (int i = heap_size / 2; i >= 1; i--) {
    		minHeapify2(i);
    	}
    }
    
    public int getMin() {
    	return heap[0];
    }
    private void minHeapify2(int i) {
    	int l = left(i);
    	int r = right(i);
    	int smallest = i;
    	
    	//left child is smaller than root
    	if (l < this.heap_size && heap[l] < heap[i] )
    		smallest = l;
    	if (r < this.heap_size && heap[r] < heap[smallest] ) // right child is smaller than smallest
    		smallest = r;
    	if (smallest != i) {
    		//
    		swap2(heap[i], heap[smallest]);
    		minHeapify2(smallest);
    	}
    }
    
    private void swap2(int x, int y) 
    { 
        int temp = x; 
        x = y; 
        y = temp; 
    }
    
    private void minHeapify(int pos)
    {
       if(!isLeaf(pos))
       { 
            if ( heap[pos] > heap[left(pos)]  || heap[pos] > heap[right(pos)])
            {
                if (heap[left(pos)] < heap[right(pos)])
                {
                    swap(pos, left(pos));
                    minHeapify(left(pos));
                }else
                {
                    swap(pos, right(pos));
                    minHeapify(right(pos));
                }
            }
       	}
    }
    
    private void swap(int x, int y)
    {
        int tmp;
        tmp = heap[x];
        heap[x] = heap[y];
        heap[y] = tmp;
    }
    public boolean isLeaf( int i) {
    	if ( i >= (heap_size / 2) && i <= heap_size) {
    		return true;
    	}
		return false;
    }
    
    private void print() {
    	for (int i = 1; i <heap_size / 2 ; i++) {
    		System.out.print("PARENT: " + heap[i] + " Left child: " + heap[2 * i]
    				+ " Right child: " + heap[ 2 * i + 1]);
    		System.out.println();
    	}
    }
    
    
    public static void main(String args[]) {
    	 System.out.println("The Min Heap is ");
         MinHeap minHeap = new MinHeap(20);
         minHeap.insert(5);
         minHeap.insert(3);
         minHeap.insert(17);
         minHeap.insert(10);
         minHeap.insert(84);
         minHeap.insert(19);
         minHeap.insert(6);
         minHeap.insert(22);
         minHeap.insert(9);
         
         minHeap.print();
         minHeap.minHeap();
  
         minHeap.print();
         System.out.println("The Min val is " + minHeap.getMin());
         
         //Expect output:
     /*  PARENT: 3 Left child: 5 Right child: 6
         PARENT: 5 Left child: 9 Right child: 84
         PARENT: 6 Left child: 19 Right child: 17
         PARENT: 9 Left child: 22 Right child: 10
         */
         
    }
}
