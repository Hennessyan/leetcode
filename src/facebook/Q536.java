package facebook;

import common.TreeNode;
import javafx.util.Pair;

import java.util.Stack;

// Construct Binary Tree from String
public class Q536 {
    // O(n) O(n)
    public TreeNode str2tree(String s) {
        if(s == null || s.length() == 0) {
            return null;
        }
        return str2tree(s, 0).getKey();
    }
    private Pair<TreeNode, Integer> str2tree(String s, int index) {
        if(index == s.length()) {
            return new Pair<>(null, index);
        }
        Pair<Integer ,Integer> p = getNumber(s, index);
        TreeNode node = new TreeNode(p.getKey());
        index = p.getValue();
        if(index < s.length() && s.charAt(index) == '(') {
            Pair<TreeNode, Integer> lp = str2tree(s, index + 1);
            node.left = lp.getKey();
            index = lp.getValue();
        }
        if(index < s.length() && s.charAt(index) == '(') {
            Pair<TreeNode, Integer> rp = str2tree(s, index + 1);
            node.right = rp.getKey();
            index = rp.getValue();
        }
        return new Pair<>(node, index < s.length() && s.charAt(index) == ')' ? index + 1 : index);
    }
    private Pair<Integer, Integer> getNumber(String s, int index) {
        boolean isNegative = false;
        int val = 0;
        if(s.charAt(index) == '-') {
            isNegative = true;
            index++;
        }
        while(index < s.length() && Character.isDigit(s.charAt(index))) {
            val = val * 10 + s.charAt(index++) - '0';
        }
        return new Pair<>(isNegative ? -val : val, index);
    }
    // Stack
    public TreeNode str2tree2(String s) {
        if(s == null || s.length() == 0) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(0), node;
        stack.push(root);
        int index = 0;
        while(index < s.length()) {
            node = stack.pop();
            if(s.charAt(index) == '-' || Character.isDigit(s.charAt(index))) {
                Pair<Integer, Integer> pair = getNumber(s, index);
                node.val = pair.getKey();
                index = pair.getValue();

                if(index < s.length() && s.charAt(index) == '(') {
                    stack.push(node);       // need to add previous node back here
                    TreeNode l = new TreeNode(0);
                    node.left = l;
                    stack.push(l);
                }
            } else if(s.charAt(index) == '(') {
                stack.push(node);           // need to add previous node back here
                TreeNode r = new TreeNode(0);
                node.right = r;
                stack.push(r);
            }
            index++;
        }
        return root;
    }
}
