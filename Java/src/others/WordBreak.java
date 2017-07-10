package others;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
determine if s can be segmented into a space-separated sequence of one or more dictionary words.
You may assume the dictionary does not contain duplicate words. You can use any word in the 
dictionary any number of times.*/
public class WordBreak {
	public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        Set<String> words = new HashSet<String>();
        for(int i=0;i<wordDict.size();i++)
            words.add(wordDict.get(i));
        for(int i=1;i<=n;i++){
            for(int j=0;j<i;j++){
                if(dp[j] && words.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
/*TC: O(n^2)
Method: DP. set DP[0]=true; DP[i] = if the word till (i-1) pos be breaken into valid 
peices or it exists as a whole in the dictionary. DP[i] = iterate through all possible 
cuts with [0,i] and if any them satisfies the condition that its left's DP is true and it's 
right exists in the dictionary then set the DP[i] as true. At last return DP[str.length()].*/
