import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;

/* 406. Queue Reconstruction by Height
 * https://leetcode.com/problems/queue-reconstruction-by-height/
 * 
 Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k),
 where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. 
 
 Write an algorithm to reconstruct the queue.
 

 Input: (h, k) --> k = number of people has height >= h
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */


public class ReconstructQueueByHeight {
	
/*	public static class Node {
        int[] person;
        Node next;

        public Node(int[] p) {
            person = p;
        }
    }	
*/	
	//Way2: sort and use LinkedList
	//pick up the tallest guy first
    //when insert the next tall guy, just need to insert him into kth position
    //repeat until all people are inserted into list
	
	public static int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0) return people;
        
        Arrays.sort(people, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o2[0] - o1[0];
            }
        });
        
        List<int[]> list = new LinkedList<>();// List<int[]> list = new ArrayList<>();
        
        for (int[] p: people) {
            list.add(p[1], p);
        }
        
        int n = people.length;
        //convert List to double array [][]
        return list.toArray(new int[n][2]); // convert List to Array [][]
    }

	public static void main(String[] args) {
		int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
		
		int[][] res = reconstructQueue(people);
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res.length; j++) {
				System.out.println("Restructed Queue: " + res[i][j]);
			}
		}
    }
}