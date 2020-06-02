package challenge.may;
// Check If It Is a Straight Line
public class Q1232 {

    public static void main(String[] args) {
        Q1232 q = new Q1232();

    }

    /**          y2 - y1 / x2 - x1 = y3 - y2 / x3 - x2
     *           (y2 - y1) * (x3 - x2) = (y3 - y2) * (x2 - x1)
     */
    public boolean checkStraightLine(int[][] coordinates) {
        int ydiff = coordinates[1][1] - coordinates[0][1];
        int xdiff = coordinates[1][0] - coordinates[0][0];
        // cross product to avoid dividing 0.
        for(int i = 2; i < coordinates.length; i++) {
            int y_diff = coordinates[i][1] - coordinates[i - 1][1]; //点得是连着点,不然可能是平行的情况.
            int x_diff = coordinates[i][0] - coordinates[i - 1][0];
            if(ydiff * x_diff != y_diff * xdiff) {
                return false;
            }
        }
        return true;
    }
}
