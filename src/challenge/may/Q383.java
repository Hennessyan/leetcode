package challenge.may;
// Ransom Note
public class Q383 {

    public static void main(String[] args) {
        Q383 q = new Q383();
        System.out.println(q.canConstruct("aa", "aab"));
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if(ransomNote == null || magazine == null || magazine.length() < ransomNote.length()) {
            return false;
        }
        int[] count = new int[26];
        for(char c : magazine.toCharArray()) {
            count[c - 'a']++;
        }
        for(char c: ransomNote.toCharArray()) {
            count[c - 'a']--;
        }
        for(int i : count) {
            if(i < 0) {
                return false;
            }
        }
        return true;
    }
}

