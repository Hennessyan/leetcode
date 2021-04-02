package google;

import java.util.HashSet;
import java.util.Set;

// Robot Room Cleaner
public class Q489 {
        // O(n - m) O(n - m) m - obstacle
//    public void cleanRoom(Robot robot) {
//        helper(new HashSet<>(), 0, 0, 0, robot);
//    }
//    private void helper(Set<String> visited, int i, int j, int d, Robot robot) {
//        if(!visited.add(i + "-" + j)) {
//            return;
//        }
//        robot.clean();
//
//        for(int k = 0; k < 4; k++) {
//            if(robot.move()) {
//                int x = i;
//                int y = j;
//                switch(d) {
//                    case 0:     // down
//                        x++;
//                        break;
//                    case 1:     // left
//                        y--;
//                        break;
//                    case 2:     // up
//                        x--;
//                        break;
//                    case 3:     // right
//                        y++;
//                        break;
//                }
//                helper(visited, x, y, d, robot);
//                robot.turnRight();    // L37-L41 : robot go back
//                robot.turnRight();
//                robot.move();
//                robot.turnRight();
//                robot.turnRight();
//            }
//            robot.turnRight();
//            d = (d + 1) % 4;
//        }
//
//    }

}
