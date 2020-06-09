import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* 
 * 582. Kill Process ( Level = medium)
https://leetcode.com/problems/kill-process/


Given n processes, each process has a unique PID (process id) and its PPID (parent process id).

Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. 
Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.

We use two list of integers to represent a list of processes, where the first list contains PID for each process and 
the second list contains the corresponding PPID.

Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end.
You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.

Example 1:
Input: 
pid =  [1, 3, 10, 5]
ppid = [3, 0, 5, 3]
kill = 5

Output: [5,10]

Explanation: 
           3
         /   \
        1     5
             /
            10

Kill 5 will also kill 10.

        HashMap<Integer, List<Integer>> children = new HashMap<>();
        
        for (int i = 0; i < pid.size(); ++i) {
            int parent = ppid.get(i);
            
            if (parent != 0) {
                if (!children.containsKey(parent)) {
                    children.put(parent, new ArrayList()); //create parent as key, new ArrayList for children 
                }
                children.get(parent).add(pid.get(i));
            }
        }
        
 */

public class KillProcess
{
	//BFS
	public static List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
		
        HashMap<Integer, List<Integer>> children = new HashMap<>();
                
        for(int i = 0; i < ppid.size(); i++) {
        	//add parent ( ppid.get(i) as key , create arrayList to add pid.get(i) as value
        	
            children.computeIfAbsent(ppid.get(i), key -> new ArrayList<>()).add(pid.get(i)); //{0=[3], 3=[1, 5], 5=[10]}
        }
       
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(kill);  //Insert 5 into this queue 
        
        while(!queue.isEmpty()) {
            int id = queue.poll();  //Retrieves and removes the head of this queue. 5 is removed , next round 10 is removed
            res.add(id);   //5 , 10
        
            //children of 5 is 10 , 10 has no children 
			
            if (children.containsKey(id)) {
                
	            for(int child: children.get(id)) { //[10] , empty children.get(10)
	                queue.offer(child);  //Inserts the specified element into this queue. 10 is inserted
	            }
            }
        }
        return res; 
    }
        

	
	public static void main(String args[])
	{
		List<Integer>  pid =  new ArrayList<>(); //[1, 3, 10, 5]
		pid.add(1);
		pid.add(3);
		pid.add(10);
		pid.add(5);
		
		List<Integer>  ppid =  new ArrayList<>(); //[3, 0, 5, 3]
		ppid.add(3);
		ppid.add(0);
		ppid.add(5);
		ppid.add(3);
		
		int kill = 5;
		
		System.out.println("Output " + killProcess(pid, ppid, kill));
	}
	
	
}