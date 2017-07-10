package others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*Given a collection of integers that might contain duplicates, nums, 
 * return all possible subsets.
If nums = [1,2,2], a solution is:
[
[2],
[1],
[1,2,2],
[2,2],
[1,2],
[]
]*/
public class SubsetsWithDuplicate {
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, 0);
		return list;
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
		list.add(new ArrayList<>(tempList));
		for(int i = start; i < nums.length; i++){
			if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
			tempList.add(nums[i]);
			backtrack(list, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}
}

/*TC: O(2^n)
Method: Backtrack
Follow the same technique as the subsets problem without duplicates. 
From every second entry check if it is not same as the previous one. 
In short, for every call, call recursively in a unique manner. 
for 1,1,2, ignore the second 1 in the first fn call.*/

