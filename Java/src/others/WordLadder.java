package others;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*** Given two words (beginWord and endWord), and a dictionary's word list, find the 
length of shortest transformation sequence from beginWord to endWord, such that:
* Only one letter can be changed at a time.
* Each transformed word must exist in the word list. Note that beginWord is not a 
transformed word.***/

public class WordLadder {

	public int ladderLength(String beginWord, String endWord, List<String> wordDict) {
		Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();
		Set<String> wordList = new HashSet<String>();
		for(int i=0;i<wordDict.size();i++)
			wordList.add(wordDict.get(i));
		if(!wordList.contains(endWord))
			return 0;
		int len = 1;
		int strLen = beginWord.length();
		Set<String> visited = new HashSet<String>();

		beginSet.add(beginWord);
		endSet.add(endWord);
		while (!beginSet.isEmpty() && !endSet.isEmpty()) {
			if (beginSet.size() > endSet.size()) {
				Set<String> set = beginSet;
				beginSet = endSet;
				endSet = set;
			}

			Set<String> temp = new HashSet<String>();
			for (String word : beginSet) {
				char[] chs = word.toCharArray();

				for (int i = 0; i < chs.length; i++) {
					for (char c = 'a'; c <= 'z'; c++) {
						char old = chs[i];
						chs[i] = c;
						String target = String.valueOf(chs);

						if (endSet.contains(target)) {
							return len + 1;
						}

						if (!visited.contains(target) && wordList.contains(target)) {
							temp.add(target);
							visited.add(target);
						}
						chs[i] = old;
					}
				}
			}

			beginSet = temp;
			len++;
		}

		return 0;
	}
}
/***TC**: 26 * O(n). only the word in the dict will do a 26-loop, and each word only do it once
**Method**: Two way BFS. Mantain four sets namely begin,end,visited and words. start with 
putting beginword in begin and endword in end.** always make sure smaller of the begin and 
end is the begin as we want the tree to be more broader rather than deeper and avoid useless
computations**. Try all possible combos of all the words in begin which have not been visited
and which exist in the wordlist. if any of the combo is the endword then return the current
		distance else add it to visited set and continue.*/