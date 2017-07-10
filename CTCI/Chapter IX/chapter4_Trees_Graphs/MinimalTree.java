package chapter4_Trees_Graphs;

public class MinimalTree {
	public static TreeNode form(int[] elements,int start, int end){
		if(start>end) return null;
		int mid = (start+end)/2;
		TreeNode t = new TreeNode(elements[mid],2);
		t.next[0] = form(elements,start,mid-1);
		t.next[1] = form(elements,mid+1,end);
		return t;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] e = {1,2,3,4,5,6};
		TreeNode root = form(e,0,e.length-1);
		root.traverse();

	}

}
