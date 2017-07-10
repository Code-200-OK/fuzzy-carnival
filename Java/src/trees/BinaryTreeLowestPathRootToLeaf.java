package trees;

public class BinaryTreeLowestPathRootToLeaf {
	//private static int sum;
	private static int path(TreeNode root){
		return find(root);
	}
	private static int find(TreeNode root){
		if(root == null)
			return Integer.MAX_VALUE;
		if(root.left==null && root.right==null)
			return root.val;
		return root.val+Math.min(find(root.left),find(root.right));
	}
	public static void main(String[] args){
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
	root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(5);
		BinaryTreeInorderTraversal a = new BinaryTreeInorderTraversal();
		System.out.println(a.inorderTraversal(root));
		System.out.println(path(root));
	}
}
