package others;

/*Given two strings s and t, write a function to determine if t is an anagram of s.
 */

//                         APPROACH 1
public class ValidAnagram {
	public boolean isAnagram(String s, String t) {

		int[] charsMap = new int['z'-'a'+1];

		for(char c: s.toCharArray()) {
			int pos = c - 'a';
			charsMap[pos]++;
		}

		for(char c: t.toCharArray()) {
			int pos = c - 'a';
			charsMap[pos]--;
		}

		for(int count: charsMap) {
			if(count != 0) {
				return false;
			}
		}

		return true;
	}
}

/*TC: O(n)
Method: Initialize a 26size array to store the occurrences of the possible 26 characters of 
the first string. Then iterate through the second string and match that the occurence of the 
characters match.

 *Method 2: Can also prime numbers technique to do things.
 */

//                          APPROACH 2 (uSING pRIMES)

/*private static final int[] PRIMES = new int[]{3, 5, 7, 11 ,13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
        73, 79, 83, 89, 97, 101, 107};
public boolean isAnagram(String s, String t) {
    return hash(s) == hash(t);
}

private long hash(String s) {
    long hash = 1;
    for (int i = 0; i < s.length(); i++) {
        hash *= PRIMES[s.charAt(i) - 'a'];
    }
    return hash;
}*/
