package linkedin;
// Shortest Word Distance
public class Q243 {
    // O(m*n) O(1) - m length of word, n - length of arr.
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int n = wordsDict.length, len = n;
        int p1 = -1, p2 = -1;
        for(int i = 0; i < n; i++) {
            if(wordsDict[i].equals(word1)) {
                p1 = i;
            } else if(wordsDict[i].equals(word2)) {
                p2 = i;
            }
            if(p1 != -1 && p2 != -1) {
                len = Math.min(len, Math.abs(p1 - p2));
            }
        }
        return len;
    }
}
