package amazon;

import java.util.*;

// Clone N-ary Tree - same as Q133
public class Q1490 {
    // method1
    public Node cloneTree(Node root) {
        if(root == null) {
            return root;
        }
        Node node = new Node(root.val, new ArrayList<>());
        for(Node child : root.children) {
            node.children.add(cloneTree(child));
        }
        return node;
    }
    // tree will not revisit node, otherwise it's graph -> Q133
    // O(v+e) O(v)
    public Node cloneTree1(Node root) {
        if(root == null) {
            return root;
        }
        Map<Node, Node> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        visited.put(root, new Node(root.val, new ArrayList<>()));

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            Node mirror = visited.get(node);
            for(Node child : node.children) {
                if(!visited.containsKey(child)) {
                    visited.put(child, new Node(child.val, new ArrayList<>()));
                    queue.add(child);
                }
                mirror.children.add(visited.get(child));
            }
        }
        return visited.get(root);
    }

    public Node cloneTree2(Node root) {
        return dfs(root, new HashMap<>());
    }
    private Node dfs(Node node, Map<Node, Node> map) {
        if(node == null) {
            return node;
        }
        if(map.containsKey(node)) {
            return map.get(node);
        }
        Node mirror = new Node(node.val, new ArrayList<>());
        map.put(node, mirror);
        for(Node child : node.children) {
            mirror.children.add(dfs(child, map));
        }
        return mirror;
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
    }
}
