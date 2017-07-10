package chapter5_Bit_Manipulation;

public class NextNumber{
	
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
		System.out.println(a[0]);
		System.out.println(a[1]);
	}
}