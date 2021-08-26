package amazon;

import javafx.util.Pair;

// Compare Version Numbers
public class Q165 {
    // O(n+m+max(n,m)) O(n+m)
    public int compareVersion1(String version1, String version2) {
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");
        int n1 = nums1.length, n2 = nums2.length;

        // compare versions
        int i1, i2;
        for (int i = 0; i < Math.max(n1, n2); ++i) {
            i1 = i < n1 ? Integer.parseInt(nums1[i]) : 0;
            i2 = i < n2 ? Integer.parseInt(nums2[i]) : 0;
            if (i1 != i2) {
                return i1 > i2 ? 1 : -1;
            }
        }
        // the versions are equal
        return 0;
    }
    // O(max(m,n)) O(max(m,n))
    public int compareVersion(String version1, String version2) {
        int n1 = version1.length(), n2 = version2.length();
        int p1 = 0, p2 = 0;
        while(p1 < n1 || p2 < n2) {
            Pair<Integer, Integer> pair1 = helper(version1, p1, n1);
            Pair<Integer, Integer> pair2 = helper(version2, p2, n2);
            int a = pair1.getKey();
            p1 = pair1.getValue();
            int b = pair2.getKey();
            p2 = pair2.getValue();
            if(a != b) {
                return a > b ? 1 : -1;
            }
        }
        return 0;
    }
    private Pair<Integer, Integer> helper(String version, int p, int n) {
        if(p >= n) {                    // must use >= because we return  t + 1 in L51.
            return new Pair(0, n);
        }
        int t = p;
        while(t < n && version.charAt(t) != '.') {
            t++;
        }
        int val = Integer.parseInt(version.substring(p, t));
        return new Pair(val, t + 1);
    }
}
