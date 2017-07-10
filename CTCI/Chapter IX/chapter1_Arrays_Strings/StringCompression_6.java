package chapter1_Arrays_Strings;
public class StringCompression_6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(stringCompression("ababababababab"));
	}
	public static String stringCompression(String s){
		if(s==null || s.length() == 0) return s;
		int[] counts = new int[52];
		// A - 0, Z-25,a-26,z- 55;
		int pos;
		char c;
		int totalLength = 0;
		int prev,next;
		/// following is a wrong of counting ahead. counting should have been
		// done like how we compress. So more or less duplicate code.
		for(int i=0;i<s.length();i++){
			c = s.charAt(i);
			if(Character.isUpperCase(c)){
				pos = c-'A';		
			}
			else pos = (c - 'a') + 26;
			prev = Integer.toString(counts[pos]).length();
			if(counts[pos] == 0) totalLength += 2;
			counts[pos]++;
			next = Integer.toString(counts[pos]).length();
			if(next>prev) totalLength++;
		}
		System.out.println(totalLength);
		if(totalLength>=s.length()) return s;	
		StringBuilder comprS = new StringBuilder();
		int count = 1;
		c = s.charAt(0);
		for(int i=1;i<s.length();i++){
			if(s.charAt(i)==c){
			count++;	
			}
			else{
				comprS.append(c);
				comprS.append(count);
				count = 1;
				c = s.charAt(i);		
			}	
		}
		comprS.append(c);
		comprS.append(count);
		return comprS.toString();
	}

}
