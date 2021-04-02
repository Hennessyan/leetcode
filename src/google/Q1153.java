package google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// String Transforms Into Another String
public class Q1153 {
    // 不能有多个OUTPUT EDGE
    // str2不能26个字符都用掉了,就没有用来中转过渡的字符了.
    // str1可以26个字符都用掉,因为可以多对1
    public boolean canConvert(String str1, String str2) {
        if(str1.equals(str2)) {
            return true;
        }
        Set<Character> set2 = new HashSet<>();
        Map<Character, Character> map = new HashMap<>();
        for(int i = 0; i < str1.length(); i++) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            if(map.containsKey(c1) && map.get(c1) != c2) {
                return false;
            }
            set2.add(c2);
            map.put(c1, c2);
        }
        if(set2.size() == 26) { //开始的时候判断了是否两个STR相等,否则这里得加上!str1.equals(str2)
            return false;
        }
        return true;
    }
}
