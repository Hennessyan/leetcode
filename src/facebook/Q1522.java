package facebook;

import java.util.ArrayList;
import java.util.List;

// Diameter of N-Ary Tree
// Q543(E)
public class Q1522 {
    // distance with height
    // check solution: distance with depth.
    int max;
    public int diameter(Node root) {
        max = 0;
        helper(root);
        return max;
    }
    private int helper(Node node) {
        if(node == null) return 0;
        int first = 0, second = 0;
        for(Node child : node.children) {
            int val = helper(child);
            if(val > first) {
                second = first;
                first = val;
            } else if(val > second) {
                second = val;
            }
        }
        max = Math.max(max, first + second);
        return Math.max(first, second) + 1;
    }

    class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
