package trees;

/*Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as a binary tree in which 
the depth of the two subtrees of every node never differ by more than 1.*/

public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        int r = checkIfBal(root);
        if(r==-1)
            return false;
        else
            return true;
    }
    public int checkIfBal(TreeNode root){
        if(root == null)
            return 0;
        int l= checkIfBal(root.left);
        if(l==-1)
            return -1;
        int r = checkIfBal(root.right);
        if(r==-1)
            return -1;
        if(Math.abs(l-r) > 1)
            return -1;
        else
            return 1 + Math.max(l,r);
    }
}
/*TC: O(n)
Method:
Method takes input node, returns the height if it is balanced else returns -1. if 
null returns 0. Hence a leaf node is balanced tree. both left and right must be balanced and their height must not differ by greater than 1.
Important:

The depth of a node is the number of edges from the node to the tree's root node.
A root node will have a depth of 0
The height of a node is the number of edges on the longest path from the node to a leaf.
A leaf node will have a height of 0.
The height of a tree would be the height of its root node,
or equivalently, the depth of its deepest node*/
