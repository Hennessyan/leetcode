package amazon;
//  Clone Binary Tree With Random Pointer
public class Q1485 {

//    Map<Node, NodeCopy> map = new HashMap<>();
//    public NodeCopy copyRandomBinaryTree(Node root) {
//        if(root == null) {
//            return null;
//        }
//        if(map.containsKey(root)) {
//            return map.get(root);
//        }
//        NodeCopy node = new NodeCopy(root.val);
//        map.put(root, node);
//
//        node.left = copyRandomBinaryTree(root.left);
//        node.right = copyRandomBinaryTree(root.right);
//        node.random = copyRandomBinaryTree(root.random);
//        return node;
//    }
      // O(2n) O(n)
//    public NodeCopy copyRandomBinaryTree(Node root) {
//        if (root == null) {
//            return null;
//        }
//
//        // Step 1. Create a copy of each node
//        Map<Node, NodeCopy> copy = new HashMap<>();
//        Stack<Node> stack = new Stack<>();
//        stack.push(root);
//
//        while (!stack.isEmpty()) {
//            Node node = stack.pop();
//            NodeCopy copyNode = new NodeCopy(node.val);
//            copy.put(node, copyNode);
//
//            if (node.left != null) {
//                stack.push(node.left);
//            }
//            if (node.right != null) {
//                stack.push(node.right);
//            }
//        }
//
//        // Step 2. Connect the copied nodes together
//        stack.push(root);
//        while (!stack.isEmpty()) {
//            Node node = stack.pop();
//
//            if (node.left != null) {
//                copy.get(node).left = copy.get(node.left);
//                stack.push(node.left);
//            }
//            if (node.right != null) {
//                copy.get(node).right = copy.get(node.right);
//                stack.push(node.right);
//            }
//            if (node.random != null) {
//                copy.get(node).random = copy.get(node.random);
//            }
//        }
//
//        return copy.get(root);
//    }
      // O(2n) O(n)
//    public NodeCopy copyRandomBinaryTree(Node root) {
//        Map<Node, NodeCopy> copy = new HashMap<>();
//        dfsCopy(root, copy);
//        dfsConnect(root, copy);
//        return copy.get(root);
//    }
//
//    private NodeCopy dfsCopy(Node root, Map<Node, NodeCopy> copy){
//        if (root == null) {
//            return null;
//        }
//        NodeCopy copyNode = new NodeCopy(root.val);
//        copy.put(root, copyNode);
//        dfsCopy(root.left, copy);
//        dfsCopy(root.right, copy);
//        return copyNode;
//    }
//
//    private void dfsConnect(Node node, Map<Node, NodeCopy> copy){
//        if (node == null) {
//            return;
//        }
//        if (node.left != null) {
//            copy.get(node).left = copy.get(node.left);
//            dfsConnect(node.left, copy);
//        }
//        if (node.right != null) {
//            copy.get(node).right = copy.get(node.right);
//            dfsConnect(node.right, copy);
//        }
//        if (node.random != null) {
//            copy.get(node).random = copy.get(node.random);
//        }
//    }
}
