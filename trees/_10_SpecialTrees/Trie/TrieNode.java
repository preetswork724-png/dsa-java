package trees._10_SpecialTrees.Trie;

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd = false;
    int countPrefix = 0;
    int endsWith = 0;
}
