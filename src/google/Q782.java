package google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Transform to Chessboard
public class Q782 {
    // O(n^2) O(n)
    public int movesToChessboard(int[][] board) {
        int n = board.length;
        Map<Integer, Integer> count = new HashMap<>();
        for(int[] row : board) {
            int value = 0;
            for(int val : row) {
                value = value * 2 + val;
            }
            count.put(value, count.getOrDefault(value, 0) + 1);
        }
        int k1 = analyze(count, n);
        if(k1 == -1) return k1;
        count = new HashMap<>();
        for(int c = 0; c < n; c++) {
            int value = 0;
            for(int r = 0; r < n; r++) {
                value = value * 2 + board[r][c];
            }
            count.put(value, count.getOrDefault(value, 0) + 1);
        }
        int k2 = analyze(count, n);
        return k2 >= 0 ? k1 + k2 : -1;
    }
    private int analyze(Map<Integer, Integer> count, int n) {
        // we can have and only have two types of combination.
        if(count.size() != 2) return -1;
        List<Integer> list = new ArrayList<>(count.keySet());
        int v1 = list.get(0), v2 = list.get(1);
        if((v1 ^ v2) != (1 << n)- 1) return -1;
        int c1 = count.get(v1), c2 = count.get(v2);
        if(!(c1 == n / 2 && c2 == (n + 1) / 2)
                && !(c1 == (n + 1) / 2 && c2 == n / 2)) return -1;

        int Nones = (1 << n) - 1;   // 1111 if n == 4
        int ones = Integer.bitCount(v1);
        int diff = Integer.MAX_VALUE;
        if(n % 2 == 0 || ones * 2 < n) {    // zero start
            /*
            System.out.println(Integer.toBinaryString(v1));
            System.out.println(Integer.toBinaryString(v1 ^ 0x2aaaaaaa));
            System.out.println(Integer.toBinaryString(v1 ^ 0x2aaaaaaa & Nones));
            110
            101010101010101010101010101100
            1100
            */

            diff = Math.min(diff, Integer.bitCount(v1 ^ 0x2aaaaaaa & Nones) / 2);   // & Nones => remove pre-ones
        }
        if(n % 2 == 0 || ones * 2 > n) {   // one start
            diff = Math.min(diff, Integer.bitCount(v1 ^ 0x55555555 & Nones) / 2);
        }
        return diff;
    }
}
