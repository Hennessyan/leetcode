package google;

import java.util.*;

// Erect the Fence
public class Q587 {
    // Jarvis Algorithm : O(m*n) O(m) -> n - length of p, m - size of candidates (m <= n)
    // for each candidates, we compare with all input nodes.

    /*      (r[1] - q[1]) / (r[0] - q[0]) > (q[1] - p[1]) / (q[0] - p[0])   => counter-clockwise
     *      (r[1] - q[1]) * (q[0] - p[0]) > (q[1] - p[1]) * (r[0] - q[0])
     *    (q[1] - p[1]) * (r[0] - q[0]) - (r[1] - q[1]) * (q[0] - p[0]) < 0
     */
    // < 0 means counter-clockwise, we need replace q as next candidate
    private int orientation(int[] p, int[] q, int[] r) {
        return (q[1] - p[1]) * (r[0] - q[0]) - (r[1] - q[1]) * (q[0] - p[0]);
    }
    private boolean isBetween(int[] p, int[] i, int[] q) {
        boolean x = (p[0] <= i[0] && i[0] <= q[0]) || (p[0] >= i[0] && i[0] >= q[0]);
        boolean y = (p[1] <= i[1] && i[1] <= q[1]) || (p[1] >= i[1] && i[1] >= q[1]);
        return x && y;
    }
    public int[][] outerTrees(int[][] trees) {
        int leftmost = 0;
        int n = trees.length;
        Set<int[]> candidates = new HashSet<>();    // remove duplicates!
        if (n < 4) {
            for (int[] tree: trees)
                candidates.add(tree);
            return candidates.toArray(new int[candidates.size()][]);
        }

        // find leftmost tree
        for(int i = 0; i < n; i++) {
            if(trees[leftmost][0] > trees[i][0]) {
                leftmost = i;
            }
        }
        int p = leftmost;
        do {
            int q = (p + 1) % n;
            for(int i = 0; i < n; i++) {
                if(orientation(trees[p], trees[i], trees[q]) < 0) {
                    q = i;
                }
            }
            for(int i = 0; i < n; i++) {
                if(i != p && i != q && orientation(trees[p], trees[i], trees[q]) == 0 && isBetween(trees[p], trees[i], trees[q])) {
                    candidates.add(trees[i]);
                }
            }
            candidates.add(trees[q]);
            p = q;
        } while(leftmost != p);
        return candidates.toArray(new int[candidates.size()][]);
    }

    // Graham Scan: O(nlgn) O(n)
    private int dist(int[] p, int[] q) {
        return (q[0] - p[0]) * (q[0] - p[0]) + (q[1] - p[1]) * (q[1] - p[1]);
    }
    private int[] findBottom(int[][] trees) {
        int[] res = trees[0];
        for(int[] tree : trees) {
            if(res[1] > tree[1]) {
                res = tree;
            }
        }
        return res;
    }
    public int[][] outerTrees1(int[][] trees) {
        int n = trees.length;
        if(n < 4) return trees;
        int[] bottom = findBottom(trees);
        Arrays.sort(trees, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                int diff = orientation(bottom, a, b) - orientation(bottom, b, a);
                if(diff == 0) return dist(bottom, a) - dist(bottom, b);
                return diff > 0 ? 1 : -1;
            }
        });
        int i = n - 1;
        // nodes in last edge should start from farthest to cloest, so we need to reverse them
        while(i >= 0 && orientation(bottom, trees[n-1], trees[i]) == 0) {
            i--;
        }
        for(int l = i + 1, r = n - 1; l < r; l++, r--) {
            int[] tmp = trees[l];
            trees[l] = trees[r];
            trees[r] = tmp;
        }
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(trees[0]);
        stack.push(trees[1]);
        for(int j = 2; j < n; j++) {
            int[] top = stack.pop();
            while(orientation(stack.peek(), top, trees[j]) > 0) {
                top = stack.pop();
            }
            stack.push(top);
            stack.push(trees[j]);
        }
        return stack.toArray(new int[stack.size()][]);
    }
}
