package google;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//Rectangle Area II
public class Q850 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Q850 q = new Q850();
        System.out.println((int) (1e9+7));
    }
    //O(n^3) O(n^2)
    public int rectangleArea(int[][] rectangles) {
        Set<Integer> axisX = new HashSet<>();
        Set<Integer> axisY = new HashSet<>();
        for(int[] rect : rectangles) {
            axisX.add(rect[0]);
            axisX.add(rect[2]);
            axisY.add(rect[1]);
            axisY.add(rect[3]);
        }
        Integer[] arrX = axisX.toArray(new Integer[axisX.size()]);	//需要是Integer,不能用int,因为 ： T
        Arrays.sort(arrX);
        Integer[] arrY = axisY.toArray(new Integer[axisY.size()]);
        Arrays.sort(arrY);
        Map<Integer, Integer> mapX = new HashMap<>();
        Map<Integer, Integer> mapY = new HashMap<>();
        for(int i = 0; i < arrX.length; i++) {
            mapX.put(arrX[i], i);
        }
        for(int i = 0; i < arrY.length; i++) {
            mapY.put(arrY[i], i);
        }
        boolean[][] grid = new boolean[arrX.length][arrY.length];
        for(int[] rect : rectangles) {
            for(int x = mapX.get(rect[0]); x < mapX.get(rect[2]); x++) {
                for(int y = mapY.get(rect[1]); y < mapY.get(rect[3]); y++) {
                    grid[x][y] = true;
                }
            }
        }
        long ans = 0;
        for(int i = 0; i < arrX.length; i++) {
            for(int j = 0; j < arrY.length; j++) {
                if(grid[i][j]) {
                    ans += (long) (arrX[i+1] - arrX[i]) * (arrY[j+1] - arrY[j]);
                }
            }
        }
        ans %= (1000000007);	//(int)(1e9+7)也对,但不能直接1e9+7
        return (int) ans;
    }
    //line sweep : O(n^2lgn) (n)
    public int rectangleArea1(int[][] rectangles) {
        int open = 0, close = 1;
        int len = rectangles.length;
        int[][] lines = new int[len*2][];
        for(int i = 0; i < len; i++) {
            int[] rect = rectangles[i];
            lines[i*2] = new int[]{rect[1], open, rect[0], rect[2]};
            lines[i*2+1] = new int[]{rect[3], close, rect[0], rect[2]};
        }
        Arrays.sort(lines, (l1, l2) -> l1[0] - l2[0]);
        long ans = 0;
        List<int[]> actives = new ArrayList<>();
        int cur_y = lines[0][0];
        for(int i = 0; i < lines.length; i++) {
            int[] line = lines[i];
            long query = 0;
            int cur_x = -1;
            for(int[] active : actives) {
                cur_x = Math.max(cur_x, active[0]);
                query += Math.max(active[1] - cur_x, 0);
                cur_x = Math.max(cur_x, active[1]);
            }
            ans += query * (line[0] - cur_y);
            if(line[1] == open) {
                actives.add(new int[]{line[2], line[3]});
                Collections.sort(actives, (a1, a2) -> a1[0] - a2[0]);
            }else {
                for(int j = 0; j < actives.size(); j++) {
                    int[] active = actives.get(j);
                    if(active[0] == line[2] && active[1] == line[3]) {
                        actives.remove(j);
                        break;
                    }
                }
            }
            cur_y = line[0];
        }
        ans %= (int) (1e9 + 7);
        return (int)ans;
    }
    //O(nlgn) O(n)
    public int rectangleArea2(int[][] rectangles) {
        int open = 1, close = -1;
        int len = rectangles.length;
        int[][] lines = new int[len*2][];
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < len; i++) {
            int[] rect = rectangles[i];
            lines[2*i] = new int[]{rect[1], open, rect[0], rect[2]};
            lines[2*i+1] = new int[]{rect[3], close, rect[0], rect[2]};
            set.add(rect[0]);
            set.add(rect[2]);
        }
        Arrays.sort(lines, (l1, l2) -> l1[0] - l2[0]);
        Integer[] axisX = set.toArray(new Integer[set.size()]);
        Arrays.sort(axisX);
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < axisX.length; i++) {
            map.put(axisX[i], i);
        }
        Node node = new Node(0, axisX.length - 1, axisX);
        long ans = 0;
        long query = 0;
        int cur_y = lines[0][0];
        for(int[] line : lines) {
            ans += query * (line[0] - cur_y);
            query = node.update(map.get(line[2]), map.get(line[3]), line[1]);
            cur_y = line[0];
        }
        ans %= (int)(1e9+7);
        return (int) ans;
    }
    class Node{
        int lRegion, rRegion;
        Node left, right;
        Integer[] X;
        long total;
        int count;
        public Node(int start, int end, Integer[] X) {
            lRegion = start;
            rRegion = end;
            this.X = X;
            total = 0;
            count = 0;
            left = null;
            right = null;
        }
        private int getMedian() {
            return lRegion + (rRegion - lRegion) / 2;
        }
        public Node getLeft() {
            if(left == null) left = new Node(lRegion, getMedian(), X);
            return left;
        }
        public Node getRight() {
            if(right == null) right = new Node(getMedian(), rRegion, X);
            return right;
        }
        public long update(int i, int j, int val) {
            if(i >= j) {
                return 0;
            }
            if(lRegion == i && rRegion == j) {
                count += val;
            }else {
                getLeft().update(i, Math.min(j, getMedian()), val);
                getRight().update(Math.max(i, getMedian()), j, val);
            }

            if(count > 0) {
                total = X[rRegion] - X[lRegion];
            }else {
                total = getLeft().total + getRight().total;
            }
            return total;
        }
    }
}
