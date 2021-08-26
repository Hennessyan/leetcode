package amazon;
// Verifying an Alien Dictionary
public class Q953 {
    // O(m*n) O(1) -> m - len of words, n - len of longest word
    // as we know n <= 20, then we may say O(m)
    public boolean isAlienSorted(String[] words, String order) {
        int[] orderMap = new int[26];
        for(int i = 0; i < order.length(); i++) {
            orderMap[order.charAt(i) - 'a'] = i;
        }
        int len = words.length;
        for(int i = 0; i < len - 1; i++) {
            for(int j = 0; j < words[i].length(); j++) {
                // "apple" "app"
                if(j >= words[i+1].length()) return false;
                char cur = words[i].charAt(j);
                char next = words[i+1].charAt(j);
                if(cur != next) {
                    if(orderMap[cur - 'a'] > orderMap[next - 'a']) {
                        return false;
                    } else {
                        break;
                    }
                }
            }
        }
        return true;
    }
}
