

import java.util.HashMap;
import java.util.Map;

// Use Sliding Window & HashMap

// Alternative:
	// can use two hashtables and can compare both at increment of every currentPos
	// http://www.geeksforgeeks.org/anagram-substring-search-search-permutations/
public class perumationsOfAStringInAnother {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		find("abbc","zzzzcbabadcbbabbcbabaabccbabc");

	}
	public static void find(String a, String b){
		Map<Character,Integer> aCount = new HashMap<>();
		int wordLength = a.length();
		char[] bCharArray = b.toCharArray();
		int currentBPos = 0;
		int winStart = -1;
		int winEnd = -1;
		
		
		for(int i=0;i<wordLength;i++){
			aCount.put(a.charAt(i),aCount.getOrDefault(a.charAt(i),0)+1);
		}
		
		while(currentBPos < bCharArray.length){
			if(aCount.containsKey(bCharArray[currentBPos])){
				if(aCount.get(bCharArray[currentBPos])>0){
					if(winStart>=0){
						winEnd++;
					}
					else{
						winStart = currentBPos;
						winEnd = winStart;
					}
					aCount.put(bCharArray[winEnd],aCount.get(bCharArray[winEnd])-1);
				}
				else{
					while(bCharArray[winStart]!=bCharArray[currentBPos]){
						aCount.put(bCharArray[winStart],aCount.get(bCharArray[winStart])+1);
						winStart++;
					}
					winStart++;
					winEnd++;
				}
				if(winEnd-winStart+1 ==wordLength){
					System.out.println(b.substring(winStart,winEnd+1));
					aCount.put(bCharArray[winStart],aCount.get(bCharArray[winStart])+1);
					winStart++;
				}
			}
			else if(winStart>=0){
					while(winStart<=winEnd){
						aCount.put(bCharArray[winStart],aCount.get(bCharArray[winStart])+1);
						winStart++;
					}
					winStart = -1;
					winEnd = -1;
				}
			currentBPos++;
		}
	}

}
