package amazon;

import java.util.*;

// Brace Expansion
public class Q1087 {
    // method1
    public String[] expand(String S) {
        List<String> list = new ArrayList<>();
        backtrack(S, "", 0, list);
        String[] res = list.toArray(new String[list.size()]);
        Arrays.sort(res);
        return res;
    }
    private void backtrack(String s, String prefix, int index, List<String> list) {
        if(index == s.length()) {
            list.add(prefix);
            return;
        }
        if(s.charAt(index) == '{') {
            int end = s.indexOf("}", index);
            for(int i = index + 1; i < end; i++) {
                if(s.charAt(i) == ',') {
                    continue;
                }
                backtrack(s, prefix + s.charAt(i), end + 1, list);
            }
        }else {
            prefix += s.charAt(index);
            backtrack(s, prefix, index + 1, list);
        }
    }
    // method2
    public String[] expand1(String s) {
        int n = s.length(), i = 0;
        Deque<List<String>> stack = new ArrayDeque<>();
        stack.push(new ArrayList<>());
        stack.peek().add("");
        char[] chs = s.toCharArray();

        while(i < n) {
            char c = chs[i];
            if(Character.isLetter(c)) {
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                i++;
                while(i < n && Character.isLetter(chs[i])) {
                    sb.append(chs[i++]);
                }
                String cur = sb.toString();
                List<String> tmp = new ArrayList<>();
                List<String> list = stack.pop();
                for(String prefix : list) {
                    tmp.add(prefix + cur);
                }
                stack.push(tmp);
            } else if(c == '{') {
                i++;
                List<String> tmp = new ArrayList<>();
                while(chs[i] != '{' && chs[i] != '}') {
                    if(chs[i] == ',') {
                        i++;
                        continue;
                    }
                    tmp.add(chs[i++] + "");
                }
                Collections.sort(tmp);
                stack.push(tmp);
            } else {
                i++;
                List<String> p1 = stack.pop();
                List<String> p2 = stack.pop();
                List<String> tmp = new ArrayList<>();
                for(String t2 : p2) {
                    for(String t1 : p1) {
                        tmp.add(t2 + t1);
                    }
                }
                stack.push(tmp);
            }
        }

        List<String> list = stack.peek();
        return list.toArray(new String[list.size()]);
    }
}
