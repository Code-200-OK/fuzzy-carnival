package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*Given a binary tree, return the level order traversal of its nodes'
		values. (ie, from left to right, level by level).*/

							// BFS using Single Queue
public class BinaryTreeLevelOrderTraversal {
	public class Solution {
	    public List<List<Integer>> levelOrder(TreeNode root) {
	        Queue<TreeNode> queue = new LinkedList<TreeNode>();
	        List<List<Integer>> wrapList = new ArrayList<List<Integer>>();

	        if(root == null) return wrapList;

	        queue.offer(root);
	        while(!queue.isEmpty()){
	            int levelNum = queue.size();
	            List<Integer> subList = new LinkedList<Integer>();
	            for(int i=0; i<levelNum; i++) {
	                if(queue.peek().left != null) queue.offer(queue.peek().left);
	                if(queue.peek().right != null) queue.offer(queue.peek().right);
	                subList.add(queue.poll().val);
	            }
	            wrapList.add(subList);
	        }
	        return wrapList;
	    }
	}
}
/***TC**: O(n)
**SC**: O(n) - Used queue (If no Queue can be used, then DFS for recursion).
**Method**: Usage of two queues and inserting left children followed by right children. 
Above code is modified version which uses one queue but of the same technique.
**Important**: 
* LinkedList(0,2) -> adds 2 to the head.
* LinkedList(2) -> adds 2 to the tail.
* 
* 
* 
* 

 								**DFS**:
```
public class Solution {
    List<List<Integer>> returnList = new ArrayList<List<Integer>>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        func(root,0);
        return returnList;
        
    }
    public void func(TreeNode root,int level)
    {
        if(root==null)
        {
            return;
        }
        //VISIT
        if(returnList.size() >= level+1)
        {
            List<Integer> l = returnList.get(level);
            l.add(root.val);
        }
        else
        {
            
            List<Integer> temp  = new ArrayList<Integer>();
            temp.add(root.val);
            returnList.add(level,temp);
        }
        
        //go left and right
        func(root.left,level+1);
        func(root.right,level+1);
    }
}
```
**TC**: O(n)
**SC**: no queue.
**Method**: Call a function with level 0. Add the root. call the left and right 
recursively with level increased. If the answer already contains the level candidates
then append element else append new list.*/


