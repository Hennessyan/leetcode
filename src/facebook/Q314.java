package facebook;

import common.TreeNode;

import java.util.*;

// Binary Tree Vertical Order Traversal
public class Q314 {
    //O(n) O(n)
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> tQ = new LinkedList<>();
        Queue<Integer> iQ = new LinkedList<>();
        tQ.offer(root);
        iQ.offer(0);
        int min = 0, max = 0;	//必须是0，对于只有根的一个树来说才返回正确结果.
        while(!tQ.isEmpty()){
            TreeNode tmp = tQ.poll();
            int index = iQ.poll();
            if(!map.containsKey(index)){
                map.put(index, new ArrayList<>());
            }
            map.get(index).add(tmp.val);
            if(tmp.left != null){
                min = Math.min(min, index - 1);
                tQ.offer(tmp.left);
                iQ.offer(index - 1);
            }
            if(tmp.right != null){
                max = Math.max(max, index + 1);
                tQ.offer(tmp.right);
                iQ.offer(index + 1);
            }
        }
        for(int i = min; i <= max; i++){
            res.add(map.get(i));
        }
        return res;
    }
    // DFS : O(whlgh) O(n) - w - width of tree, h - height of tree
    int min, max;
    Map<Integer, List<int[]>> map;
    public List<List<Integer>> verticalOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        min = 0;
        max = 0;
        map = new HashMap<>();
        dfs(root, 0, 0);


        for(int i = min; i <= max; i++) {
            List<Integer> list = new ArrayList<>();
            List<int[]> l = map.get(i);
            Collections.sort(l, (a,b) -> a[0] - b[0]);
            for(int[] tmp : l) {
                list.add(tmp[1]);
            }
            res.add(list);
        }
        return res;
    }
    private void dfs(TreeNode node, int row, int col) {
        if(node != null) {
            map.computeIfAbsent(col, x -> new ArrayList<>()).add(new int[]{row, node.val});
            if(node.left != null) {
                dfs(node.left, row + 1, col - 1);
                min = Math.min(min, col - 1);
            }
            if(node.right != null) {
                dfs(node.right, row + 1, col + 1);
                max = Math.max(max, col + 1);
            }
        }
    }
}
