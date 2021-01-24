package google;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// Find Root of N-Ary Tree
public class Q1506 {
    // O(n) o(n)
    public Node findRoot(List<Node> tree) {
        // set that contains all the child nodes.
        HashSet<Integer> seen = new HashSet<Integer>();

        // add all the child nodes into the set
        // O(n + n - 1) - each node twice except root once
        for (Node node : tree) {
            for (Node child : node.children)
                // we could either add the value or the node itself.
                seen.add(child.val);
        }

        Node root = null;
        // find the node that is not in the child node set.
        // O(n)
        for (Node node : tree) {
            if (!seen.contains(node.val)) {
                root = node;
                break;
            }
        }
        return root;
    }
    // O(n) O(1) - 这个题的思路是ROOT被访问一次,其余NODE两次. 那么可以看作一个ARRAY中找到重复一次的数(其他都是两次重复)
    public Node findRoot1(List<Node> tree) {
        int val = 0;
        for(Node node : tree) {
            val ^= node.val;            // val += node.val;
            for(Node child : node.children) {
                val ^= child.val;      //  val -= child.val;
            }
        }
        Node root = null;
        for(Node node : tree) {
            if(node.val == val) {
                root = node;
                break;
            }
        }
        return root;
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

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
