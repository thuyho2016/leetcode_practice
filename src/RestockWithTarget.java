import java.util.Arrays;
import java.util.List;

/*
 * List of containers 
 * itemCount = [10,20,30,40,15]
 * target = 80
 * 
 * Manager start buying at index =0, with itemCount = 10 + 20 + 30+40 = 100
 * so, the number sold = total of purchased - target = 100 - 80= 20 units
 * 
 * if target = 130, total of purchase = 10 + 20 + 30 + 40 + 15 = 
 * so target - purchases = 130 - 115 = 15 additional units must be purchased
 * 
 * Write a function must return the number of units that must be resold or must be purchased
 * 
 */
public class RestockWithTarget {
	public static int restock (int[] itemCount, int target) {
		int keepAdding = 0;
		int units = 0;
		for ( int i = 0; i < itemCount.length ; i++) {
			keepAdding += itemCount[i];
			
			if (keepAdding >= target) break;
		}
		
		return keepAdding >= target ? keepAdding - target : target - keepAdding;
		/*if (keepAdding >= target) {
			units = keepAdding - target;
		} else {
			units = target - keepAdding;
		}
		return units; */
	}
	
	public static void main(String[] args) {
		
	   int[] nums = {10,20,30,40,15};
	   int res = restock(nums, 80);
	   System.out.println("Units: " + res);  //20
	   res = restock(nums, 130);
	   System.out.println("Units: " + res);  //15
	}
}
