package chapter5_Bit_Manipulation;

//https://github.com/careercup/CtCI-6th-Edition/tree/master/Java
public class PairwiseSwap {
	public static int swap(int n){
		int mask = 3;
		int t1=n;
		int t2=n;
		int count = 0;
		while(n>0 && mask>=3){
			count +=2;
			if((mask&n)!=mask){
				t1 &= (~mask);			
			}
			if((mask&n)!=0){
				t2 |= (mask);			
			}
			mask <<=2;
		}
		System.out.println(Integer.toBinaryString(t1));
		System.out.println(Integer.toBinaryString(t2));
		return (t1 | (n^((1<<(count+1))-1))) & t2;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 557;
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString(swap(a)));

	}

}
