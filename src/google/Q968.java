package google;

import common.TreeNode;

import java.util.HashSet;
import java.util.Set;

// Binary Tree Cameras
// Q979
public class Q968 {
    // O(n) O(n)
    int camera;
    Set<TreeNode> seen;
    public int minCameraCover(TreeNode root) {
        camera = 0;
        seen = new HashSet<>();
        seen.add(null);
        dfs(root, null);
        return camera;
    }
    private void dfs(TreeNode cur, TreeNode par) {
        if(cur != null) {
            dfs(cur.left, cur);
            dfs(cur.right, cur);

            if(par == null && !seen.contains(cur) ||
                    !seen.contains(cur.left) ||
                    !seen.contains(cur.right)) {
                camera++;
                seen.add(par);
                seen.add(cur);
//                seen.add(cur.left);   // unnecessary
//                seen.add(cur.right);  // unnecessary
            }
        }
    }

    // 0 - need add camera, 1 - camera, 2 - covered
    int ans;
    public int minCameraCover1(TreeNode root) {
        ans = 0;
        return cover(root) == 0 ? ++ans : ans;
    }
    private int cover(TreeNode root) {
        if(root == null) return 2;
        int l = cover(root.left);
        int r = cover(root.right);
        if(l == 0 || r == 0) {
            ans++;
            return 1;
        }
        if(l == 1 || r == 1) {
            return 2;
        }
        return 0;
    }

    int camera1 = 0;
    public enum Camera { HAS_CAMERA, COVERED, PLEASE_COVER };

    public int minCameraCover2(TreeNode root) {
        // If root is not covered then we need to place a camera at root node
        return cover1(root) == Camera.PLEASE_COVER ? ++camera1 : camera1;
    }

    public Camera cover1(TreeNode root) {

        // Base case - if there is no node then it's already covered
        if (root == null)
            return Camera.COVERED;

        // Try to cover left and right children's subtree
        Camera l = cover1(root.left);
        Camera r = cover1(root.right);

        // If Any one of the children is not covered then we must place a camera at current node
        if (l ==  Camera.PLEASE_COVER || r == Camera.PLEASE_COVER) {
            camera1++;
            return Camera.HAS_CAMERA;
        }

        // If any one of left or right node has Camera then the current node is also covered
        if (l== Camera.HAS_CAMERA || r == Camera.HAS_CAMERA)
            return Camera.COVERED;

        // If None of the children is covering the current node then ask it's parent to cover
        return Camera.PLEASE_COVER;
    }
}
