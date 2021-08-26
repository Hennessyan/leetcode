package amazon;

import java.util.*;

// Serialize and Deserialize N-ary Tree
public class Q428 {
    // https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/solution/
    // check solutions use char for 0 <= value <= 65535, and wrap int to save space !!
    // todo solution2 - 4, write again
    class Codec {
        // Encodes a tree to a single string.
        public String serialize(Node root) {
            if(root == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            dfs(root, sb);
            return sb.toString();
        }
        private void dfs(Node root, StringBuilder sb) {
            if(root == null) {
                // sb.append("#,"); // do not need this line for n-nary tree
                return;
            }
            sb.append(root.val).append(",");
            int size = root.children.size();
            sb.append(size).append(",");
            for(Node node : root.children) {
                dfs(node, sb);
            }
        }

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {
            if(data == null || data.length() == 0) {
                return null;
            }
            Deque<String> deque = new LinkedList<>(Arrays.asList(data.split("\\,")));
            return help(deque);
        }
        private Node help(Deque<String> deque) {
            if(deque.size() == 0) {
                return null;
            }
            // if(deque.getFirst().equals("#")) {
            //     deque.removeFirst();
            //     return null;
            // }
            Node node = new Node(Integer.parseInt(deque.removeFirst()), new ArrayList<>());
            int size = Integer.parseInt(deque.removeFirst());   //注意要提出来,如果写在了for中会每次都call remove,overflow
            for(int i = 0; i < size; i++) {
                node.children.add(help(deque));
            }
            return node;
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
    };
     // method2
//    class Codec {
//
//        class WrappableInt {
//            private int value;
//            public WrappableInt(int x) {
//                this.value = x;
//            }
//            public int getValue() {
//                return this.value;
//            }
//            public void increment() {
//                this.value++;
//            }
//        }
//
//        // Encodes a tree to a single string.
//        public String serialize(Node root) {
//
//            StringBuilder sb = new StringBuilder();
//            this._serializeHelper(root, sb);
//            return sb.toString();
//        }
//
//        private void _serializeHelper(Node root, StringBuilder sb) {
//
//            if (root == null) {
//                return;
//            }
//
//            // Add the value of the node
//            sb.append((char) (root.val + '0'));
//
//            // Add the number of children
//            sb.append((char) (root.children.size() + '0'));
//
//            // Recurse on the subtrees and build the
//            // string accordingly
//            for (Node child : root.children) {
//                this._serializeHelper(child, sb);
//            }
//        }
//
//        // Decodes your encoded data to tree.
//        public Node deserialize(String data) {
//            if(data.isEmpty())
//                return null;
//
//            return this._deserializeHelper(data, new WrappableInt(0));
//        }
//
//        private Node _deserializeHelper(String data, WrappableInt index) {
//
//            if (index.getValue() == data.length()) {
//                return null;
//            }
//
//            // The invariant here is that the "index" always
//            // points to a node and the value next to it
//            // represents the number of children it has.
//            Node node = new Node(data.charAt(index.getValue()) - '0', new ArrayList<Node>());
//            index.increment();
//            int numChildren = data.charAt(index.getValue()) - '0';
//            index.increment();  // more make sense.
//            for (int i = 0; i < numChildren; i++) {
//                // index.increment();
//                node.children.add(this._deserializeHelper(data, index));
//            }
//
//            return node;
//        }
//    }
}
