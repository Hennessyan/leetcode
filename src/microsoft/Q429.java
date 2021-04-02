package microsoft;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// N-ary Tree Level Order Traversal
public class Q429 {
    // BFS : O(n) O(n)
//    public List<List<Integer>> levelOrder(Node root) {
//        List<List<Integer>> res = new ArrayList<>();
//        if(root == null) {
//            return res;
//        }
//        Queue<Node> queue = new LinkedList<>();
//        queue.offer(root);
//        List<Integer> list;
//        while(!queue.isEmpty()) {
//            int size = queue.size();
//            list = new ArrayList<>();
//            for(int i = 0; i < size; i++) {
//                Node tmp = queue.poll();
//                list.add(tmp.val);
//                queue.addAll(tmp.children);
//            }
//            res.add(list);
//        }
//        return res;
//    }
      // DFS : O(n) O(n)
//    List<List<Integer>> res = new ArrayList<>();
//    public List<List<Integer>> levelOrder1(Node root) {
//        if(root == null) {
//            return res;
//        }
//        dfs(root, 0);
//        return res;
//    }
//    private void dfs(Node node, int i) {
//        if(node == null) {
//            return;
//        }
//        if(res.size() == i) {
//            res.add(new ArrayList<>());
//        }
//        res.get(i).add(node.val);
//        for(Node n : node.children) {
//            dfs(n, i + 1);
//        }
//    }
}
