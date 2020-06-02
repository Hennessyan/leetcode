package challenge.may;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// Find All Anagrams in a String
// similar as Q567()
public class Q438 {

    public static void main(String[] args) {
        Q438 q = new Q438();
        List<Integer> res = q.findAnagrams("cbaebabacd", "abc");
        System.out.println(Arrays.asList(res));
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new LinkedList<>();
        if(p.length() > s.length()) {
            return list;
        }
        int[] sc = new int[26];
        int[] pc = new int[26];
        for(int i = 0; i < p.length(); i++) {
            sc[s.charAt(i) - 'a']++;
            pc[p.charAt(i) - 'a']++;
        }
        int count = 0;
        for(int i = 0; i < 26; i++) {
            if(sc[i] == pc[i]) {
                count++;
            }
        }
        for(int i = 0; i < s.length() - p.length(); i++) {
            int r = s.charAt(i + p.length()) - 'a';
            int l = s.charAt(i) - 'a';
            if(count == 26) {
                list.add(i);
            }
            sc[r]++;
            if(sc[r] == pc[r]) {
                count++;
            }else if(sc[r] == pc[r] + 1) {
                count--;
            }
            sc[l]--;
            if(sc[l] == pc[l]) {
                count++;
            }else if(sc[l] + 1 == pc[l]) {
                count--;
            }
        }
        if(count == 26) {
            list.add(s.length() - p.length());  //注意这里不是S.LENGTH() - P.LENGTH() - 1.
        }
        return list;
    }
}
