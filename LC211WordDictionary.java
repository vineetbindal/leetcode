package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC211WordDictionary {
	
	Map<Integer,Set<String>> map;
    /** Initialize your data structure here. */
    public LC211WordDictionary() {
        map = new HashMap<>();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if (map.containsKey(word.length())) {
         map.get(word.length()).add(word);
            //set.add(word);
        } else {
            Set<String> set = new HashSet<>();
            set.add(word);
            map.put(word.length(), set);
        }
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if (map.containsKey(word.length())) {
            Set<String> set = map.get(word.length());
            for (String str : set) {
                if (str.length() == word.length()) {
                    if (str.equals(word)) return true;
                    if (isSame(str, word)) return true;
                }
            }
        }
        return false;
    }
    
    private boolean isSame (String str, String word) {
         for (int i = 0 ; i < str.length(); i++) {
            if (word.charAt(i) != '.' && word.charAt(i) != str.charAt(i)) return false;
        }
        return true;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */


