package Test;

class Trie {
    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        for(int i = 0; i < word.length(); ++ i){
            int ch = word.charAt(i) - 'a';
            if(cur.children[ch] == null){
                cur.children[ch] = new TrieNode();
            }
            cur = cur.children[ch];
        }
        cur.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        for(int i = 0; i < word.length(); ++ i){
            int ch = word.charAt(i) - 'a';
            if(cur.children[ch] == null) return false;
            cur = cur.children[ch];
        }
        return cur.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for(int i = 0; i < prefix.length(); ++ i){
            int ch = prefix.charAt(i) - 'a';
            if(cur.children[ch] == null) return false;
            cur = cur.children[ch];
        }
        return true;
    }

    class TrieNode{
        TrieNode[] children;
        boolean isEnd;
        public TrieNode(){
            children = new TrieNode[26];
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("hello");
        boolean f1 = trie.search("hello");   // 返回 true
        boolean f2 = trie.search("hell");     // 返回 false
        boolean f5 = trie.search("helloa");
        boolean f3 = trie.startsWith("hello"); // 返回 true
        boolean f4 = trie.startsWith("helloa");     // 返回 true

    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */