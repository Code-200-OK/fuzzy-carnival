package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/***Binary Tree Right Side View**

**Approach 1: BFS***/
public class BinaryTreeRightSideView {
	public List<Integer> rightSideView(TreeNode root) {
		// reverse level traversal
		List<Integer> result = new ArrayList();
		Queue<TreeNode> queue = new LinkedList();
		if (root == null) return result;

		queue.offer(root);
		while (queue.size() != 0) {
			int size = queue.size();
			for (int i=0; i<size; i++) {
				TreeNode cur = queue.poll();
				if (i == 0) result.add(cur.val);
				if (cur.right != null) queue.offer(cur.right);
				if (cur.left != null) queue.offer(cur.left);
			}

		}
		return result;
	}
}
/*
 * **TC**: O(n)
**SC**: O(n);
**Method**:
Used one queue similar to Level Order Traversal. Every first element should be added to
 the answer list

**Approach 2: DFS**
```
public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }
    
    public void rightView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.val);
        }
        
        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
        
    }
```
**TC**: O(n)
**SC**: O(1)
**Method**: Use recursion for DFS. Call fn with 0 as the depth parameter. 
*before adding check if the size of the answer so far is equal to the depth. 
*If yes, then add else call right and left recrusively.

 */
