package trie;

public class Trie {
	 private TrieNode root;
	    public Trie() {
	        root = new TrieNode();
	    }
	    // Inserts a word into the trie.
	    public void insert(String word) {
	        TrieNode node = root;
	        char[] wo = word.toCharArray();
	        for(int i=0;i<wo.length;i++){
	            if(node.children[wo[i]-'a']==null){
	                node.children[wo[i]-'a'] = new TrieNode();
	            }
	            node = node.children[wo[i]-'a'];
	        }
	        node.item = word;
	    }
	 // Returns if the word is in the trie.
	    public boolean search(String word) {
	        return find(word,0,root);
	    }
	    private boolean find(String str, int ai, TrieNode node){
	        if(ai>= str.length())
	            return (!node.item.equals(""));
	        if(node.children[str.charAt(ai)-'a']==null)
	            return false;
	        return find(str,ai+1,node.children[str.charAt(ai)-'a']);
	    }
	 // Returns if there is any word in the trie
	    // that starts with the given prefix.
	    public boolean startsWith(String prefix) {
	        return tryy(prefix,0,root);
	        
	    }
	    private boolean tryy(String str, int ai, TrieNode node){
	    	
	        if(ai>= str.length())
	           return true;
	        if(node.children[str.charAt(ai)-'a']==null)
	            return false;
	        
	        return tryy(str,ai+1,node.children[str.charAt(ai)-'a']);
	    }
	    
}
