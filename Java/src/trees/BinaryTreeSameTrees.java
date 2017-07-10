package trees;

/*Given two binary trees, write a function to check if they are equal or not.
Two binary trees are considered equal if they are structurally identical 
and the nodes have the same value.*/
public class BinaryTreeSameTrees {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null || q==null){
            if(p==null && q==null)
                return true;
            else
                return false;
        }
        else{
            return (p.val==q.val) && isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        }
    }
}
/*TC: O(n)
Method: Both have to be null or both have to be not null. else return not equal. 
compare the values of the root and do recursively for left with left and right with right.*/