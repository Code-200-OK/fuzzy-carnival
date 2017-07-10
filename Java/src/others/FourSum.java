package others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if(nums==null || nums.length<4)
            return ans;
        Arrays.sort(nums);
        int len = nums.length;
        int k,l;
        int look;
        for(int i=0;i<=len-4;i++){
            if(i>0 && nums[i]==nums[i-1]) continue;
            for(int j=i+1;j<=len-3;j++){
                if(j>i+1 && nums[j]==nums[j-1])continue;
                k=j+1;
                l=len-1;
                look = nums[i]+nums[j];
                while(k<l){
                    if(nums[k]+nums[l]==target-look){
                        ans.add(Arrays.asList(nums[i],nums[j],nums[k],nums[l]));
                        k++;
                        while(k<l && nums[k]==nums[k-1]) k++;
                        l--;
                        while(k<l && nums[l]==nums[l+1]) l--;
                    }
                    else if(nums[k]+nums[l]<target-look)k++;
                    else l--;
                }
            }
        }
        return ans;
    }
}

/*
 * Method: Using 2sum and 3sum.
 */
