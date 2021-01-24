package facebook;

import java.util.ArrayList;
import java.util.List;

// Check If Two String Arrays are Equivalent
public class Q1662 {
    // O(n) O(n)
    public boolean arrayStringsAreEqual1(String[] word1, String[] word2) {
        String str1 = String.join("", word1);
        String str2 = String.join("", word2);
        return str1.equals(str2);   // convert to char array, then compare element one by one.
    }
    // O(n) O(n)
    public boolean arrayStringsAreEqual2(String[] word1, String[] word2) {
        List<Character> list1 = new ArrayList<>();
        List<Character> list2 = new ArrayList<>();
        for(String word : word1) {
            for(char c : word.toCharArray()) {
                list1.add(c);
            }
        }
        for(String word : word2) {
            for(char c : word.toCharArray()) {
                list2.add(c);
            }
        }
        if(list1.size() != list2.size()) {
            return false;
        }
        for(int i = 0; i < list1.size(); i++) {
            if(list1.get(i) != list2.get(i)) {
                return false;
            }
        }
        return true;
    }
    // O(n) O(n)
    public boolean arrayStringsAreEqual3(String[] word1, String[] word2) {
        int len2 = word2.length;
        int wordIndex2 = 0;
        int arrIndex2 = 0;
        for(String word : word1) {
            for(char c : word.toCharArray()) {
                if(arrIndex2 == len2 || c != word2[arrIndex2].charAt(wordIndex2)) {
                    return false;
                } else {
                    if (++wordIndex2 == word2[arrIndex2].length()) {
                        arrIndex2++;
                        wordIndex2 = 0;
                    }

                }
            }
        }
        return arrIndex2 == len2;
    }

    public boolean arrayStringsAreEqual4(String[] word1, String[] word2) {
        List<Character> list2 = new ArrayList<>();
        for(String word : word2) {
            for(char c : word.toCharArray()) {
                list2.add(c);
            }
        }
        int i = 0, len = list2.size();
        for(String word : word1) {
            for(char c : word.toCharArray()) {
                if(i == len || c != list2.get(i)) {
                    return false;
                }
                i++;
            }
        }
        return i == len;
    }

    public boolean arrayStringsAreEqual51(String[] word1, String[] word2) {
        StringBuilder sb = new StringBuilder();
        for(String word : word2) {
            sb.append(word);
        }
        String s2 = sb.toString();
        for(String s : word1) {
            int index = s2.indexOf(s);  // time complexity of indexOf is (m*n)
            if(index == 0) {
                s2 = s2.substring(s.length());
            } else {
                return false;
            }
        }
        return s2.length() == 0;
    }
    // O(n) O(n)
    public boolean arrayStringsAreEqual52(String[] word1, String[] word2) {
        String string2 = String.join("", word2);
        int string2Length = string2.length();
        int index = 0;
        for (String s : word1) {
            for (char c : s.toCharArray()) {
                if (index >= string2Length || c != string2.charAt(index)) {
                    return false;
                }
                index++;
            }
        }
        return index == string2Length;
    }

    // O(n) O(1)
    public boolean arrayStringsAreEqual6(String[] word1, String[] word2) {
        int i1 = 0, i2 = 0, a1 = 0, a2 = 0;
        while(a1 < word1.length && a2 < word2.length) {
            if(word1[a1].charAt(i1++) != word2[a2].charAt(i2++)) {
                return false;
            }
            if(i1 == word1[a1].length()) {
                a1++;
                i1 = 0;
            }
            if(i2 == word2[a2].length()) {
                a2++;
                i2 = 0;
            }
        }
        return a1 == word1.length && a2 == word2.length;
    }
}
