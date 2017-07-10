package chapter1_Arrays_Strings;
import java.util.HashMap;
import java.util.Map;

public class PalindromePerumtation_4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String does not implement iterable
		if(isPermutationOfPalindrome2("az"))	System.out.println("Yes");
		else System.out.println("No");
	}
	
	public static boolean isPermutationOfPalindrome(String s){
		// o(n)
	
		Map<Character,Integer> counts = new HashMap<>();
		int numOfOdds = 0;
		for(int i=0;i<s.length();i++){
			counts.put(s.charAt(i),(counts.getOrDefault(s.charAt(i),0)+1));
		}
		for(Map.Entry<Character,Integer> e: counts.entrySet()){
			if(e.getValue()%2==1){
				numOfOdds++;
				if(numOfOdds > 1){
					return false;				
				}			
			}
		}
		return true;
	
	}
	public static boolean isPermutationOfPalindrome2(String s){
		
		// assumption: String contains 26 lower case letters
		int bitvector = 0;
		int pos;
		int mask;
		for(int i=0;i<s.length();i++){
			pos = s.charAt(i) - 'a';
			mask = 1 << pos;
			if((mask & bitvector) == 0){
				bitvector |= mask;			
			}
			else{
				bitvector &= ~mask;			
			}
		}
		System.out.println(Integer.toBinaryString(bitvector));
		// either no odd, or only one odd.
		return (bitvector ==0) || (((bitvector-1) & bitvector) == 0);
	
	}
	

}
