package amazon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.function.BiFunction;

// Design an Expression Tree With Evaluate Function
public class Q1628 {

    abstract class Node {
        public abstract int evaluate();
        // define your fields here
    };

    class TreeNode extends Node {
        String val;
        TreeNode left, right;
        public TreeNode(String val, TreeNode l, TreeNode r) {
            this.val = val;
            this.left = l;
            this.right = r;
        }
        public int evaluate() {
            if(this.left == null && this.right == null) {
                return Integer.parseInt(this.val);
            }
            int res = 0;
            if(val.equals("+")) {
                res = this.left.evaluate() + this.right.evaluate();
            } else if(val.equals("-")) {
                res = this.left.evaluate() - this.right.evaluate();
            } else if(val.equals("*")) {
                res = this.left.evaluate() * this.right.evaluate();
            } else if(val.equals("/")) {
                res = this.left.evaluate() / this.right.evaluate();
            }
            return res;

        }
    }

    class TreeBuilder {
        Node buildTree(String[] postfix) {
            Deque<TreeNode> stack = new ArrayDeque<>();
            String operators = "+-*/";
            for(String cur : postfix) {
                if(operators.contains(cur)) {
                    TreeNode r = stack.pop();
                    TreeNode l = stack.pop();
                    TreeNode node = new TreeNode(cur, l, r);
                    stack.push(node);
                } else {
                    stack.push(new TreeNode(cur, null, null));
                }
            }
            return stack.peek();
        }
    };

//    class Node {
//        static final Map<String, BiFunction<Integer, Integer, Integer>> OPERATIONS =
//                // Java9
//                Map.ofEntries(
//                        Map.entry("+", (op1, op2) -> op1 + op2),
//                        Map.entry("-", (op1, op2) -> op1 - op2),
//                        Map.entry("*", (op1, op2) -> op1 * op2),
//                        Map.entry("/", (op1, op2) -> op1 / op2)
//                );
//
//        Node(String val, Node left, Node right){
//            this.val = val;
//            this.left = left;
//            this.right = right;
//        }
//
//        final Node left;
//        final Node right;
//        final String val;
//
//        public int evaluate(){
//            if(OPERATIONS.containsKey(val)){
//                return OPERATIONS.get(val).apply(left.evaluate(), right.evaluate());
//            }
//            return Integer.valueOf(val);
//        }
//    };
//
//    class TreeBuilder {
//        Node buildTree(String[] postfix) {
//            Stack<Node> stack = new Stack<>();
//            for(String token: postfix){
//                if(Node.OPERATIONS.keySet().contains(token)){
//                    // operator
//                    Node o2 = stack.pop();
//                    Node o1 = stack.pop();
//                    stack.push(new Node(token, o1, o2));
//                } else{
//                    // operand
//                    stack.push(new Node(token, null, null));
//                }
//            }
//            return stack.pop();
//        }
//    };
}
