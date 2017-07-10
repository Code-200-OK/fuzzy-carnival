package others;

import java.util.ArrayList;
import java.util.List;
/*Given an integer array, your task is to find all the different possible increasing subsequences of the 
given array, and the length of an increasing subsequence should be at least 2 . */
public class IncreasingSubsequences {
    List<Integer> temp;
    List<List<Integer>> ans;
    int current;
    public List<List<Integer>> findSubsequences(int[] nums) {
        temp = new ArrayList<Integer>();
        ans = new ArrayList<List<Integer>>();
        current = Integer.MIN_VALUE;
        find(0,nums);
        return ans;
    }
    private void find(int pos, int[] nums){
        if(temp.size()>=2)
            ans.add(new ArrayList<>(temp));
        int oldCurrent,t;
        boolean next;
        for(int i=pos;i<nums.length;i++){
        next = false;
        if(i==pos && (temp.size()==0 || nums[i]>=current)) next = true;
        else if(i!=pos && nums[i]>=current){
            t=i-1;
            while(t>=pos && nums[t]!=nums[i])t--;
            next = t<pos?true:false;
        }
        if(next){
            oldCurrent = current;
            current = nums[i];
            temp.add(current);
            find(i+1,nums);
            current = oldCurrent;
            temp.remove(temp.size()-1);
        }
        }
    }
}
/***TC**:
**Method**:
Start with assuming that the last encountered element is Integer.MIN_VALUE.
The recursive function expects a position as input, and for every value lying between position to the end of nums, is added if it is greater than the last encountered element else is ignored.
Every time an element is greater than/equal to the last encountered element, then this becomes the new last encountered element and the recursive function is called with next position as input.

For elimination of duplicates, we must check that the current element must not be present in the range [position,current].

At the start of every recursive function, if the size of temp is greater than 2 than add it to the answer's list.*/