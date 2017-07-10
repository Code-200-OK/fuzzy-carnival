package chapter4_Trees_Graphs;

public class TreeNode {
	
	public int value;
	public TreeNode[] next;
	public TreeNode parent;
	public TreeNode(int val,int num){
		value = val;
		next = new TreeNode[num];		
	}
	public void traverse(){
		System.out.print(value+",");
		for(int i=0;i<next.length;i++){
			if(next[i]!=null)
				next[i].traverse();
		}
	}
	public String toString(){
		return Integer.toString(value);
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
		t.traverse();
	}

}
