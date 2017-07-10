package trees;

/***Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes
in the BST.
According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined 
between two nodes v and w as the lowest node in T that has both v and w as descendants
(where we allow a node to be a descendant of itself).” It is guranteed than both are in 
the tree***/
public class BinarySearchTreeLCA {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	if (root == null || p == null || q == null) return null;
        if(root.val>Math.max(p.val, q.val)){
            return lowestCommonAncestor(root.left, p, q);
        }else if(root.val<Math.min(p.val, q.val)){
            return lowestCommonAncestor(root.right, p, q);
        }else{
            return root;
        }
    }
}
/***TC**: O(n)
**Method**: if my root is larger than both, then find in root's right. If root is smaller 
*than both then find in the left. else return root. If needed without recursion, 
*then use while(true) and keep on updating root while repeating the same steps like recursion.
**Important**:
1st Submission: The solution for LCA of Binary Tree. Time: 8ms.
2nd Submission: Let's use BST Property.
												UPDATE: made changes. Time: 9ms


BST doesn't have dupilcates assume.

3rd Submission: Submitted the non duplicate code of LCS of Binary Tree which took 23 ms.
*/