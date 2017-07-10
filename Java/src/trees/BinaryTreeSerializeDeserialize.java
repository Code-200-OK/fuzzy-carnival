package trees;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/***Serialization is the process of converting a data structure or object into a sequence of bits
so that it can be stored in a file or memory buffer, or transmitted across a network connection 
link to be reconstructed later in the same or another computer environment.
Design an algorithm to serialize and deserialize a binary tree. There is no restriction on 
how your serialization/deserialization algorithm should work. You just need to ensure that a 
 tree can be serialized to a string and this string can be deserialized to the original tree 
 structure.***/
public class BinaryTreeSerializeDeserialize {
	public String serialize(TreeNode root) {
		StringBuilder treeS = new StringBuilder();
		convertTreetoString(root,treeS);
		return treeS.toString();
	}
	public void convertTreetoString(TreeNode root, StringBuilder s){

		if(root!= null){
			s.append(root.val);
			s.append(",");
			convertTreetoString(root.left,s);
			convertTreetoString(root.right,s);
		}
		else{
			s.append("X");
			s.append(",");
		}
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		Queue<String> nodes  = new LinkedList<>();
		nodes.addAll(Arrays.asList(data.split(",")));
		return convertStringtoTree(nodes);
	}

	public TreeNode  convertStringtoTree(Queue<String> nodes){
		String top = nodes.poll();
		if(top.equals("X"))
			return null;
		TreeNode head = new TreeNode(Integer.parseInt(top));
		head.left= convertStringtoTree(nodes);
		head.right = convertStringtoTree(nodes);
		return head;
	}
}
/*TC: Serialize: O(n) Deserialize: O(n)
Method: Store CSV of Nodes where null are represented as X in the form of string. For decoding,
split it on "," in a queue and call the fn with queue as the parameter. if the top is null then 
return null else it becomes the head and recursively call for the children and return head.
Important:
String a = "done,fell,short";
Queue v = new LinkedList();
v.addAll(Arrays.asList(a.split(",")));*/
