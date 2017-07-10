package others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:

[
 [1,1,2],
 [1,2,1],
 [2,1,1]
]*/

public class PermutationsWithDuplicate {
	public class Solution {
		public List<List<Integer>> permuteUnique(int[] nums) {
			List<List<Integer>> ans = new ArrayList<List<Integer>>();
			if(nums==null || nums.length==0)
				return ans;
			int[] used = new int[nums.length];
			Arrays.sort(nums);
			find(ans,nums,used,new ArrayList<Integer>());
			return ans;	
		}
		private void find(List<List<Integer>> ans, int[] nums, int[] used, ArrayList<Integer> temp){
			if(temp.size()==nums.length)
			{
				ans.add(new ArrayList<>(temp));
				return;
			}
			for(int i=0;i<nums.length;i++){
				if(used[i]==0){
					if(i>0 && nums[i]==nums[i-1] && used[i-1]!=0) continue;
					used[i]=1;
					temp.add(nums[i]);
					find(ans,nums,used,temp);
					temp.remove(temp.size()-1);
					used[i]=0;
				}
			}
		}
	}
}
/***TC**: O(n!)
**Method**: Same as normal without duplicates. For removal of duplicate answer,
make sure when a duplicate item is being processed, it's identical precedor's used[]
		must be 1. else skip it.*/