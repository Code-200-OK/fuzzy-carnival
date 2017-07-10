package trees;

/***Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
***/
public class BinarySearchTreeKthSmallestElement {
	public int kthSmallest(TreeNode root, int k) {
		int left  = count(root.left);
		if(k<=left)
			return kthSmallest(root.left,k);
		if(k==left+1)
			return root.val;
		return kthSmallest(root.right,k-1-left);
	}
	private int count(TreeNode root){
		if(root == null)return 0;
		return 1+ count(root.left)+count(root.right);
	}
}
/***TC**: O(nlogn)*/
