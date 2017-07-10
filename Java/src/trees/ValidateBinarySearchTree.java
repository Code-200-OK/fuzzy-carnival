package trees;

/***Given a binary tree, determine if it is a valid binary search tree (BST). **
*/
public class ValidateBinarySearchTree {
	public boolean isValidBST(TreeNode root) {
		return isvalid(root,Long.MIN_VALUE,Long.MAX_VALUE);
	}
	public boolean isvalid(TreeNode root, long min, long max){
		if(root == null)
			return true;
		return (root.val<max && root.val>min) && isvalid(root.left,min,root.val) && isvalid(root.right,root.val,max);

	}
}
/***TC**: O(n) n: is the total number of nodes.
**Method**: every node's value must lie in the range (min,max) where 
both of them have been initialized with Long.MIN and Long.Max.
**Important**:
Integer.MAX_VALUE = (-2147483648 , 2147483647)
Long.MAX_VALUE = (-9223372036854775808 , 9223372036854775807)
Float.MAX_VALUE = (1.4E-45, 3.4028235E38)
Double.MAX_VALUE = (4.9E-324 , 1.7976931348623157E308)*/
