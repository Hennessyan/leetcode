package tesla;
// Maximum Number of Balloons
public class Q1189 {

    public static void main(String[] args) {
        Q1189 q = new Q1189();
        System.out.println(q.maxNumberOfBalloons("nlaebolko"));    //1
        System.out.println(q.maxNumberOfBalloons("loonbalxballpoon"));  //2
    }
    // only lower-case English letters.
    public int maxNumberOfBalloons(String text) {
        if(text == null || text.length() < 6) {
            return 0;
        }
        int[] count = new int[26];
        for(char c: text.toCharArray()) {
            count[c - 'a']++;
        }
        int min = count[0];
        min = Math.min(min, count[1]);
        min = Math.min(min, count[11] / 2);
        min = Math.min(min, count[14] / 2);
        min = Math.min(min, count[13]);
        return min;
    }
}
