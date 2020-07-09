package google;

import java.util.HashSet;
import java.util.Set;

// Shortest Way to Form String
public class Q1055 {

    public static void main(String[] args) {
        Q1055 q = new Q1055();
        System.out.println(q.shortestWay("abc", "abcbc"));  //2
        System.out.println(q.shortestWay("abc", "abcde"));  //-1
    }

    // O(sLen*tLen) O(sLen)
    // add tmp = j inside while loop, to avoid using set => ST : O(1)
    // (but I think it will affect by garbage collection, not O(1))
    public int shortestWay(String source, String target) {
        Set<Character> set = new HashSet<>();
        for(char c : source.toCharArray()) {
            set.add(c);
        }
        for(char c : target.toCharArray()) {
            if(!set.contains(c)) {
                return -1;
            }
        }
        int count = 0;
        int i = 0, j = 0, sLen = source.length(), tLen = target.length();
        while(j < tLen) {
            count++;
            i = 0;
            while(i < sLen && j < tLen) {
                if(source.charAt(i) == target.charAt(j)) {
                    j++;
                }
                i++;
            }
        }
        return count;
    }
    // // O(sLen*tLen) O(tLen)
    public int shortestWay1(String source, String target) {
        return shortest(source.toCharArray(), target.toCharArray(), 0);
    }
    private int shortest(char[] s, char[] t, int start) {
        if(start == t.length) {
            return 0;
        }
        int i = 0, j = start;
        while(i < s.length && j < t.length) {
            if(s[i++] == t[j]) {
                j++;
            }
        }
        if(j == start) return -1;
        int res = shortest(s, t, j);
        return res == -1 ? -1 : res + 1;
    }
}
