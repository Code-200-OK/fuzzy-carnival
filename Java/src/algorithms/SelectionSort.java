package algorithms;

public class SelectionSort {
	public static void selectionSort(int[] a){
		// find minimum bring to the top each iteration
		/*
		Time Complexity: O(n2) as there are two nested loops.

		Auxiliary Space: O(1)

		The good thing about selection sort is it never makes more than O(n) swaps 
		and can be useful when memory write is a costly operation. 
		*/
		int minpos,swap;
		for(int i=0;i<a.length-1;i++){
			
			minpos = i;
			for(int j=i+1;j<a.length;j++){
				minpos = (a[j]<a[minpos])? j : minpos;
			}
			if(minpos != i){
				swap = a[i];
				a[i] = a[minpos];
				a[minpos] = swap;
			}
		}
	}
	public static void print(int[] a){
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
		System.out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {4,7,2,0,99,23};
		print(a);
		selectionSort(a);
		print(a);
	}

}
