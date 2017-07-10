package trees;

/*** Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined
between two nodes v and w as the lowest node in T that has both v and w as descendants 
(where we allow a node to be a descendant of itself).”***/
public class BinaryTreeLCA {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if(root==null || p==null || q==null)
			return null;
		if(root==p || root==q)
			return root;
		TreeNode l = lowestCommonAncestor(root.left,p,q);
		TreeNode r = lowestCommonAncestor(root.right,p,q);
		if(l!=null && r!=null)
			return root;
		else if(l==null)
			return r;
		else
			return  l;
	}
}
/***TC**: O(n)
**Method**: if root is equal to either of them then root is the answer. Else if something is 
returned from both the sides of the root then also root is the answer. Else whichever side 
returns that is the answer.*/
