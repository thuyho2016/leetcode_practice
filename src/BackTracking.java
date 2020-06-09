/*
 * What is Backtracking Programming?
 * 
 * Recursion is the key in backtracking programming. We backtrack to find the solution
 * 
 * We start with one possible move out of many available moves and try to solve the problem.
 * if we are able to solve the problem with the selected move then we will print the solution
 * else we will backtrack and select some other move and try to solve it.
 * If none if the moves work out, we will claim that there is no solution for the problem.
 * 
 * 
 * Genaralized Algorithm:
 
 Pick a starting point.
   while(Problem is not solved)
      For each path from the starting point.
         check if selected path is safe, if yes select it
         and make recursive call to rest of the problem
         If recursive calls returns true, 
           then return true.
         else 
           undo the current move and return false.
      End For
 If none of the move works out, return false, NO SOLUTION.
 
 
 Summary: 
 1. Make change
 2. Recursive
 3. Undo change
 */
public class BackTracking {

}
