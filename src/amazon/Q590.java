package amazon;

import java.util.*;

// N-ary Tree Postorder Traversal
public class Q590 {

    List<Integer> list;
    public List<Integer> postorder1(Node root) {
        list = new ArrayList<>();
        helper(root);
        return list;
    }
    private void helper(Node root) {
        if(root != null) {
            for(Node child : root.children) {
                helper(child);
            }
            list.add(root.val);
        }
    }

    public List<Integer> postorder2(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            output.addFirst(node.val);
            for (Node item : node.children) {
                if (item != null) {
                    stack.add(item);
                }
            }
        }
        return output;
    }

    public List<Integer> postorder(Node root) {
        list = new ArrayList<>();
        if(root == null) {
            return list;
        }
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        Node node = null;
        while(!stack.isEmpty()) {
            node = stack.pop();
            list.add(node.val);
            for(Node child : node.children) {
                stack.push(child);
            }
        }
        return list;
    }

}
