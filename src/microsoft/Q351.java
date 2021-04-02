package microsoft;
// Android Unlock Patterns
public class Q351 {

    private boolean[] used;
    public int numberOfPatterns0(int m, int n) {
        used = new boolean[9];
        int sum = 0;
        for(int i = m; i <= n; i++) {
            sum += help(-1, i);
        }
        return sum;
    }
    private int help(int last, int num) {
        if(num == 0) {
            return 1;
        }
        int sum = 0;
        for(int i = 0; i < 9; i++) {	//必须用0~8的方式才能用%3或/3
            if(isValid0(last, i)) {
                used[i] = true;
                sum += help(i, num - 1);
                used[i] = false;
            }
        }
        return sum;
    }
    private boolean isValid0(int last, int i) {
        if(used[i]) {
            return false;
        }
        if(last == -1) {
            return true;
        }
        //knight moves or adjacent cells (in a row or in a column)
        if((last + i) % 2 == 1) {
            return true;
        }
        int mid = (last + i) / 2;
        if(mid == 4) {	//这个必须先判断，否则下一个adjacent diagnoal判断会导致对角两个顶点总返回true(不论是否used[4])
            return used[mid];
        }
        if((last % 3 != i % 3) && (last /3 != i / 3)) {
            return true;
        }
        return used[mid];
    }
    // O(n!) -> Sum(P(9,i)) (i from m to n)
    // O(n) -> recursion
    int m, n;
    boolean[] visited;
    int count;
    public int numberOfPatterns(int m, int n) {
        this.m = m;
        this.n = n;
        this.count = 0;
        visited = new boolean[9];
        backtrack(-1, 0);
        return count;
    }
    private void backtrack(int last, int depth) {
        if(depth >= this.m) {
            count++;
        }
        if(depth == this.n) {
            return;
        }
        for(int i = 0; i < 9; i++) {
            if(isValid(last, i)) {
                visited[i] = true;
                backtrack(i, depth + 1);
                visited[i] = false;
            }
        }
    }
    private boolean isValid(int last, int i) {
        if(visited[i]) {
            return false;
        }
        if(last == -1) {
            return true;
        }
        // knight move or adjacent cells in same row / col
        if((last + i) % 2 == 1) {
            return true;
        }
        int mid = (last + i) / 2;
        if(mid == 4) {  //这个必须先判断，否则下一个adjacent diagonal判断会导致对角两个顶点总返回true(不论是否used[4])
            return visited[4];
        }
        // adjacent cell in diagonal, actually is cells in different row and col:
        // but we can consider visited[4] check the "longest" diagonal, and just adjacent diagonal left.
        if(last % 3 != i % 3 && last / 3 != i / 3) {
            return true;
        }
        return visited[mid];
    }

    //method2 : 更快
    // The algorithm above could be optimized if we consider the symmetry property of the problem.
    // We notice that the number of valid patterns with first digit 1, 3, 7, 9 are the same.
    // A similar observation is true for patterns which starts with digit 2, 4, 6, 8.
    // Hence we only need to calculate one among each group and multiply by 4.
    int[][] jump;
    public int numberOfPatterns1(int m, int n) {
        jump = new int[10][10];
        jump[1][3] = jump[3][1] = 2;
        jump[7][9] = jump[9][7] = 8;
        jump[1][7] = jump[7][1] = 4;
        jump[3][9] = jump[9][3] = 6;
        jump[1][9] = jump[9][1] = jump[2][8] = jump[8][2] =
        jump[3][7] = jump[7][3] = jump[4][6] = jump[6][4] = 5;
        visited = new boolean[10];
        this.m = m;
        this.n = n;
        int count = 4 * help(1, 1, 0);
        count += 4 * help(2, 1, 0);
        count += help(5, 1, 0);
        return count;
    }
    private int help(int num, int deep, int count) {
        if(deep >= m) {
            count++;
        }
        if(deep == n) {
            return count;
        }
        visited[num] = true;
        for(int i = 1; i <= 9; i++) {
            if(!visited[i] && (jump[num][i] == 0 || visited[jump[num][i]])) {
                count = help(i, deep + 1, count);
            }
        }
        visited[num] = false;
        return count;   // count is total number of result as we pass it into next loop and return after increasing.
    }
}
