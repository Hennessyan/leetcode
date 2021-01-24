package google;
// Rotated Digits
public class Q788 {

    public static void main(String[] args) {
        Q788 q = new Q788();
        System.out.println(q.rotatedDigits(10));    // 4
    }
    // 2 5 6 9
    // 0 1 8
    // 3 4 7
    // O(nlgn) O(1) - log is 10 based.
    public int rotatedDigits(int N) {
        if(N < 2) {
            return 0;
        }
        int count = 0;
        for(int i = 1; i <= N; i++) {
            if(good(i)) {
                count++;
            }
        }
        return count;
    }
    private boolean good(int num) {
        boolean isSame = true;
        int val = 0;
        while(num != 0) {
            val = num % 10;
            if(val == 3 || val == 4 || val == 7) {
                return false;
            }
            if(val == 2 || val == 5 || val == 6 || val == 9) {
                isSame = false;
            }
            num /= 10;
        }
        return !isSame;
    }
    // method2 : DP O(lgn) O(lgn)
    /*
        equal - 用来表示当前INDEX的值是否等于N在此位置的值
        contain - 代表是否必须包含 2 5 6 9
        e.g., 23
        对于3 -> 00 01 10 11
                 4  7   1 3
        对于2 -> 如果equal是FALSE的情况,就是说我们在遍历十位是0跟1的时候,那么当然是0-9都能取了
                         是TRUE的情况,就是已经从20开始遍历了,那只能看20～23中有多少个符合条件的.
                 与此同时,我们的遍历还在为下一轮计算做准备,所以才有了c == A[k] ? equal的情况：
                            因为是FALSE (0)的情况时,当然是结果组合要更多了,所以EQUAL需要为0而不是1.
                            (因此不能写成c == A[k] ? 1 : 0)
                 如果CONTAIN是0,那么只有为2,5,6,9的情况才能加1;
                            是1,那么只要不是3，4，7就都可以.
     */
    public int rotatedDigits1(int N) {
        char[] A = String.valueOf(N).toCharArray();
        int K = A.length;

        int[][][] memo = new int[K + 1][2][2];
        memo[K][0][1] = memo[K][1][1] = 1;

        for(int k = K - 1; k >= 0; k--) {
            for(int equal = 0; equal < 2; equal++) {
                for(int contain = 0; contain < 2; contain++) {
                    int ans = 0;
                    for(char c = '0'; c <= (equal == 1 ? A[k] : '9'); c++) {
                        if(c == '3' || c == '4' || c == '7') {
                            continue;
                        }
                        boolean flag = c == '2' || c == '5' || c == '6' || c == '9';
                        ans += memo[k+1][c == A[k] ? equal : 0][flag ? 1 : contain];
                    }
                    memo[k][equal][contain] = ans;
                }
            }
        }
        return memo[0][1][0];
    }
}
