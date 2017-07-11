package chapter5_Bit_Manipulation;

public class Conversion {
	
	public static int flipbits(int m, int n){
		int a = m ^ n;
		int count = 0;
		while(a>0){
			if((a&1) == 1) count++;
			a >>>= 1; 		
		}
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(flipbits(4,40));

	}

}
