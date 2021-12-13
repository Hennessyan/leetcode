package amazon;
// Robot Bounded In Circle
public class Q1041 {
    // O(n) O(1)
    public boolean isRobotBounded(String instructions) {
        // north west south east
        int[][] dirs = {{0,1}, {-1,0}, {0,-1}, {1,0}};
        int x = 0, y = 0, index = 0;
        for(char c : instructions.toCharArray()) {
            if(c == 'L') {
                index = (index + 1) % 4;        // need to set correct dir orders based on the direction change
            } else if(c == 'R') {
                index = (index + 3) % 4;        // should not use index - 1 => overflow
            } else {
                x += dirs[index][0];
                y += dirs[index][1];
            }
        }
        // after one cycle:
        // robot returns into initial position
        // or robot doesn't face north
        return (x == 0 && y == 0) || (index != 0);
    }
}
