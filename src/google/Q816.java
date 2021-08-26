package google;

import java.util.ArrayList;
import java.util.List;

// Ambiguous Coordinates
public class Q816 {
    // O(n^3) O(n^3)
    public List<String> ambiguousCoordinates(String s) {
        List<String> res = new ArrayList<>();
        for(int i = 2; i <= s.length() - 1; i++) {
            for(String l : make(s, 1, i)) {
                for(String r : make(s, i, s.length() - 1)) {
                    res.add("(" + l + ", " + r + ")");
                }
            }
        }
        return res;
    }
    private List<String> make(String s, int i, int j) {
        List<String> list = new ArrayList<>();
        for(int d = 1; d <= j - i; d++) {
            String l = s.substring(i, i + d);
            String r = s.substring(i + d, j);
            if((!l.startsWith("0") || l.equals("0")) && !r.endsWith("0")) {
                list.add(l + (d < j - i ? "." : "") + r);
            }
        }
        return list;
    }

    private String s;
    public List<String> ambiguousCoordinates1(String s) {
        this.s = s;
        int n = s.length();
        List<String> res = new ArrayList<>();
        for (int i = 1; i < n - 2; i++) {
            List<String> left = parseNums(1, i);
            if (left.isEmpty()) {
                continue;
            }
            List<String> right = parseNums(i + 1, n - 2);
            if (right.isEmpty()) {
                continue;
            }
            for (String l : left) {
                for (String r : right) {
                    res.add("(" + l + ", " + r + ")");
                }
            }
        }
        return res;
    }

    private List<String> parseNums(int i, int j) {
        List<String> res = new ArrayList<>();
        if (s.charAt(i) != '0' || i == j) {
            res.add(s.substring(i, j + 1));
        }
        if (s.charAt(j) == '0' || i == j) {
            return res;
        }
        int end = s.charAt(i) == '0' ? i : j - 1;
        for (int k = i; k <= end; k++) {
            res.add(s.substring(i, k + 1) + "." + s.substring(k + 1, j + 1));
        }
        return res;
    }
}
