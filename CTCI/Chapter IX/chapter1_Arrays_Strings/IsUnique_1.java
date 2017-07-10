package chapter1_Arrays_Strings;
public class IsUnique_1 {

	public static void main(String[] args) {
		System.out.println(isUnique("sakthi"));
	
	}
	public static boolean isUnique(String s){
		int bitVector = 0;
		char c;
		int pos;
		int mask;
		for(int i=0;i<s.length();i++){
			c = s.charAt(i);
			pos = (c - 'a') + 25;
			mask = 1 << pos;
			if((bitVector & mask) == 0){
				bitVector |= mask;			
			}
			else return false;	
		}
		return true;
	}

}
