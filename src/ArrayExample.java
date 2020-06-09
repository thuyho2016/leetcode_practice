
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Map<K,V> is an interface, HashMap<K,V> is a class that implements Map
 
public class ArrayExample  {
	public static void main (String[] args)
    {
		int arr[][] = { {2,7},{3,6},{7,4} };  // array of array
		for (int i=0; i< arr.length ; i++) {
			for (int j=0; j < arr[0].length ; j++) {
                System.out.print(arr[i][j] + " ");
			}
            System.out.println();
        }
		
		String[] words = {"Jack", "and", "Jill"};  
        List<String> wordList = Arrays.asList(words);  //convert String[] to List
        for (String w: wordList) {
        	System.out.println(w);
        }
        
    //  int[][] grid = {{1,1,0,0}, {0,0,1,0}, {1,0,1,1}};		 
	  List<Integer> l1 = Arrays.asList(1,1,0,0);
	  List<Integer> l2 = Arrays.asList(0,0,1,0);
	  List<Integer> l3 = Arrays.asList(1,0,1,1);
	  
	  List<List<Integer>> grid = new ArrayList<>();
	  grid.add(l1);
	  grid.add(l2);
	  grid.add(l3);
	  
	 //convert List of List Integer to 2D array
	 int[][] input = new int[grid.size()][grid.get(0).size()];
	 for(int i=0; i<grid.size(); i++){
		for (int j = 0; j < grid.get(i).size(); j++) {
			input[i][j] = grid.get(i).get(j);
		}
	 }
	 System.out.println(Arrays.deepToString(input)); //[[1, 1, 0, 0], [0, 0, 1, 0], [1, 0, 1, 1]]
    	     
    		 
    //convert 2d array to List of List Integer
    Integer[][] dataSet = new Integer[][] {{1, 2}, {3, 4}, {5, 6}};

    List<List<Integer>> l = Arrays.stream(dataSet).map(Arrays::asList).collect(Collectors.toList());
    System.out.println(l);
    
    // convert ArrayList to array
    ArrayList<Integer> myList =new ArrayList<Integer>();
	myList.add(1);
	myList.add(2);
	myList.add(3);
  
	Object[] array = myList.toArray();
	System.out.println("First element of array" + array[0]);
	
	
	
	 
	ArrayList<String> list=new ArrayList<String>();//Create arraylist to hold a list of string
	list.add("Apple");   //Adding object in arraylist  
	list.add("BlueBerry"); 
	
	//1. print element By Iterator interface.
	Iterator<String> iter = list.iterator();
	while (iter.hasNext()) {
		System.out.println(iter.next());
	}
	
	//2. print element By for loop
	for ( String element : list) {
		System.out.println(element);
	}
      		
	 Student s1= new Student("Thuy Ho ",23);  
	 Student s2= new Student("ViVi Tran",21);  
	 
	 ArrayList<Student>  studentList = new  ArrayList<Student>(); // hold a list of Student objs
	 studentList.add(s1);
	 studentList.add(s2);
	 
	 //print student
	 Iterator<Student> iter1 = studentList.iterator();
	 while(iter1.hasNext()) {
		 System.out.println(iter1.hasNext());
		 iter1.next();
	 }
    }
}