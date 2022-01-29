package linkedin;
// Shortest Word Distance III
public class Q245 {

    public int shortestWordDistance(String[] words, String word1, String word2) {
        int i1 = -1, i2 = -1, res = Integer.MAX_VALUE;
        boolean flag = false;
        for(int i = 0; i < words.length; i++) {
            if(word1.equals(words[i])) {
                if(word1.equals(word2)) {
                    if(flag) {
                        i1 = i;
                    } else {
                        i2 = i;
                    }
                    flag = !flag;
                } else {
                    i1 = i;
                }
            } else if(word2.equals(words[i])) {
                i2 = i;
            }
            if(i1 != -1 && i2 != -1) {
                res = Math.min(res, Math.abs(i1 - i2));
            }
        }
        return res;
    }
}
