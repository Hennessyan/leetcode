package challenge.dec;
// Shortest Word Distance
public class Q243 {
    // O(n) O(1)
    // a b c a b c b a
    // 顺序遍历记录INDEX的时候已经保证了比较的两个是更近的!
    public int shortestDistance(String[] words, String word1, String word2) {
        int w1 = -1, w2 = -1;
        int min = words.length;
        for(int i = 0; i < words.length; i++) {
            if(word1.equals(words[i])) {
                w1 = i;
            }
            if(word2.equals(words[i])) {
                w2 = i;
            }
            if(w1 != -1 && w2 != -1) {
                min = Math.min(min, Math.abs(w1 - w2));
            }
        }
        return min;
    }
}
