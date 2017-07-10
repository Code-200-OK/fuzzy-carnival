package others;

import java.util.HashSet;
import java.util.Set;

public class FirstNonRepeatingCharacter {
	public static char first(String s){
/*		Map<Character,Integer> bd = new LinkedHashMap<Character,Integer>();
		char[] sa = s.toCharArray();
		for(int i=0;i<sa.length;i++){
			if(!bd.containsKey(sa[i]))
				bd.put(sa[i], 0);
			bd.put(sa[i], bd.get(sa[i])+1);
		}
		for(Map.Entry<Character, Integer> e: bd.entrySet()){
			if(e.getValue()==1)
				return e.getKey();
		}
		return '-';*/
		Set<Character> visited = new HashSet<Character>();
		char cur;
		char[] sa = s.toCharArray();
		for(int i=0;i<sa.length;i++){
			cur = sa[i];
			if(!visited.contains(cur)){
				for(int j=i+1;j<sa.length;j++){
					if(sa[j]==cur){
						visited.add(cur);
						break;
					}
				}
			}
			if(!visited.contains(cur))
				return cur;
		}
		
		return '-';
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(first("GG"));
	}

}
