package others;

/*** A magical string S consists of only '1' and '2' and obeys the following rules:
The string S is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the string S itself.
The first few elements of string S is the following: S = "1221121221221121122……"***/
public class MagicalString {
	public int magicalString(int n) {
		int[] s = new int[n+2];
		if(n==0)return 0;
		if(n>=1)s[1]=1;
		if(n>=2)s[2]=2;
		if(n>=3)s[3]=2;
		boolean cur=true;
		int pos = 3;
		int ans=1;
		int inserted = 3;
		while(inserted<n){
			for(int j=1;j<=s[pos];j++){
				if(cur){
					s[++inserted]=1;
					ans++;
				}
				else
					s[++inserted]=2;
			}
			cur = !cur;
			pos++;
		}
		if(n>=4&&inserted>n && s[s.length-1]==1)
			ans--;
		return ans;
	}
}/*
Method: Start with putting 122. Set the pointer at 3rd position. If current is '2' that means need to add '1' pointer 
number of times. If current '1' that means need to add  '2' pointer number of times.*/