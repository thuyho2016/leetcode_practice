package sort;

/* 
  
 What is Max Heap? and Min Heap?
 A Binary Heap is a Complete Binary Tree where items are stored in descending or ascending order. 
 - In a Max Heap, parent node is always greater than or equal to child nodes.
 - In a Min Heap, parent node is always smaller than or equal to child nodes. 
 
 Sort elements using Heap Sort
 * To sort the elements in ascending order, I create a Min Heap
 * To sort the elements in descending order, I create a Max Heap
 * 
 * Input: int arr[] = {12, 11, 13, 5, 6, 7};
 * Sorted array is  5 6 7 11 12 13  (use Min Heap)
 * 
 * Steps:
 * 1. build Max heap
 * 2. transform the heap into Min Heap
 * 3. delete the root node
 * 4. put the last node of the heap in root position
 * 5. repeat from step 2 till all nodes are covered
 
  To get child, take first Index * 2 and 2 * i + 1 
    int largest = i; // Initialize largest as root of the heap
    int l = 2*i + 1; // left = 2*i + 1
    int r = 2*i + 2; // right = 2*i + 2

*/
public class MaxHeapSort
{
	public void sort(int arr[])   // arr[] = [12, 11, 13, 5, 6, 7]
	{
		int n = arr.length;  //n=6

		// Build heap (rearrange array)
		for (int i = n / 2 - 1; i >= 0; i--) { //i =2 , i =1, i = 0
			printArray(arr);
			maxHeapify(arr, n, i);
		}
		
		// One by one extract an element from heap
		for (int i= n-1; i>= 0; i--)  // i = 5, 4, 3, 2,1,0
		{
			// Move current root to end
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;

			// call max heapify on the reduced heap 
			maxHeapify(arr, i, 0);
		}
	}

	// To heapify a subtree rooted with node i which is an index in arr[]. n is size of heap
	void maxHeapify(int arr[], int n, int i)  //n = 6 , i = 2  
	{
		int largest = i; // Initialize largest as root i = 0 ( in a max heap, root must be largest node)
		int l = 2*i + 1; // left = 2*i + 1 = 5
		int r = 2*i + 2; // right = 2*i + 2  = 6

		// If left child is larger than root
		if (l < n && arr[l] > arr[largest])  // 5< 6 && arr[5] > arr[2] --> 7 > 13 ? , so skip
			largest = l;

		// If right child is larger than largest so far
		if (r < n && arr[r] > arr[largest])
			largest = r;

		// If root is not largest, then swap
		if (largest != i)
		{
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;

			// Recursively heapify the affected sub-tree
			maxHeapify(arr, n, largest);
		}
	}
	
	// to print array 
	static void printArray(int arr[])
	{
		for (int i=0; i< arr.length; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	public static void main(String args[])
	{
		int arr[] = {12, 11, 13, 5, 6, 7};

		MaxHeapSort mh = new MaxHeapSort();
		mh.sort(arr);

		System.out.print("Sorted array is: ");
		printArray(arr);
	}

}
