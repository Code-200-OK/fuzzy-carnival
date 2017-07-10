package trees;

public class BinaryTreeInvert {
	public TreeNode invertTree(TreeNode root) {
		if(root == null) return null;
		TreeNode t = root.left;
		root.left = invertTree(root.right);
		root.right = invertTree(t);
		return root;
	}
}
/*```
**TC**: O(n)
**Method**: Store the left child. Invert the right one and put as left then invert the 
*stored left one and store as right child. If null return null;

**APPROACH 2:**
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        
        if (root == null) {
            return null;
        }

        final Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        
        while(!stack.isEmpty()) {
            final TreeNode node = stack.pop();
            final TreeNode left = node.left;
            node.left = node.right;
            node.right = left;
            
            if(node.left != null) {
                stack.push(node.left);
            }
            if(node.right != null) {
                stack.push(node.right);
            }
        }
        return root;
    }
}
**TC**: O(n)
**Method**: Swap the children and put them back on stack if they are not null for next iteration.*/
