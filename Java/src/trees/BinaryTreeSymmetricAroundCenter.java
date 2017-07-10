package trees;

/***Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).**
*/
public class BinaryTreeSymmetricAroundCenter {
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        else
            return issym(root.left,root.right);
    }
    public boolean issym(TreeNode one, TreeNode two){
        if(one==null || two== null){
            if(one==null && two==null)
                return true;
            else
                return false;
        }
        else
            return (one.val==two.val) && issym(one.left,two.right) && issym(one.right,two.left);
    }
}
/***TC**: O(n)
**Method**: issym(root.left,root.right). Both can be null else if only one of them is
null then false. Else root values have to be same and left's left should be sym with 
right's right and left' right must sym with right's left.*/