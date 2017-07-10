package trees;

/*** Given a binary tree, find the maximum path sum.**

**For this problem, a path is defined as any sequence of nodes from some starting 
node to any node in the tree along the parent-child connections. The path must
contain at least one node and does not need to go through the root.***/
public class BinaryTreeMaximumPathSum {
	int sum = Integer.MIN_VALUE;
	public int maxPathSum(TreeNode root) {
		findMaxPath(root);
		return this.sum;
	}
	public int findMaxPath(TreeNode root){
		if(root == null)
			return 0;
		int l = findMaxPath(root.left);
		int r = findMaxPath(root.right);
		int to;
		if(l<0 && r<0){
			sum = Math.max(sum,root.val);
			to = root.val;
		}
		else{
			to = root.val + Math.max(l,r);
			if(l<0 || r<0)
				sum = Math.max(sum,to);
			else
				sum = Math.max(sum,to + Math.min(l,r));
		}
		return to;
	}
}
/***TC**: O(n)
**Method**
for every node, two max paths down both sides possible, and sum of them can form
a max sum. They can also be negative thats why you can have the option of not
considering them.*/
