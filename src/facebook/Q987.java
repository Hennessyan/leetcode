package facebook;

import common.TreeNode;

import java.util.*;

//Vertical Order Traversal of a Binary Tree
// Q314
public class Q987 {
    // O(n + k * n/k * lg(n/k)) => O(nlg(n/k)) O(n)
    // k - width of tree (num of cols)
    int min, max;
    Map<Integer, List<int[]>> map;
    public List<List<Integer>> verticalTraversal0(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        map = new HashMap<>();
        min = 0;
        max = 0;
        dfs1(root, 0, 0);
        for(int i = min; i <= max; i++) {
            List<int[]> tmp = map.get(i);
            List<Integer> list = new ArrayList<>();
            Collections.sort(tmp, (a, b) -> (a[0] != b[0]) ? a[0] - b[0] : a[1] - b[1]);
            for(int[] pair : tmp) {
                list.add(pair[1]);
            }
            res.add(list);
        }
        return res;
    }
    private void dfs1(TreeNode node, int r, int c) {
        if(node != null) {
            map.computeIfAbsent(c, x -> new ArrayList<>()).add(new int[]{r, node.val});
            min = Math.min(min, c);
            max = Math.max(max, c);
            dfs1(node.left, r + 1, c - 1);
            dfs1(node.right, r + 1, c + 1);
        }
    }

    // DFS : O(nlgn) O(n)
    List<Location> list;
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        list = new ArrayList<>();
        dfs(root, 0, 0);
        Collections.sort(list);
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        int curCol = list.get(0).c;
        for(Location loc : list) {
            if(curCol == loc.c) {
                res.get(res.size() - 1).add(loc.val);
            } else {
                curCol = loc.c;
                res.add(new ArrayList<>());
                res.get(res.size() - 1).add(loc.val);
            }
        }
        return res;
    }
    private void dfs(TreeNode node, int r, int c) {
        if(node != null) {
            list.add(new Location(r, c, node.val));
            dfs(node.left, r + 1, c - 1);
            dfs(node.right, r + 1, c + 1);
        }
    }
    // BFS : O(nlgn) O(n)
    public List<List<Integer>> verticalTraversal1(TreeNode root) {
        List<Location> list = new ArrayList<>();
        Queue<TreeNode> tq = new LinkedList<>();
        Queue<Integer> iq = new LinkedList<>();
        tq.add(root);
        iq.add(0);
        int row = 0;
        while(!tq.isEmpty()) {
            int size = tq.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = tq.poll();
                int index = iq.poll();
                list.add(new Location(row, index, node.val));
                if(node.left != null) {
                    tq.add(node.left);
                    iq.add(index - 1);
                }
                if(node.right != null) {
                    tq.add(node.right);
                    iq.add(index + 1);
                }
            }
            row++;
        }

        Collections.sort(list);
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        int curCol = list.get(0).c;
        for(Location loc : list) {
            if(curCol == loc.c) {
                res.get(res.size() - 1).add(loc.val);
            } else {
                curCol = loc.c;
                res.add(new ArrayList<>());
                res.get(res.size() - 1).add(loc.val);
            }
        }
        return res;
    }

    class Location implements Comparable<Location> {
        int r, c, val;
        public Location(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }
        @Override
        public int compareTo(Location that) {
            if(this.c != that.c) {
                return this.c - that.c;
            } else if(this.r != that.r) {
                return this.r - that.r;
            } else {
                return this.val - that.val;
            }
        }
    }
}
