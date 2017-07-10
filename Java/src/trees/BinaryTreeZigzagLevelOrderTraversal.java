package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeZigzagLevelOrderTraversal {
	public class Solution {
		public List<List<Integer>> zigzagLevelOrder(TreeNode root) 
		{
			List<List<Integer>> sol = new ArrayList<>();
			travel(root, sol, 0);
			return sol;
		}

		private void travel(TreeNode curr, List<List<Integer>> sol, int level)
		{
			if(curr == null) return;

			if(sol.size() <= level)
			{
				List<Integer> newLevel = new LinkedList<>();
				sol.add(newLevel);
			}

			List<Integer> collection  = sol.get(level);
			if(level % 2 == 0) collection.add(curr.val);
			else collection.add(0, curr.val);

			travel(curr.left, sol, level + 1);
			travel(curr.right, sol, level + 1);
		}
	}
}

/*
 * **TC**: O(n)
**SC: O(1)**
**Method**: Call fn with level 1. Every odd level guy gets added to the back of the list and every even level guy gets added to the front. Calling is left & right order as usual.

**APPROACH 2: BFS**
```
   public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
       List<List<Integer>> answer = new ArrayList<List<Integer>>();
        if(root == null)
            return answer;
        Stack<TreeNode> one = new Stack<TreeNode>();
        Stack<TreeNode> two = new Stack<TreeNode>();
        
        one.push(root);
        while(!(one.isEmpty() && two.isEmpty())){
            List<Integer> toAdd = new ArrayList<Integer>();
            if(!one.isEmpty()){
               while(!one.isEmpty()){
                   TreeNode t = one.pop();
                   toAdd.add(t.val);
                   if(t.left != null)
                        two.push(t.left);
                   if(t.right!=null)
                        two.push(t.right);
               } 
            }
            else{
                while(!two.isEmpty()){
                   TreeNode t = two.pop();
                   toAdd.add(t.val);
                   if(t.right != null)
                        one.push(t.right);
                   if(t.left!=null)
                        one.push(t.left);
               }
            }
        answer.add(toAdd);
        }
       return answer; 
    }
```
**TC**: O(n)
**SC**: O(n)
**Method**: Use two stacks. For first to second, its left then right. For second to first, 
*it's righ then left. Don't consider null values.
 */
