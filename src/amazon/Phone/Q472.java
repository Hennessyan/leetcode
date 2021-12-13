package amazon.Phone;

import java.util.*;

/*
    Variant of Q472
    Identify all combinations where one words can be composed of two or more words of the same list, and print them.
    You are given a (potentially large) List of words. Some of these are compounds, where all parts are also in the List.

    Example List:

    [rockstar, rock, star, rocks, tar, star, rockstars, super, highway, high, way, superhighway]

    If returning, the output would be a list of lists, e.g.

    [[rock,‍‌‍‌‍‌‍‌‍‌‍‌‍‍‌‌‌ star], [rocks, tar], [super, highway], [super, high, way],...]
 */
public class Q472 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("rockrockrock", "rockstar", "rock", "star", "rocks", "tar", "star", "rockstars", "super", "highway", "high", "way", "superhighway");
        List<List<String>> res =  combs(list);
        for(List<String> tmp : res) {
            for(String s : tmp)
                System.out.print(s + " ");
            System.out.println();
        }
    }

    public static List<List<String>> combs(List<String> list) {
        List<List<String>> res = new ArrayList<>();
        if(list == null || list.size() < 2) return res;
        Set<String> set = new HashSet<>(list);

        for(String s : list) {
            set.remove(s);
            backtrack(set, s, 0, new LinkedList<>(), res);
            set.add(s);
        }
        return res;
    }
    private static void backtrack(Set<String> set, String s, int start, LinkedList<String> list, List<List<String>> res) {
        if(start == s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = start + 1; i <= s.length(); i++) {
            String tmp = s.substring(start, i);
            if(set.contains(tmp)) {
                list.add(tmp);
                backtrack(set, s, i, list, res);
                list.removeLast();
            }
        }
    }

}
