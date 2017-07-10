package others;

/*Given a string s, find the longest palindromic substring in s. 
You may assume that the maximum length of s is 1000.*/

/*Expanding around Center differs by below DP method only in SC but peforms lot better.*/
public class LongestPalindromeSubString {
	public String longestPalindrome(String s) {
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);
			int len2 = expandAroundCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}

	private int expandAroundCenter(String s, int left, int right) {
		int L = left, R = right;
		while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
			L--;
			R++;
		}
		return R - L - 1;
	}
}
/*TC: O(n^2). Since expanding a palindrome around its center could take O(n) time, 
the overall complexity is O(n^2).
SC: O(1)
Method: Expanding around the centers. total n + n-1 = 2n-1 centers. choose a center, 
try expanding from it meaninwhile compare with the max stored.*/


			// DYNAMIC PROGRAMMING

/*public class Solution {
	public String longestPalindrome(String s) {
		if(s==null || s.length()==0)
			return s;
		char[] str = s.toCharArray();
		int l = str.length;
		boolean[][] dp = new boolean[l][l];
		int r=0,c=0;
		for(int i=l-1;i>=0;i--)
			for(int j=i;j<=l-1;j++){
				if(i==j)
					dp[i][j] = true;
				else if(i+1 == j)
					dp[i][j] = (str[i]==str[j]);
				else
					dp[i][j] = (str[i]==str[j] && dp[i+1][j-1]);
				if(dp[i][j] && j-i+1 > c-r+1)
				{
					r=i;
					c=j;
				} 
			}
		return s.substring(r,c+1);
	}
}
TC: O(n^2)
SC: O(n^2)
Method: DP[i][j] = true if palindrome else not.
P(i,j)=(P(i+1,j-1) and S[i]==S[j]
Base Case: P(i,i) = true; P(i,i+1) = s[i]==s[j]
*/

