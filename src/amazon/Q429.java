package amazon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// N-ary Tree Level Order Traversal
public class Q429 {
    // O(n) O(n)
    List<List<Integer>> res;
    public List<List<Integer>> levelOrder0(Node root) {
        res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        dfs(root, 0);
        return res;
    }
    private void dfs(Node node, int level) {
        if(node == null) return;
        if(res.size() == level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(node.val);
        for(Node child : node.children) {
            dfs(child,level + 1);
        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node node;
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                node = queue.poll();
                list.add(node.val);
                if(node.children.size() > 0) {
                    queue.addAll(node.children);
                }
            }
            res.add(list);
        }
        return res;
    }
}
