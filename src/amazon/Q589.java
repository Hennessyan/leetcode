package amazon;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// N-ary Tree Preorder Traversal
public class Q589 {

    // O(n) O(n)
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        Node node;
        while(!stack.isEmpty()) {
            node = stack.pop();
            res.add(node.val);
            if(node.children.size() > 0) {
                for(int i = node.children.size() - 1; i >= 0; i--) {
                    stack.push(node.children.get(i));
                }
            }
        }
        return res;
    }

    public List<Integer> preorder1(Node root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list;
    }

    private void helper(Node root, List<Integer> list){
        if(root==null){
            return;
        }
        list.add(root.val);
        for(Node child:root.children){
            helper(child,list);
        }
    }
}
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
}