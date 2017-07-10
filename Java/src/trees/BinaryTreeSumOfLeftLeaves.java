package trees;

/*Find the sum of all left leaves in a given binary tree. (Root is not a left leaf)*/
public class BinaryTreeSumOfLeftLeaves {
	public int sumOfLeftLeaves(TreeNode root) {
		return leftSum(root,1);
	}
	public int leftSum(TreeNode root,int c){
		if(root == null)
			return 0;
		if(root.left == null && root.right==null){
			if(c==0)
				return root.val;
			else
				return 0;
		}
		return leftSum(root.left,0)+leftSum(root.right,1);
	}
}

/*TC: O(n) DFS
Method:
Function called with 1 as parameter. call recursively the left child with 0, and 
right child with 1. Return the sum of both. Any leaf with parameter 1 means it is a 
right leaf so need not be counted.*/
