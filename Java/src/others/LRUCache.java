package others;

import java.util.HashMap;
import java.util.Map;

/*** Design and implement a data structure for Least Recently Used (LRU) cache. It should 
support the following operations: get and put. 
get(key) - Get the value (will always be positive
		) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When 
the cache reached its capacity, it should invalidate the least recently used item 
before inserting a new item.
Could you do both operations in O(1) time complexity?***/
public class LRUCache {
		class DLinkedNode {
			int key;
			int value;
			DLinkedNode pre;
			DLinkedNode post;
		}

		/**
		 * Always add the new node right after head;
		 */
		private void addNode(DLinkedNode node){
			node.pre = head;
			node.post = head.post;
			
			head.post.pre = node;
			head.post = node;
		}

		/**
		 * Remove an existing node from the linked list.
		 */
		private void removeNode(DLinkedNode node){
			DLinkedNode pre = node.pre;
			DLinkedNode post = node.post;
			
			pre.post = post;
			post.pre = pre;
		}

		/**
		 * Move certain node in between to the head.
		 */
		private void moveToHead(DLinkedNode node){
			this.removeNode(node);
			this.addNode(node);
		}

		// pop the current tail. 
		private DLinkedNode popTail(){
			DLinkedNode res = tail.pre;
			this.removeNode(res);
			return res;
		}

		private Map<Integer, DLinkedNode> 
			cache = new HashMap<Integer, DLinkedNode>();
		private int count;
		private int capacity;
		private DLinkedNode head, tail;

		public LRUCache(int capacity) {
			this.count = 0;
			this.capacity = capacity;

			head = new DLinkedNode();
			head.pre = null;
			
			tail = new DLinkedNode();
			tail.post = null;
			
			head.post = tail;
			tail.pre = head;
		}

		public int get(int key) {
		    
			DLinkedNode node = cache.get(key);
			if(node == null){
				return -1; // should raise exception here.
			}
			
			// move the accessed node to the head;
			this.moveToHead(node);
			
			return node.value;
		}


		public void put(int key, int value) {
			DLinkedNode node = cache.get(key);
			
			if(node == null){
				
				DLinkedNode newNode = new DLinkedNode();
				newNode.key = key;
				newNode.value = value;
				
				this.cache.put(key, newNode);
				this.addNode(newNode);
				
				++count;
				
				if(count > capacity){
					// pop the tail
					DLinkedNode tail = this.popTail();
					this.cache.remove(tail.key);
					--count;
				}
			}else{
				// update the value.
				node.value = value;
				this.moveToHead(node);
			}
			
		}
		    }

/*
 * ```
**Method**: Mantain HashMap<Integer,DoubleListNode>. A doubly linked dummy head and dummy
* tail and capacities to keep a check on size. Use map for O(1) checking for presence, use
*  Doubly linked guy for moving to the front/back. 

Approach 2: 
```
public class LRUCache {
    Map<Integer,Integer> lru;
    int size;
    public LRUCache(int capacity) {
        size = capacity;
        lru = new LinkedHashMap<Integer,Integer>();
    }
    
    public int get(int key) {
        if(!lru.containsKey(key))
            return -1;
        int val = lru.get(key);
        lru.remove(key);
        lru.put(key,val);
        return val;
    }
    
    public void put(int key, int value) {
        if(lru.containsKey(key))
            lru.remove(key);
        else if(lru.size()==size){
            Iterator<Map.Entry<Integer,Integer>> it = lru.entrySet().iterator();
            it.next();
            it.remove();
        }
        lru.put(key,value);
        }
    }
```
**Method**: Use a LinkedHashMap for O(1) presence checking, and remove and adding 
*something will add it to the last. For poping out the top, use entryset iterator and 
*remove the very first entry. 
 */

