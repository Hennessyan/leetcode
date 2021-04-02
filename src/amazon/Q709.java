package amazon;
// To Lower Case
public class Q709 {
    // O(n) O(n)
    public String toLowerCase(String str) {
        StringBuilder sb = new StringBuilder();
        for(char c : str.toCharArray()) {
            if(c >= 'A' && c <= 'Z') {
                c -= 'A' - 'a';
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
