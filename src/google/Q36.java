package google;

import java.util.HashSet;
import java.util.Set;
// Valid Sudoku
public class Q36 {

    //https://discuss.leetcode.com/topic/27436/short-simple-java-using-strings
    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] != '.'){
                    String s = "(" + board[i][j] + ")";
                    if(!seen.add(s + i) || !seen.add(j + s) || !seen.add(i/3 + s + j/3)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku1(char[][] board) {
        int [] vset = new int [9];
        int [] hset = new int [9];
        int [] bckt = new int [9];
        int idx = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    idx = 1 << (board[i][j] - '0') ;
                    if ((hset[i] & idx) > 0 ||							//(hset[i] & idx) > 0
                            (vset[j] & idx) > 0 ||							//并非(hset[i] & idx > 0)
                            (bckt[(i / 3) * 3 + j / 3] & idx) > 0) return false;
                    hset[i] |= idx;
                    vset[j] |= idx;
                    bckt[(i / 3) * 3 + j / 3] |= idx;
                }
            }
        }
        return true;
    }
}
