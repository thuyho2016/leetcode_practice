import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Hackerrank Buying show tickets Optimization
 * 
here are n people standing in line to buy show tickets.Due to high demand, the venue sells tickets according to the following rules:

The person at the head of the line can buy exactly one ticket and must then exit the line.
if a person needs to purchase additional tickets,they must re-enter the end of the line and wait to be sold their next ticket(assume exit and re-entry takes zero seconds).

Each ticket sale takes exactly one second.
We express initial line of n people as an array, tickets = [tickets0, tickets1 ... ticketsN-1], where ticketsi denotes the number of tickets person i wishes to buy. 
If Jesse is standing at a position p in this line, find out how much time it would take for him to buy all tickets. 
Complete the waiting time function in the editor below. It has two parameters:

An array, tickets, of n positive integers describing initial sequence of people standing in line. 
Each ticketsi describes number of tickets that a person waiting at initial place.
An integer p, denoting Jesse's position in tickets.

Input 5 2 6 3 4 5 2  , Output 12  

Input 4 5 5 2 3 3 , Output 11

 */


public class BuyShowTickets {
	// p = position
	/** Solution 2: Compiled successfully. All test cases passed!
	
	We divide people in 2 groups:
	1. Those stands before pth person.
	2. Those stands behind pth perosn.
	
	Let's call times - the number of move that pth person has to take to buy tickets.

	Now considering the first group [tickets0, tickets1, ..., ticketsP-1], 
	
	if there is a person i who needs to buy the number of tickets smaller than the pth person, then just simply add ticket.get(i) to times 
	(the duration that pth person has to wait for the person i until he gets out of the line ). 
	Else, the amount of buying tickets of person i is bigger than person pth, add ticket.get(p) to times.

	Now considering the second group - 
     People who stand behind the pth person [ticketsP+1, ticketsP+2, ..., ticketsN]. 
     Considering person t (t > p), we add <ticket t> to times if ticketT < ticketP. 
     Unless person t buys tickets less then person p, skip the last round so that just add <ticket p - 1 > to times	
	 */
	
	public static long waitingTime2( List<Integer> tickets, int p) {
	    long times = 0;
	    List<Integer> temp = new ArrayList<>(); //creating this array to check whether the person at i index buy tickets less than person at p index
	   
	    for(int i = 0; i < tickets.size(); i++ ) {
	       temp.add(tickets.get(i) - tickets.get(p));  //[-1, 3, 0, 1, 2]
	    }
	    for(int i = 0; i < tickets.size(); i++ ) {
	       if(temp.get(i) < 0) 
	    	   times += tickets.get(i);
	       else {
	          if(i <= p) // person who stand before the pth person - index i = 0, i = 1
	        	  times += tickets.get(p);
	          else        //person who stand behind the pth person - index i = 3, i = 14
	        	  times += tickets.get(p) - 1;
	       }
	    }
	    return times;
	} 
	
	public static long waitingTime3( int[] tickets, int p) {
	    long times = 0;
	    int[] temp = Arrays.copyOf(tickets, tickets.length); //creating this array to check whether the person at i index buy tickets less than person at p index
	   
	    for(int i = 0; i < tickets.length; i++ ) {
	       temp[i] = tickets[i] - tickets[p];
	    }
	    for(int i = 0; i < tickets.length; i++ ) {
	       if(temp[i] < 0) 
	    	   times += tickets[i];
	       else {
	          if(i <= p) 
	        	  times += tickets[p];
	          else 
	        	  times += tickets[p] - 1;
	       }
	    }
	    return times;
	} 
	
	
	/**
Solution: - All the test cases on HackerRank are passed.

1. Divide list into two halves. People standing ahead of Jesse and persons standing behind Jesse.
2. Check with each person in both the halves - how many tickets that person wants to buy.

3. Lets consider first half
4. If the person wants to buy less number of tickets than that of Jesse - the person will visit the ticket window till he buy the tickets before Jesse. 
    So add his number of tickets to the total unit time
5. If the person wants to buy more or the equal tickets than Jesse then he will visit ticket window before Jesse exactly the same number of times that Jesse visits the ticket window. 
   So add Jesse's tickets count to the total unit time which is equal to the person's ticket count which he buys before Jesse

6. Now consider second half -
7. If the person standing behind Jesse wants to buy more or equal tickets than Jesse, he will visit ticket window one less time than Jesse. 
   So add (Jesse's ticket count - 1) to the total unit time
8. If the person standing behind Jesse wants to buy less tickets than Jesse, then the person will visit ticket window until he buys all his tickets. 
   So add persons total ticket count to the total unit time.
9. Finally, add Jesse's ticket count to the total unit time too, because Jesse will also visit the ticket window until he buys all the tickets that he wants

e.g. Five persons are standing in a queue. Their ticket count is given in the list below. Jesse is standing at 3rd position (list index = 2)

[2,6,3,4,5]

first half = [2,6] second half = [4,5]

Consider first half

  1. Person#1 wants to buy 2 tickets. Jesse's count (3) is greater than 2. So this person will definitely visit ticket window twice before Jesse. Hence total_unit_time = 2

  2. Person#2 wants to buy 6 tickets. Jesse's count (3) is less than 6. So this person will visit ticket window 3 times before Jesse. So total_unit_time = 2+3 = 5

Consider second half  

  1. Person#1 wants to buy 4 tickets. Jesse's count (3) is less than 4. Now, Jesse will buy his first ticket then the person will get a chance to buy his first ticket. 
      But then Jesse will have to wait for 2 more turns to buy remaining 2 tickets after this person. So total_unit_time = 2+3+(3-1) = 7

  2. Person#2 wants to buy 5 tickets. Again Jesse will buy his first ticket and will wait for his remaining two turns until this guy buys two tickets. So total_unit_time = 2+3+2+(3-1)= 9

*/
	public static long  waitingTime(List<Integer> tickets, int p) {
	    long total_steps = tickets.get(p); // initial times = Jess ticket = 3
	    
        // first half = [2,6] 
        for ( int i = 0; i < p; i++ ) {
	        if ( tickets.get(i) < tickets.get(p) ) 
	            total_steps += tickets.get(i);  //total_steps = 3 + 2 = 5, 
	        else
	            total_steps += tickets.get(p);  // 6 < 3, total_steps = 5 + 3 = 8
	    }
          
	    // second half = [4,5] 
	    for ( int j = p + 1; j < tickets.size(); j++ ) {
	        if ( tickets.get(j) < tickets.get(p) ) 
	            total_steps += tickets.get(j);
	        else									// person behind Jess 4 > 3  , 5 > 3
	            total_steps += tickets.get(p) - 1;  // 4 > 3 --> total_steps = 8 + (3 -1) = 10,  5 > 3 --> total_steps = 10 + (3-1) = 12
	    }
		
		return total_steps ;
    } 
	
	public static void main(String[] args) {
		List<Integer> tickets = Arrays.asList(2,6,3,4,5);// new ArrayList<>();
		
		System.out.println(waitingTime(tickets, 2));
		
		
		System.out.println(waitingTime2(tickets, 2));
		
		int[] tickets2 = {2,6,3,4,5};
		long result = waitingTime3(tickets2, 2); //12
		System.out.println(result);
	}
}
