package amazon;
// Palindrome Partitioning II
public class Q132 {

    //O(n^2) O(n^2)
    public int minCut(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int[] cuts = new int[len];
        for(int i = 0; i < len; i++){
            cuts[i] = i;		//最坏情况： aab, i=2代表切两次得到a a b
            for(int j = 0; j <= i; j++){
                if(s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j+1][i-1])){
                    if(j == 0){
                        cuts[i] = 0;	//没有必要给dp[j][i]赋值true.
                        break;			//有的话更快结束,没有也行.
                    }else{
                        dp[j][i] = true;
                        cuts[i] = Math.min(cuts[i], cuts[j - 1] + 1);
                    }
                }
            }
        }
        return cuts[len - 1];
    }
}
