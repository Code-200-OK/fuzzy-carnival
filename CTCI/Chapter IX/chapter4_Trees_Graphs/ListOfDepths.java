package chapter4_Trees_Graphs;

import java.util.ArrayList;
import java.util.List;

public class ListOfDepths {
	private static List<List<Integer>> data;
	public static List<List<Integer>> form(TreeNode root){
		data = new ArrayList<List<Integer>>();
		//find(root,1);
		List<Integer> currentvalues = new ArrayList<>();
		List<TreeNode> current = new ArrayList<>();

		current.add(root);
		currentvalues.add(root.value);

		while(current.size()>0){
			data.add(currentvalues);
			
			List<TreeNode> parent = current;
			current = new ArrayList<TreeNode>();
			currentvalues = new ArrayList<Integer>();
			
			for(TreeNode node: parent){
				if(node.next[0] != null){
					current.add(node.next[0]);
					currentvalues.add(node.next[0].value);
				}
				if(node.next[1] != null){
					current.add(node.next[1]);
					currentvalues.add(node.next[1].value);
				}
			}
		}
		return data;
	}
	public static void find(TreeNode node, int depth){
		if(node == null) return;
		if(data.size()<depth) data.add(new ArrayList<Integer>());
		data.get(depth-1).add(node.value);
		find(node.next[0],depth+1);
		find(node.next[1],depth+1);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t = new TreeNode(1,2);
		t.next[0] = new TreeNode(2,2);
		t.next[1] = new TreeNode(3,2);
		t.next[0].next[0] = new TreeNode(4,2);
		t.next[0].next[1] = new TreeNode(5,2);
		t.next[1].next[0] = new TreeNode(6,2);
		t.next[1].next[1] = new TreeNode(7,2);
		System.out.println(form(t));
	}

}
