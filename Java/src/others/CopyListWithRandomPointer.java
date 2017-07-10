package others;

/*** A linked list is given such that each node contains an additional random pointer which 
 * could point to any node in the list or null. Return a deep copy of the list. ***/
public class CopyListWithRandomPointer {
	public RandomListNode copyRandomList(RandomListNode head) {
		RandomListNode iter = head, next;

		// First round: make copy of each node,
		// and link them together side-by-side in a single list.
		while (iter != null) {
			next = iter.next;

			RandomListNode copy = new RandomListNode(iter.label);
			iter.next = copy;
			copy.next = next;

			iter = next;
		}

		// Second round: assign random pointers for the copy nodes.
		iter = head;
		while (iter != null) {
			if (iter.random != null) {
				iter.next.random = iter.random.next;
			}
			iter = iter.next.next;
		}

		// Third round: restore the original list, and extract the copy list.
		iter = head;
		RandomListNode pseudoHead = new RandomListNode(0);
		RandomListNode copy, copyIter = pseudoHead;

		while (iter != null) {
			next = iter.next.next;

			// extract the copy
			copy = iter.next;
			copyIter.next = copy;
			copyIter = copy;

			// restore the original list
			iter.next = next;

			iter = next;
		}

		return pseudoHead.next;
	}
}
/*```
**TC**: O(n)
**SC**: O(1)
**Method**: 
The algorithm is composed of the follow three steps which are also 3 iteration rounds.
*     Iterate the original list and duplicate each node. The duplicate of each node follows its original immediately.
*     Iterate the new list and assign the random pointer for each duplicated node.
*     Restore the original list and extract the duplicated nodes.

```
public RandomListNode copyRandomList(RandomListNode head) {
  if (head == null) return null;
  
  Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
  
  // loop 1. copy all the nodes
  RandomListNode node = head;
  while (node != null) {
    map.put(node, new RandomListNode(node.label));
    node = node.next;
  }
  
  // loop 2. assign next and random pointers
  node = head;
  while (node != null) {
    map.get(node).next = map.get(node.next);
    map.get(node).random = map.get(node.random);
    node = node.next;
  }
  
  return map.get(head);
}
```
**TC**: O(n)
**SC**: O(n)
**Method**:
Use HashTables. Do 1st iteration on the list and For each node create a entry. Key = actual 
node, value = new node of the same value. Do 2nd iteration on the list and find the random 
pointers node in the key of the table and assign it's value to the current node's value's 
random pointer.*/
