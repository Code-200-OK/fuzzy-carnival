package others;

public class ProductOfArrayExceptSelf {
	public class Solution {
		public int[] productExceptSelf(int[] nums) {
			int n = nums.length;
			int[] res = new int[n];
			res[0] = 1;
			for (int i = 1; i < n; i++) {
				res[i] = res[i - 1] * nums[i - 1];
			}
			int right = 1;
			for (int i = n - 1; i >= 0; i--) {
				res[i] *= right;
				right *= nums[i];
			}
			return res;
		}
	}
}
/*	**TC**: O(n)
	**Method**: In one iteration, put the products of previous multplications before a 
	particular index. In the second iteration, multiply it with all the right multiplications 
	after a particular index.*/