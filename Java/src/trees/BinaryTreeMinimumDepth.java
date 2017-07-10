package trees;

/***Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to 
the nearest leaf node.***/
public class BinaryTreeMinimumDepth {
	public int minDepth(TreeNode root) {
		if(root == null)
			return 0;
		else{
			int lef = minDepth(root.left);
			int rig = minDepth(root.right);

			if(lef ==0 || rig ==0)
				return 1+ lef + rig;
			else
				return 1 + Math.min(lef,rig);
			
		}
	}
}
/***TC**: O(n)
**Method**: null returns 0. Else if any of the children returns zero then return the 
		maxofboth+1 else minofboth+1.*/
