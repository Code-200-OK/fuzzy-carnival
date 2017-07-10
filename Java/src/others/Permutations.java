package others;

import java.util.ArrayList;
import java.util.List;

/***Given a collection of distinct numbers, return all possible permutations.***/
public class Permutations {
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		// Arrays.sort(nums); // not necessary
		backtrack(list, new ArrayList<>(), nums);
		return list;
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
		if(tempList.size() == nums.length){
			list.add(new ArrayList<>(tempList));
		} else{
			for(int i = 0; i < nums.length; i++){ 
				if(tempList.contains(nums[i])) continue; // element already exists, skip
				tempList.add(nums[i]);
				backtrack(list, tempList, nums);
				tempList.remove(tempList.size() - 1);
			}
		}
	}
}

/***TC**:O(n!)
**Method**:Backtrackking. Could have used a USED array. but used here a arraylist.contains() 
which saves space.*/