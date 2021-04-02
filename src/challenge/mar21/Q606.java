package challenge.mar21;

import common.TreeNode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

// Construct String from Binary Tree
public class Q606 {
    // O(n) O(n)
    public String tree2str(TreeNode t) {
        if(t==null)
            return "";
        if(t.left==null && t.right==null)
            return t.val+"";
        if(t.right==null)
            return t.val+"("+tree2str(t.left)+")";
        return t.val+"("+tree2str(t.left)+")("+tree2str(t.right)+")";
    }

    public String tree2str1(TreeNode t) {
        if (t == null)
            return "";
        Stack< TreeNode > stack = new Stack < > ();
        stack.push(t);
        Set< TreeNode > visited = new HashSet< >();
        StringBuilder s = new StringBuilder();
        while (!stack.isEmpty()) {
            t = stack.peek();
            if (visited.contains(t)) {
                stack.pop();
                s.append(")");
            } else {
                visited.add(t);
                s.append("(" + t.val);
                if (t.left == null && t.right != null)
                    s.append("()");
                if (t.right != null)
                    stack.push(t.right);
                if (t.left != null)
                    stack.push(t.left);
            }
        }
        return s.substring(1, s.length() - 1);
    }

    /*public String tree2str(TreeNode t) {
        if(t == null) return "";
        String left = tree2str(t.left);
        String right = tree2str(t.right);
        StringBuilder sb = new StringBuilder();
        sb.append(t.val);
        if(left.length() != 0) {
            sb.append("(").append(left).append(")");
        }
        if(right.length() != 0) {
            if(left.length() == 0) {
                sb.append("()");
            }
            sb.append("(").append(right).append(")");
        }
        return sb.toString();
    }*/
}
