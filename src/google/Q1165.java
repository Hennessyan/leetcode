package google;
// Single-Row Keyboard
public class Q1165 {
    // O(n) O(1)
    public int calculateTime(String keyboard, String word) {
        int[] keyIndices = new int[26];

        for (int i = 0; i < keyboard.length(); i++)
            keyIndices[keyboard.charAt(i) - 'a'] = i;

        int prev = 0;
        int result = 0;

        for (char c : word.toCharArray()) {
            result += Math.abs(prev - keyIndices[c - 'a']);
            prev = keyIndices[c - 'a'];
        }
        return result;
    }
}
