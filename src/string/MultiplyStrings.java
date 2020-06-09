package string;

/*
 * 43. Multiply Strings
 * https://leetcode.com/problems/multiply-strings/
 
 Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"

 */
public class MultiplyStrings {

	public static String multiply(String num1, String num2) {
		int m = num1.length(), n = num2.length();
		int[] cal = new int[m + n];
		String res = new String();

		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				
				int p1 = i + j;
				int p2 = i + j + 1;

				int cur = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + cal[p2];
				cal[p2] = cur % 10;
				cal[p1] += cur / 10;
			}
		}

		for (int num : cal) {
			if (res.length() != 0 || num != 0)
				res += num;
		}

		return res.length() == 0 ? "0" : res;
	}

	public static void main(String[] args) {

		String num1 = "2";
		String num2 = "3";

		System.out.println(multiply(num1, num2));

		num1 = "12";
		num2 = "43";

		System.out.println(multiply(num1, num2));
	}
}
