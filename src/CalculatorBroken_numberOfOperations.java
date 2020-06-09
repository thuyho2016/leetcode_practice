/* 991. Broken Calculator
 * https://leetcode.com/problems/broken-calculator/
 * On a broken calculator that has a number showing on its display, we can perform two operations:

Double: Multiply the number on the display by 2, or;
Decrement: Subtract 1 from the number on the display.

Initially, the calculator is displaying the number X.

Return the minimum number of operations needed to display the number Y.



Example 1:

Input: X = 2, Y = 3
Output: 2

Explanation: Use double operation and then decrement operation {2 -> 4 -> 3}.

Example 2:

Input: X = 5, Y = 8
Output: 2
Explanation: Use decrement and then double {5 -> 4 -> 8}.

Example 3:

Input: X = 3, Y = 10
Output: 3
Explanation:  Use double, decrement and double {3 -> 6 -> 5 -> 10}.

Solution:

Case1: X > Y   multiple by 2 will only bring us far away from target...
 *     so each step, we can only subtract 1 from X to get the target value... 
 *     thus we just need to return X - Y;
 *     
Case 2: Y > X   we can think reversely...
 *     we can achieve Y from the previous number by either of two cases 
 *     (a) pre - 1 = Y;
 *     (b) pre * 2 = Y
 *     which step to take is dependent on if Y is even or odd...
 *     
 *     if (Y is even), pre = Y / 2;
 *     if (Y is odd), pre = Y + 1
 *     then we reset Y as pre...
 *     do the same thing, until Y <= X... which is the first case...

Actually, what we do is:
If Y is even, Y = Y / 2
If Y is odd, Y = (Y + 1) / 2
We reduce Y with least possible operations, until it's smaller than X.

 */
public class CalculatorBroken_numberOfOperations {
	//Time Complexity: O(logY).
    //Space Complexity: O(1).
	public static int brokenCalc(int X, int Y) {
        int countOper = 0;
        
        while ( X < Y) {
            if ((Y %2 ) == 1) { // is odd number, then increment
                Y += 1;
                countOper++;
            } 
            // Y is even, then divide by 2 
            Y = Y/2;  //Y /= 2;
            countOper++;
        }
        return countOper + (X - Y);  // Y < X
    }
	
	public static void main(String[] args) {		

		System.out.println(brokenCalc(2, 3)); //2
		
		System.out.println(brokenCalc(5, 8)); //2
	}
}
