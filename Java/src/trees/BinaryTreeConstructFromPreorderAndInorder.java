package trees;

/***Given preorder and inorder traversal of a tree, construct the binary tree.***/
public class BinaryTreeConstructFromPreorderAndInorder {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if(preorder.length!=inorder.length) return null;
		return build(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
	}

	public TreeNode build(int [] preorder, int preLow, int preHigh, int[] inorder, int inLow, int inHigh){
		if(preLow>preHigh || inLow>inHigh) return null;
		TreeNode root = new TreeNode(preorder[preLow]);

		int inorderRoot = inLow;
		for(int i=inLow;i<=inHigh;i++){
			if(inorder[i]==root.val){ inorderRoot=i; break; }
		}

		int leftTreeLen = inorderRoot-inLow;
		root.left = build(preorder, preLow+1, preLow+leftTreeLen, inorder, inLow, inorderRoot-1);
		root.right = build(preorder, preLow+leftTreeLen+1, preHigh, inorder, inorderRoot+1, inHigh);       
		return root;        
	}
}
/***TC**: O(n)
Method: Call fn with (prelow,prehigh,inlow,inhigh). Prelow is the new root, find the prelow's 
element in the inorder arary and recuvrsivley call. Return null when boundary conditions 
violated. After finding the root from the inorder, calculate the length of the left subtree
 from inorder and the next that many elements from the current pre position would be the part.
 
*/

