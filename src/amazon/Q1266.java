package amazon;
// Minimum Time Visiting All Points
public class Q1266 {


    public int minTimeToVisitAllPoints(int[][] points) {
        int res = 0;

        for(int i = 0; i < points.length-1; i++){
            int x = Math.abs(points[i+1][0] - points[i][0]);
            int y = Math.abs(points[i+1][1] - points[i][1]);
            res += Math.max(x,y);
        }

        return res;
    }

    public int minTimeToVisitAllPoints1(int[][] points) {
        if(points == null || points.length < 2) {
            return 0;
        }
        int ans = 0, n = points.length;
        for(int i = 0; i < n - 1; i++) {
            ans += distance(points, i, i + 1);
        }
        return ans;
    }
    private int distance(int[][] points, int i, int j) {
        int x = points[i][0] < points[j][0] ? 1 : -1;
        int y = points[i][1] < points[j][1] ? 1 : -1;
        int step = 0;
        while(points[i][0]  != points[j][0] && points[i][1] != points[j][1]) {
            points[i][0] += x;
            points[i][1] += y;
            step++;
        }
        if(points[i][0] == points[j][0]) {
            step += Math.abs(points[i][1] - points[j][1]);
        }
        if(points[i][1] == points[j][1]) {
            step += Math.abs(points[i][0] - points[j][0]);
        }
        return step;
    }
}
