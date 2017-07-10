package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*Inorder Traversal
**Approach 1** : **Iterative***/
public class BinaryTreeInorderTraversal {
	public List<Integer> inorderTraversal(TreeNode root) {
	    List<Integer> list = new ArrayList<Integer>();

	    Stack<TreeNode> stack = new Stack<TreeNode>();
	    TreeNode cur = root;

	    while(cur!=null || !stack.empty()){
	        while(cur!=null){
	            stack.add(cur);
	            cur = cur.left;
	        }
	        cur = stack.pop();
	        list.add(cur.val);
	        cur = cur.right;
	    }

	    return list;
}
}

/*
 * **TC**: O(n)
**Method**: Stack. Keep on pushing nodes on stack while trying to reach the left deepmost node.
* then display it then do it again for the right side of the node.

**Appraoch 2: Recursive**
```
public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<Integer>();
        rec(root,answer);
        return answer;
    }
    public void rec(TreeNode root, List<Integer>  ans){
        if(root!=null){
            rec(root.left,ans);
            ans.add(root.val);
            rec(root.right,ans);
        }
    }
```
 */
