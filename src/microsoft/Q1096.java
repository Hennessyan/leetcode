package microsoft;

import java.util.*;

// Brace Expansion II
// 1087
public class Q1096 {

    int i;
    public List<String> braceExpansionII(String expression) {
        char[] chs = expression.toCharArray();
        Set<String> set = help(chs);

        i = 0;
        List<String> list = new ArrayList<>(set);
        Collections.sort(list);
        return list;
    }

    private Set<String> help(char[] chs) {
        Set<String> set = new HashSet<>();
        set.add("");
        while(i < chs.length) {
            if(Character.isLetter(chs[i])) {
                set = cross(set, "" + chs[i++]);
            } else if(chs[i] == ',') {
                i++;
                set.addAll(help(chs));
                break;      // return in this case !
            } else if(chs[i] == '{') {
                i++;
                Set<String> tmp = help(chs);
                set = cross(set, tmp);
            } else {
                i++;
                break;
            }
        }
        return set;
    }
    private Set<String> cross(Set<String> set, String s) {
        Set<String> res = new HashSet<>();
        for(String pre : set) {
            res.add(pre + s);
        }
        return res;
    }
    private Set<String> cross(Set<String> s1, Set<String> s2) {
        Set<String> res = new HashSet<>();
        for(String t1 : s1) {
            for(String t2 : s2) {
                res.add(t1 + t2);
            }
        }
        return res;
    }

}
