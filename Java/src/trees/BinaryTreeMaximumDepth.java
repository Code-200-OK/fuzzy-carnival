package trees;

/***Given a binary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to 
the farthest leaf node.***/
public class BinaryTreeMaximumDepth {
	public int maxDepth(TreeNode root) {
		return root==null?0:(1 + Math.max(maxDepth(root.left),maxDepth(root.right)));
	}
}
/***TC**: O(n)
**Method**: for nulls its 0, else 1+ max of both the children.*/