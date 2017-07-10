package others;

/*Given a string containing just the characters '(' and ')', 
find the length of the longest valid (well-formed) parentheses substring.*/

public class LongestValidParentheses{
	public int longestValidParentheses(String s) {
		if(s== null || s.length()==0)
			return 0;
		int len = s.length();
		char[] ch = s.toCharArray();
		int[] dp = new int[len];
		int ans=0;
		for(int i=1;i<len;i++){
			if(ch[i]=='(')
					dp[i] = 0;
			else{
				if(ch[i-1]=='('){
					dp[i] = 2;
					if(i>=2)
						dp[i]+= dp[i-2];
				}
				else{
					if(i-1-dp[i-1]>=0 && ch[i-1-dp[i-1]]=='('){
						dp[i] = 2 + dp[i-1];
						if(i-1-dp[i-1]>=1)
							dp[i] += dp[i-1-dp[i-1]-1];
					}
					else
						dp[i]=0;
				}
			}
			ans = Math.max(ans,dp[i]);
		}
		return ans;
	}
}

/*TC: O(n)
Method: DP; dp[i] = longest valid parenthesis ending at i;
If current is ( then 0 as no valid seq can end with (; 
If current is ) then either ValidSeq() possible or ValidSeq1(ValidSeq2).*/