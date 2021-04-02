package amazon;

import java.util.PriorityQueue;
import java.util.Stack;

// Trapping Rain Water II
public class Q407 {
    // O(mnlg(m+n)) O(mn)
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int trapRainWater(int[][] heightMap) {
        if(heightMap == null || heightMap.length == 0) {
            return 0;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((c1, c2) -> (c1[2] - c2[2]));
        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] seen = new boolean[m][n];
        for(int c = 0; c < n; c++) {
            pq.offer(new int[]{0, c, heightMap[0][c]});
            pq.offer(new int[]{m - 1, c, heightMap[m - 1][c]});
            seen[0][c] = true;
            seen[m - 1][c] = true;
        }
        for(int r = 1; r < m - 1; r++) {
            pq.offer(new int[]{r, 0, heightMap[r][0]});
            pq.offer(new int[]{r, n - 1, heightMap[r][n - 1]});
            seen[r][0] = true;
            seen[r][n - 1] = true;
        }
        int ans = 0;
        while(!pq.isEmpty()) {
            int[] p = pq.poll();
            for(int[] d : dirs) {
                int x = p[0] + d[0];
                int y = p[1] + d[1];
                if(x >= 1 && x < m - 1 && y >= 1 && y < n - 1 && !seen[x][y]) {
                    ans += Math.max(0, p[2] - heightMap[x][y]);
                    pq.offer(new int[]{x, y, Math.max(p[2], heightMap[x][y])});
                    seen[x][y] = true;
                }
            }
        }
        return ans;
    }

    /*method2*/
    //https://leetcode.com/problems/trapping-rain-water-ii/discuss/89467/Why-reinvent-the-wheel-An-easy-understood-commented-solution-based-on-trapping-rain-1
    //此链接中讨论的第一个方法通过首先fill一个特别大的数避免了使用下边列出的可用在Q42的方法.
    // 每次有SPILL就会遍历一遍数组,相对慢很多。 （从"漏水口"开始往内不停的更新）
    // [[12,13,1,12],[13,4,13,12],[13,8,10,12],[12,13,12,12],[13,13,13,13]]
    public int trapRainWater1(int[][] heightMap) {
        /*FIRST STEP*/
        if(heightMap.length == 0) return 0;
        int[][] wetMap = new int[heightMap.length][heightMap[0].length];
        int sum = 0;
        /*row by row*/
        for(int i = 1; i < wetMap.length - 1; i++){
            wetMap[i] = calculate(heightMap[i]);
        }
        /*column by column*/
        for(int i = 1; i < heightMap[0].length - 1; i++){
            int[] col = new int[heightMap.length];
            for(int j = 0; j < heightMap.length; j++){
                col[j] = heightMap[j][i];
            }
            int[] colResult = calculate(col);
            /*update the wetMap to be the bigger value between row and col, later we can spill, don't worry*/
            for(int j = 0; j < heightMap.length; j++){
                wetMap[j][i] = Math.max(colResult[j], wetMap[j][i]);
                sum += wetMap[j][i];
            }
        }
        /*SECOND STEP*/
        boolean spillWater = true;
        int[] rowOffset = {-1,1,0,0};
        int[] colOffset = {0,0,1,-1};
        while(spillWater){
            spillWater = false;
            for(int i = 1; i < heightMap.length - 1; i++){
                for(int j = 1; j < heightMap[0].length - 1; j++){
                    /*If this slot has ever gotten wet, exammine its 4 neightbors*/
                    if(wetMap[i][j] != 0){
                        for(int m = 0; m < 4; m++){
                            int neighborRow = i + rowOffset[m];
                            int neighborCol = j + colOffset[m];
                            int currentHeight = wetMap[i][j] + heightMap[i][j];
                            int neighborHeight = wetMap[neighborRow][neighborCol] +
                                    heightMap[neighborRow][neighborCol];
                            if(currentHeight > neighborHeight){
                                int spilledWater = currentHeight - Math.max(neighborHeight, heightMap[i][j]);
                                //wetMap[i][j] = Math.max(0, wetMap[i][j] - spilledWater);
                                wetMap[i][j] = wetMap[i][j] - spilledWater;	//不需要上边的Math.max
                                sum -= spilledWater;
                                spillWater = true;
                            }
                        }
                    }
                }
            }
        }
        return sum;
    }
    /*Nothing interesting here, the same function for trapping water 1*/
    private int[] calculate (int[] height){
        int[] result = new int[height.length];
        Stack<Integer> s = new Stack<Integer>();
        int index = 0;
        while(index < height.length){
            if(s.isEmpty() || height[index] <= height[s.peek()]){
                s.push(index++);
            }else{
                int bottom = s.pop();
                if(s.size() != 0){
                    for(int i = s.peek() + 1; i < index; i++){
                        result[i] += (Math.min(height[s.peek()], height[index]) - height[bottom]);	//＋必须有
                    }
                }
            }
        }
        return result;
    }
    /*method3 比2好一些,但一个意思*/
    //https://leetcode.com/problems/trapping-rain-water-ii/discuss/89459/First-rain-then-collect:-A-natural-approach-using-BFS

}
