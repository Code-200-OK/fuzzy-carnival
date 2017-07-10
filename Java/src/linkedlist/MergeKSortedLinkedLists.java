package linkedlist;

/*Merge k sorted linked lists and return it as one sorted list. 
 * Analyze and describe its complexity.
*/

public class MergeKSortedLinkedLists {
	public ListNode mergeKLists(ListNode[] lists) {
		if(lists==null || lists.length==0)
			return null;
		return sort(lists,0,lists.length-1);
	}
	public ListNode sort(ListNode[] l, int i, int j){
		if(i==j)
			return l[i];
		int le = (i+j)/2;
		ListNode one = sort(l,i,le);
		ListNode two = sort(l,le+1,j);
		return merge(one,two);
	}
	public ListNode merge(ListNode l1, ListNode l2) {
		if(l1==null||l2==null) return l1==null?l2:l1;
		if(l1.val < l2.val){
			l1.next = merge(l1.next,l2);
			return l1;
		}
		else{
			l2.next = merge(l1,l2.next);
			return l2;
		}
	}
}

/*TC: O(nlogk)
Method: Divide & Conquer. Merging K lists can be simulated similarly like mergesort by using 
the code of merging two sorted lists.

Improvment: Can use Priority Queue with a modified comparator to sort lists on the basis of 
their first element & polling every time adding the head to the list and offering back
the remaining list to the priority queue.*/


//TLE - BRUTE FORCE - O(nk)
/*public class Solution {
    private ListNode[] l;
    public ListNode mergeKLists(ListNode[] lists) {
        l = new ListNode[lists.length];
        for(int i=0;i<lists.length;i++)
            l[i] = lists[i];
        return find();
    }
    private ListNode find(){
        int min = Integer.MAX_VALUE;
        int pos = -1;
        for(int i=0;i<l.length;i++){
            if(l[i]!=null && l[i].val < min){
                min = l[i].val;
                pos = i;
            }
        }
        if(pos ==-1)
            return null;
        ListNode  head = l[pos];
        l[pos] = l[pos].next;
        head.next = find();
        return head;
    }
}*/