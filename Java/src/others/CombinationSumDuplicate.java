package others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*Given a set of candidate numbers (C) (without duplicates) and 
a target number (T), find all unique combinations in C where the candidate numbers sums to T.
The same repeated number may be chosen from C unlimited number of times.*/ 

public class CombinationSumDuplicate {
	public class Solution {
		List<List<Integer>> ans;
		public List<List<Integer>> combinationSum(int[] candidates, int target){
			ans = new ArrayList<List<Integer>>();
			if(candidates==null || candidates.length==0)
				return ans;
			Arrays.sort(candidates);
			find(0,candidates,target,new ArrayList<Integer>());
			return ans;
		}
		private void find(int pos, int[] nums, int target,ArrayList<Integer> temp){
			if(target==0)
				ans.add(new ArrayList<>(temp));
			if(target<nums[pos])
				return;
			for(int i=pos;i<nums.length;i++){
				temp.add(nums[i]);
				find(i,nums,target-nums[i],temp);
				temp.remove(temp.size()-1);
			}

		}
	}
}
/***TC**:O(k * 2 ^ n) is the time complexity of Combination Sum II:
k is average length of each solution, and we need O(k) time to copy new linkedlist when we get one combination.

Solution space:
Since we use one element ONLY for one time, so, for the combinations with k elements, the number of different choices is C(n, k). And most of the time, this number is far smaller than k. But what is the worst case?
int[] arr = {1,1,1,1,1,1,1,1,1}, target is 2, in this case, the number can actually reach C(n,k).

Considering that the combinations may have different length, which range from 0 ~ n. So, there are at most
C(n,0) + C(n,1) + C(n,2) + ... C(n,n) solutions. We know that C(n,0) + C(n,1) + C(n,2) + ... C(n,n) = 2^n. Then we got the time complexity of Combination Sum II which is O(k * 2 ^ n).

**Method**: Similar to finding combinations. Sort the input array. Call the fun. If at the start target is 0 than append to answer else if the target is lesser then the top of the array which is the smallest possible of the array then say not possible else go recursive.
*/