package google;
// Reverse Vowels of a String
public class Q345 {

    public String reverseVowels(String s) {
        if(s == null || s.length() < 2) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        String vowel = "aeiouAEIOU";
        int n = s.length(), j = n - 1;
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(vowel.indexOf(c) != -1) {
                while(vowel.indexOf(s.charAt(j)) == -1) {
                    j--;
                }
                sb.append(s.charAt(j--));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
