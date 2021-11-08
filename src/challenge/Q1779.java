package challenge;
// Find Nearest Point That Has the Same X or Y Coordinate
// doordash
public class Q1779 {
    // O(n) O(1)
    public int nearestValidPoint(int x, int y, int[][] points) {
        int minDistance = Integer.MAX_VALUE;
        int res = -1;

        for (int i = 0; i < points.length; i++) {
            if (points[i][0] == x || points[i][1] == y) {
                int distance = Math.abs(x - points[i][0]) + Math.abs(y - points[i][1]);
                if(distance == 0) return i; // stop earlier if possible
                if (minDistance > distance) {
                    minDistance = distance;
                    res = i;
                }
            }
        }
        return res;
    }
}
