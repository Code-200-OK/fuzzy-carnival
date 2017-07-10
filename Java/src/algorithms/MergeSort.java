package algorithms;

public class MergeSort {
	static void mergeSort(int[] a){
		/*
		T(n) = 2T(n/2) + Theta(n)
		O(nlogn) in all 3 cases (worst, average and best) as merge sort always 
		divides the array in two halves and take linear time to merge two halves.
		
		Auxiliary Space: O(n)

		Algorithmic Paradigm: Divide and Conquer

		Sorting In Place: No in a typical implementation

		Stable: Yes
		*/
		
		System.out.println("started..");
		sort(a,0,a.length-1);
	}
	private static void merge(int[] a, int start, int mid, int end){
		int left[] = new int[mid-start+1];
		int right[] = new int[end-mid];
		for(int i=0;i<left.length;i++)
		{
			left[i] = a[start+i];
		}
		for(int i=0;i<right.length;i++)
		{
			right[i] = a[mid+1+i];
		}

		int l=0, r=0,k=start;
		while(l<left.length && r<right.length){
			if(left[l]<right[r]){
				a[k] = left[l];
				l++;
				k++;
			}
			else{
				a[k] = right[r];
				r++;
				k++;
			}
		}
		while(l<left.length){
			a[k] = left[l];
			l++;
			k++;
		}

		while(r<right.length){
			a[k] = right[r];
			r++;
			k++;
		}

	}
	private static void sort(int[]a, int start, int end){
		if(start>= end || start<0 || end>=a.length)
			return;
		int mid = (end+start)/2;

		// divide
		sort(a,start,mid);
		sort(a,mid+1,end);
		merge(a,start,mid,end);
	}
	public static void print(int[] a){
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
		System.out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {4,7,2,0,5,1};
		print(a);
		mergeSort(a);
		print(a);
	}

}
