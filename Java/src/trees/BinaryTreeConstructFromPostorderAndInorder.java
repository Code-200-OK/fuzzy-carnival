package trees;

/***Given inorder and postorder traversal of a tree, construct the binary tree.***/
public class BinaryTreeConstructFromPostorderAndInorder {
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		if(inorder.length!=postorder.length) return null;
		return build(postorder, 0, postorder.length-1, inorder, 0, postorder.length-1);
	}

	public TreeNode build(int [] postorder, int postLow, int postHigh, int[] inorder, int inLow, int inHigh){
		if(postLow>postHigh || inLow>inHigh) return null;
		TreeNode root = new TreeNode(postorder[postHigh]);

		int inorderRoot = inLow;
		for(int i=inLow;i<=inHigh;i++){
			if(inorder[i]==root.val){ inorderRoot=i; break; }
		}

		int leftTreeLen = inHigh-inorderRoot;
		root.right = build(postorder, postHigh-leftTreeLen, postHigh-1, inorder, inorderRoot+1, inHigh);
		root.left = build(postorder, postLow, postHigh-leftTreeLen-1, inorder, inLow, inorderRoot-1);       
		return root;        
	}
}
/***TC**: O(n)
**Method**: Same as constructing binary tree from inorder and preorder method.*/
