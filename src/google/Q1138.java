package google;
// Alphabet Board Path
public class Q1138 {
    // O(n) O(n)
    // at most 11 move per char, O(11n) => O(n0
    public String alphabetBoardPath(String target) {
        StringBuilder sb = new StringBuilder();
        char cur = 'a';
        for(char c : target.toCharArray()) {
            moveToNext(cur, c, sb);
            cur = c;
        }
        return sb.toString();
    }
    private void moveToNext(char from, char to, StringBuilder sb) {
        int xOffset = (to - 'a') / 5 - (from - 'a') / 5;
        int yOffset = (to - 'a') % 5 - (from - 'a') % 5;
        // in order to avoid the corner case of position 'z', we check up before right, left before down !!!
        while(xOffset < 0) {
            sb.append('U');
            xOffset++;
        }
        while(yOffset < 0) {
            sb.append('L');
            yOffset++;
        }
        while(xOffset > 0) {
            sb.append('D');
            xOffset--;
        }
        while(yOffset > 0) {
            sb.append('R');
            yOffset--;
        }
        sb.append('!');
    }
}
