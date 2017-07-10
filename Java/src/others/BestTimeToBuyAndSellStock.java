package others;

/*Say you have an array for which the ith element is the price of a given stock on day i.
If you were only permitted to complete at most one transaction (ie, buy one and sell one
share of the stock), design an algorithm to find the maximum profit.*/
public class BestTimeToBuyAndSellStock {
	public int maxProfit(int[] prices) {
		int dif = 0;
		if(prices == null)
			return dif;
		int l= prices.length;
		if(l<=1)
			return dif;
		int min = prices[0];
		int temp;
		for(int i=1;i<l;i++){
			temp = prices[i] - min;
			dif = Math.max(temp,dif);
			min = (prices[i] < min) ? prices[i] : min;
		}
		return dif;

	}   
}
/***TC**: O(n)
**Method**: Consider the first value as the min and the maxProfit=0. Now iterate through 
*each value and if it is less than min then update min else if difference of this and the 
*min so far is greater than maxProfit then update it.
*
**Important**:
Usage of a variable instead of a dp array performs better.
***TERNARY OPERATOR***
```
int opening_time;
if (day == SUNDAY)
    opening_time = 12;
else
    opening_time = 9;
```

Becomes 

```
int opening_time = (day == SUNDAY) ? 12 : 9;
```*/
