package trees;


import java.util.HashMap;
/*You are given a binary tree in which each node contains an integer value.
Find the number of paths that sum to a given value.
The path does not need to start or end at the root or a leaf, but it must go downwards
(traveling only from parent nodes to child nodes).***/
public class BinaryTreeDownwardPathSum {
	public int pathSum(TreeNode root, int sum) {
		HashMap<Integer, Integer> preSum = new HashMap();
		preSum.put(0,1);
		helper(root, 0, sum, preSum);
		return count;
	}
	int count = 0;
	public void helper(TreeNode root, int sum, int target, HashMap<Integer, Integer> preSum) {
		if (root == null) {
			return;
		}
		sum += root.val;

		if (preSum.containsKey(sum - target)) {
			count += preSum.get(sum - target);
		}

		if (!preSum.containsKey(sum)) {
			preSum.put(sum, 1);
		} else {
			preSum.put(sum, preSum.get(sum)+1);
		}

		helper(root.left, sum, target, preSum);
		helper(root.right, sum, target, preSum);
		preSum.put(sum, preSum.get(sum) - 1);
	}
}
/*TC: O(n)
Method: Prefix Sum. Store 0=1 in a map. For every node visited calculate the root to this 
node's sum. Now if (thissum-target) exists in the map then increment the count. Eg: if so
far we have made 24 as sum, and target is 10 and we have a 14 in the map that means there 
is path from root to some node that sums up to 24. We could consider the path from the next
node to this node.After handling the children, decrement the sum's count from the map.*/
