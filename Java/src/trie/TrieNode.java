package trie;

public class TrieNode {
	public TrieNode[] children;
    public String item;
    public TrieNode() {
        children = new TrieNode[26];
        item = "";
    }
}
