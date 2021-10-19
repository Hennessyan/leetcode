package amazon;
// Robot Return to Origin
public class Q657 {
    // O(n) O(1)
    public boolean judgeCircle(String moves) {
        if(moves == null || moves.length() == 0) return true;
        int x = 0, y = 0;
        for(char c : moves.toCharArray()) {
            if(c == 'U') x--;
            if(c == 'D') x++;
            if(c == 'L') y--;
            if(c == 'R') y++;
        }
        return x == 0 && y == 0;
    }
}
