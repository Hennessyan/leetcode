package challenge.july21;
// Push Dominoes
public class Q838 {
    // O(n) O(n)
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        int[] index = new int[n + 2];
        char[] dirs = new char[n + 2];
        index[0] = -1;
        dirs[0] = 'L';
        int i = 1;

        char[] chs = dominoes.toCharArray();
        for(int j = 0; j < n; j++) {
            if(chs[j] != '.') {
                index[i] = j;
                dirs[i++] = chs[j];
            }
        }

        index[i] = n;
        dirs[i] = 'R';

        for(int k = 0; k < i; k++) {
            int l = index[k], r = index[k + 1];
            char ld = dirs[k], rd = dirs[k + 1];
            if(ld == rd) {
                for(int x = l + 1; x < r; x++) {
                    chs[x] = ld;
                }
            } else if(ld > rd) {    // RL
                for(int x = l + 1; x < r; x++) {
                    chs[x] = x - l < r - x ? 'R' : (x - l > r - x ? 'L' : '.');
                }
            }
        }

        return String.valueOf(chs);
    }
    // force method: O(n) O(n) - method1 is faster.
    public String pushDominoes1(String dominoes) {
        int n = dominoes.length();
        int[] f = new int[n];
        int force = 0;
        for(int i = 0; i < n; i++) {
            char c = dominoes.charAt(i);
            if(c == 'R') {
                force = n;
            } else if(c == 'L') {
                force = 0;
            } else {
                force = Math.max(force - 1, 0);
            }
            f[i] += force;
        }
        force = 0;
        for(int i = n - 1; i >= 0; i--) {
            char c = dominoes.charAt(i);
            if(c == 'R') {
                force = 0;
            } else if(c == 'L') {
                force = n;
            } else {
                force = Math.max(force - 1, 0);
            }
            f[i] -= force;
        }
        StringBuilder sb = new StringBuilder();
        for(int i : f) {
            sb.append(i < 0 ? 'L' : i > 0 ? 'R' : '.');
        }
        return sb.toString();
    }
}
