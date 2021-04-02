package facebook;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

// Simplify Path
public class Q71 {
    // O(n) O(n)
    public String simplifyPath(String path) {
        if(path == null || path.length() == 0) {
            return "";
        }
        String res = "";
        Deque<String> stack = new ArrayDeque<>();
        String[] dirs = path.split("/");

        for(String dir : dirs) {
            if(dir.equals("..") && !stack.isEmpty()) {
                stack.pop();
            } else if(!dir.equals("..") && !dir.equals(".") && !dir.equals("")) {
                stack.push(dir);
            }
        }
        while(!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }
        return res.length() == 0 ? "/" : res;
    }

    public String simplifyPath1(String path) {
        if(path == null || path.length() == 0) {
            return "";
        }
        String res = "";
        Stack<String> stack = new Stack<String>();  // stack here !!!
        String[] dirs = path.split("/");

        for(String dir : dirs) {
            if(dir.equals("..") && !stack.isEmpty()) {
                stack.pop();
            } else if(!dir.equals("..") && !dir.equals(".") && !dir.equals("")) {
                stack.push(dir);
            }
        }
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/");
            result.append(dir);
        }
        return result.length() == 0 ? "/" : result.toString();
    }
}
