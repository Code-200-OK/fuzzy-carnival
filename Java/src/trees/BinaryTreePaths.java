package trees;

import java.util.ArrayList;
import java.util.List;


/*Given a binary tree, return all root-to-leaf paths.
*/
public class BinaryTreePaths {
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> answer = new ArrayList<String>();
		StringBuilder ans = new StringBuilder();
		findPaths(root,ans,answer);
		return answer;
	}
	public void findPaths(TreeNode root, StringBuilder ans, List<String> answer){
		if(root != null){
			int l = ans.length();
			ans.append(root.val);
			if(root.left == null && root.right == null)
			{
				answer.add(ans.toString());
			}
			else
			{
				ans.append("->");
				findPaths(root.left,ans,answer);
				findPaths(root.right,ans,answer);
			}
			ans.setLength(l);  
		}
	}
}

/*TC: O(n) since we are basically visiting each node in the tree
Method: Backtracking. Use stringBuilder to append nodes through the path. When leaf node met, add/display. The backtracking step is to reset the length of the stringbuilder after every call.
Important:

"StringBuilder" is a mutable object
"String" is a immutable object.
You cannot use "replace" in stringbuilder
StringBuilder s = new StringBuilder();
s.length();
s.setLength(6);*/