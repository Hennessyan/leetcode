package amazon;
// To Lower Case
public class Q709 {
    // O(n) O(n)
    public String toLowerCase(String str) {
        StringBuilder sb = new StringBuilder();
        for(char c : str.toCharArray()) {
            if(c >= 'A' && c <= 'Z') {
                c -= 'A' - 'a';
                // c |= 32;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public boolean isUpper(char x) {
        return 'A' <= x && x <= 'Z';
    }

    public char toLower(char x) {
        return (char) ((int)x | 32);
    }

    public String toLowerCase1(String str) {
        StringBuilder sb = new StringBuilder();
        for (char x : str.toCharArray()) {
            sb.append(isUpper(x) ? toLower(x) : x);
        }
        return sb.toString();
    }
}