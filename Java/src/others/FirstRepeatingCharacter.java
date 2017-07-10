package others;

import java.util.HashSet;
import java.util.Set;

public class FirstRepeatingCharacter {
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
		/*Set<Character> visited = new HashSet<Character>();
		char cur;
		char[] sa = s.toCharArray();
		for(int i=0;i<sa.length;i++){
			cur = sa[i];
			if(visited.contains(cur)) return cur;
			visited.add(cur);
		}
		
		return '-';*/
		boolean[] charHits = new boolean['z' - 'a' + 1];

	    for (int i=0; i < s.length(); i++){
	        // our current character
	        char current = s.charAt(i);
	        if (charHits[current - 'a']){
	            // character index already set in charHits array - already seen!
	            // print and return
	      
	            return current;
	        } else {
	            // first hit - mark as already occurred for future iterations
	            charHits[current - 'a'] = true;
	        }
	    }
	    return '.';
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(first("abcddaf"));
	}

}
