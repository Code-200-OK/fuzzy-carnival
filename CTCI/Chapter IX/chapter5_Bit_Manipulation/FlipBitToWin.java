package chapter5_Bit_Manipulation;

public class FlipBitToWin {
	public static int flip(int n){
		if(n==0) return 1;
		int mask = 1;
		int l =0,r=-1;
		int i=1;
		int count = 0;
		while(n>0){
			if((mask&n) == 0){
				count = Integer.max(count,l+r+1);
				r = l;
				l = 0;		
			}
			else{
				l++;			
			}
			n &= (~mask);
			mask <<=1;
			i++;		
		}
		count = Integer.max(count,l+(r==-1?0:r+1));
		return count;	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Integer.BYTES);
		System.out.println(flip(10));

	}

}
