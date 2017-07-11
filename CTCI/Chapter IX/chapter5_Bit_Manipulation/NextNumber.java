package chapter5_Bit_Manipulation;

public class NextNumber{

	public static int getSmaller(int n){
		
		int c = n;
		int c0=0,c1=0;
		while(c!=0 && (c&1)==1){
			c1++;
			c >>= 1;		
		}
		while(c!=0 && (c&1)==0){
			c0++;
			c >>= 1;		
		}
		int p = c0+c1; // zero based
		if(c0==0) return -1;
		n &= (~0<<(p+1));
		n |= ((1<<(c1+1))-1)<<(c0-1);
		return n;
	}
	
	public static int[] find(int n){
		int[] ans = new int[2];
		if(~n==0) return ans;
		int mask = 1<<1;
		while(mask>0){
			if((mask&n)!=0){
				mask >>= 1;
				if((mask&n)==0){
					ans[0] = (n | mask);
					ans[0] &= (~(mask<<1));
					break;			
				}
				mask <<=1;
			}
			mask <<=1;		
		}
		mask = 1;
		while(mask>0){
			if((mask&n)!=0){
				mask <<= 1;
				if((mask&n)==0){
					
					ans[1] = (n | mask);
					ans[1] &= (~(mask>>1));
					break;			
				}
				mask >>=1;
			}
			mask <<=1;		
		}
		return ans;
	}
	public static void main(String[] args){
		int[] a= find(6);
		System.out.println(getSmaller(9));
	}
}
