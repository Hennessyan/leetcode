package challenge.september;

import java.util.HashMap;
import java.util.Map;

// Word Pattern
public class Q290 {

    public static void main(String[] args) {
        Q290 q = new Q290();
        System.out.println(q.wordPattern("abba", "dog cat cat dog"));   // true
    }

    // O(n) O(m) n - num of words in str; m - unique words in str
    public boolean wordPattern(String pattern, String str) {
        String[] arr = str.trim().split(" ");
        int len = arr.length;
        if (pattern.length() != len) {
            return false;
        }
        Map<Character, String> m1 = new HashMap<>();
        Map<String, Character> m2 = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = pattern.charAt(i);
            if (!m1.containsKey(c)) {
                if (m2.containsKey(arr[i])) {
                    return false;
                } else {
                    m1.put(c, arr[i]);
                    m2.put(arr[i], c);
                }
            } else {
                if (!m1.get(c).equals(arr[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean wordPattern2(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != (pattern.toCharArray()).length) return false;
        HashMap<Character, String> map = new HashMap<>();
        int i = 0;
        for (char c : pattern.toCharArray()) {
            if (map.containsKey(c)) {
                if (!(map.get(c)).equals(words[i])) return false;
            } else if (map.containsValue(words[i])) return false;   // O(n)
            else map.put(c, words[i]);
            i++;
        }
        return true;
    }

    //O(n) O(n)
    // This is because if you use primitive int as a key/value for generic map,
    // java will convert it into Integer then save the converted Integer into map,
    // and "==" or "!=" only works for Integer whos value is below 127.
    public boolean wordPattern1(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map<Object, Integer> index = new HashMap<>();    //注意类型
        for (Integer i = 0; i < words.length; ++i)        //不能用int,会报错
            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
                return false;
        return true;
    }
}
