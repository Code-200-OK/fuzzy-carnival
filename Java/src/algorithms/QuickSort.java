package algorithms;

public class QuickSort {
	private static void quicksort(int[] a){
		sort(a,0,a.length-1);
	}
	private static void sort(int[] a, int st, int end){
		if(st>=end)
			return;
		
		int pivot = a[end];
		int left = st;
		int swap;
		for(int i=st;i<end;i++){
			if(a[i]<pivot){
				// swap left with i
				swap = a[left];
				a[left++] = a[i];
				a[i] = swap;
			}
		}
		swap=a[left];
        a[left] = pivot;
        a[end] = swap;
        sort(a,left+1,end);
        sort(a,st,left-1);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {4,7,2,0,5,1};
		print(a);
		quicksort(a);
		print(a);
	}
	public static void print(int[] a){
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
		System.out.println();
	}
}
