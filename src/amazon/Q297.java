package amazon;

import common.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//不可以用dfs，说是sparse的binary tree所以dfs会有很多null占空间，强迫你用bfs并且需要尽量‍‌‍‌‍‌‍‌‍‌‍‌‍‍‌‌‌压缩空间的算法。
//Serialize and Deserialize Binary Tree
public class Q297 {
    //O(n) O(n)
    //DFS
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            dfs(root, sb);
            return sb.toString();
        }
        private void dfs(TreeNode root, StringBuilder sb) {
            if(root == null) {
                sb.append("null").append(",");
                return;
            }
            sb.append(root.val + ",");
            dfs(root.left, sb);
            dfs(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            List<String> list = new LinkedList<>(Arrays.asList(data.split("\\,")));
            return help(list);
        }
        private TreeNode help(List<String> list) {
            // if(list.isEmpty()) {
            //     return null;
            // }
            if(list.get(0).equals("null")) {
                list.remove(0);
                return null;
            }
            TreeNode node = new TreeNode(Integer.valueOf(list.remove(0)));
            node.left = help(list);
            node.right = help(list);
            return node;
        }
    }
    //BFS
    public class Codec1 {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root == null) {
                return "";
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            StringBuilder sb = new StringBuilder();
            while(!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                if(cur == null) {
                    sb.append("null,");
                    continue;
                }
                sb.append(cur.val + ",");
                queue.add(cur.left);
                queue.add(cur.right);
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(data == null || data.length() == 0) {
                return null;
            }
            List<String> list = new LinkedList<>(Arrays.asList(data.split("\\,")));
            if(list.isEmpty()) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(list.get(0)));	//注意用bfs的时候两个method最先的if都不能少,否则这里会exception
            int i = 1;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if(!list.get(i).equals("null")) {
                    node.left = new TreeNode(Integer.valueOf(list.get(i)));
                    queue.add(node.left);
                }
                i++;
                if(!list.get(i).equals("null")) {
                    node.right = new TreeNode(Integer.valueOf(list.get(i)));
                    queue.add(node.right);
                }
                i++;
            }
            return root;
        }
    }
    //better way
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node.left != null && node.right != null) {
                sb.append(node.val + "a");
                queue.add(node.left);
                queue.add(node.right);
            }else if(node.left != null) {
                sb.append(node.val).append("b");
                queue.add(node.left);
            }else if(node.right != null) {
                sb.append(node.val).append("c");
                queue.add(node.right);
            }else {
                sb.append(node.val).append("d");
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length() == 0) {
            return null;
        }
        System.out.println(data);
        int l = 0, r = 0;
        List<Pair> list = new LinkedList<>();
        while(r < data.length()) {
            while(r < data.length() && !Character.isLetter(data.charAt(r))) {
                r++;
            }
            int val = Integer.valueOf(data.substring(l, r));    //不能使用val = val * 10 + c - '0'; 会有负数
            l = r + 1;
            list.add(new Pair(val, data.charAt(r++)));
        }
        return help(list);
    }
    private TreeNode help(List<Pair> list) {
        if(list == null || list.size() == 0) {
            return null;
        }
        TreeNode root = new TreeNode(list.get(0).val);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1, j = 0;
        while(!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            char type = list.get(j++).type;
            if(type == 'a') {
                tmp.left = new TreeNode(list.get(i++).val);
                tmp.right = new TreeNode(list.get(i++).val);
                queue.add(tmp.left);
                queue.add(tmp.right);
            }else if(type == 'b') {
                tmp.left = new TreeNode(list.get(i++).val);
                queue.add(tmp.left);
            }else if(type == 'c') {
                tmp.right = new TreeNode(list.get(i++).val);
                queue.add(tmp.right);
            }
        }
        return root;
    }
    class Pair {
        int val;
        char type;
        public Pair(int val, char c) {
            this.val = val;
            this.type = c;
        }
    }

    private LinkedList<Node> convert(String data) {
        LinkedList<Node> list = new LinkedList<>();
        int l = 0, r = 0, len = data.length();
        char[] chs = data.toCharArray();
        while(r < len) {
            if(Character.isLetter(chs[r])) {
                int val = Integer.parseInt(data.substring(l, r));
                l = r + 1;
                char type = chs[r];
                list.add(new Node(val, type));
            }
            r++;
        }
        return list;
    }
    class Node {
        int val;
        char type;
        public Node(int val, char type) {
            this.val = val;
            this.type = type;
        }
    }
}

