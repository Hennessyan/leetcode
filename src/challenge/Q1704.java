package challenge;

import java.util.HashSet;
import java.util.Set;

// Determine if String Halves Are Alike
public class Q1704 {

    // O(n)O(1)
    public boolean halvesAreAlike(String s) {
        int len = s.length();
        Set<Character> set = new HashSet<>();
        String vowels = "aeiouAEIOU";
        for(char c : vowels.toCharArray()) {
            set.add(c);
        }
        int c1 = 0, c2 = 0;
        for(int i = 0; i < len / 2; i++) {
            if(set.contains(s.charAt(i))) {
                c1++;
            }
            if(set.contains(s.charAt(i + len / 2))) {
                c2++;
            }
        }
        return c1 == c2;
    }

    public boolean halvesAreAlike1(String s) {
        int len = s.length();
        String vowels = "aeiouAEIOU";
        int aVowelCount = 0, bVowelCount = 0;
        for(int i = 0; i < len / 2; i++) {
            if(vowels.indexOf(s.charAt(i)) != -1) {
                aVowelCount++;
            }
            if(vowels.indexOf(s.charAt(i + len / 2)) != -1) {
                bVowelCount++;
            }
        }
        return aVowelCount == bVowelCount;
    }
}
