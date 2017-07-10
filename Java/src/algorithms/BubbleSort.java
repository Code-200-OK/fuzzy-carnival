package algorithms;

public class BubbleSort {
	public static void bubbleSort(int[] a){
		/*
		Worst and Average Case Time Complexity: O(n*n). Worst case occurs when array is reverse sorted.

		Best Case Time Complexity: O(n). Best case occurs when array is already sorted.

		Auxiliary Space: O(1)

		Boundary Cases: Bubble sort takes minimum time (Order of n) when elements are already sorted.

		Sorting In Place: Yes

		Stable: Yes
		*/
		int swap;
		for(int i=0;i<a.length-1;i++){
			
			for(int j=1;j<a.length-i;j++){
				if(a[j]<a[j-1]){
					swap = a[j];
					a[j] = a[j-1];
					a[j-1] = swap;
				}	
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
		int[] a = {5,4,3,2,1};
		print(a);
		bubbleSort(a);
		print(a);
	}

}
/*Method: Total n-1 iterations. Each iteration starts from 1th pos and tries to swap with i-1 pos;
*/