package trees;

public class BinaryTreeFlip {
	private static TreeNode flip(TreeNode root){
		if(root==null)
			return null;
		if(root.left == null)
			return root;
		TreeNode ro = flip(root.left);
		ro.left = root.right;
		ro.right = root;
		root.left = null;
		root.right = null;
		return ro;
	}
	public static void main(String[] args){
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		/*root.left.left = new TreeNode(4);
	root.left.right = new TreeNode(5);*/
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(5);
		BinaryTreeInorderTraversal a = new BinaryTreeInorderTraversal();
		System.out.println(a.inorderTraversal(root));
		System.out.println(a.inorderTraversal(flip(root)));
	}
}

/*TC: O(n)
Method: Flip the left side then do the arranging. For any  node whose left is null return the same node.	
*/