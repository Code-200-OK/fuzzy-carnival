package others;

public class MoveZeroes {
	public static void move(int[] a){
		int insertpos = a.length-1;
		int pos = insertpos;
		while(pos>=0){
			if(!(a[pos]==0))
				a[insertpos--] = a[pos];
			pos--;
		}
		while(insertpos>=0)a[insertpos--]=0;
	}
public static void main(String[] args){
	int[] a = new int[]{0,0,1,2,0,3,4,0,5,0,0};
	move(a);
	for(int i=0;i<a.length;i++)
		System.out.print(a[i]);
}
}
 
