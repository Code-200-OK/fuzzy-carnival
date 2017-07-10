package others;

import java.util.HashMap;
import java.util.Map;

/*Given an array of integers, return indices of the two numbers 
 * such that they add up to a specific target.*/
public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
		int[] result = new int[2];
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				result[1] = i;
				result[0] = map.get(target - nums[i]);
				return result;
			}
			map.put(nums[i], i);
		}
		return result;
	}
}

/*TC: O(n)
Method: Use Hashmap to store value. Before adding check if (sum - this_number) exists in the map.*/