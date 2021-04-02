package microsoft;

import java.util.ArrayList;
import java.util.List;

// N-Queens
public class Q51 {

    //Cracking the Coding interview Q8.12
    //TC: O(n^n)	不管valid()需要花费多久,只有true的情况才能继续做下去,一定是n行每次都要对n列进行操作,因此是n^n
    // https://leetcode.com/problems/n-queens/solution/ O(n!) ??
    //SC: O(n)		backtrack的深度为n
    public List<List<String>> solveNQueens(int n) {
        if(n <= 0){
            return new ArrayList<>();
        }
        List<List<String>> res = new ArrayList<>();
        backtrack(0, n, new int[n], res);
        return res;
    }
    private void backtrack(int row, int n, int[] columns, List<List<String>> res){
        if(row == n){
            convertInsert(columns, res);
            return;
        }
        for(int col = 0; col < n; col++){
            if(valid(col, row, columns)){
                columns[row] = col;
                backtrack(row + 1, n, columns, res);
            }
        }
    }
    private boolean valid(int col, int row, int[] columns){
        //no need check row
        for(int r = 0; r < row; r++){	//我们不需要考虑>=row的行是什么情况,因为它们到目前为止还没有被使用
            int c = columns[r];
            //check column
            if(col == c){
                return false;
            }
            //check diagonals
            int rdiff = row - r;		//row - r > 0 所以不需要abs
            int cdiff = Math.abs(c - col);
            if(rdiff == cdiff){
                return false;
            }
        }
        return true;
    }
    private void convertInsert(int[] columns, List<List<String>> res){
        List<String> list = new ArrayList<>();
        for(int i = 0; i < columns.length; i++){
            int p = columns[i];
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < columns.length; j++){
                char c = j == p ? 'Q' : '.';
                sb.append(c);
            }
            list.add(sb.toString());
        }
        res.add(list);
    }
}
