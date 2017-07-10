package others;

import java.util.Arrays;
import java.util.Comparator;
/***Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that 
all houses could be covered by those heaters.***/
public class Heaters {
	public int findRadius(int[] houses, int[] heaters) {
		if(houses.length==0)
			return 0;
		if(heaters.length==0)
			return Integer.MAX_VALUE;
		Arrays.sort(heaters);
		Arrays.sort(houses);
		int ans=0;
		int hpos=0;
		int firstdif=0;
		for(int i=0;i<houses.length;i++){
			firstdif = Integer.MAX_VALUE;
			while(hpos<heaters.length && Math.abs(houses[i]-heaters[hpos])<=firstdif){
				firstdif = Math.abs(houses[i]-heaters[hpos]);
				hpos++;
			}
			hpos--;
			ans = Math.max(ans,firstdif);
		}
		String[] strs = null;
		Comparator<String> c = new Comparator<String>(){
            public int compare(String a, String b){
                return a.length()-b.length();
            }
        };
        Arrays.sort(strs,c);
        
		return ans;
	}
}
/***
 * **TC**: Max(O(mlogn),O(nlogm)) 
 * Method**: Sort both houses & heaters. For each of the house, find the nearest heater and that would be be distance
that house would prefer. Maximum of all such distances would be the answer.*/
