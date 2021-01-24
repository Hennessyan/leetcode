package google;

import java.util.ArrayList;
import java.util.List;

// Palindrome Partitioning
public class Q131 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Q131 q = new Q131();

        List<List<String>> res = q.partition("aab");
        for(List<String> list : res){
            for(String i : list){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
    // TC : O(n*2^n) =>
    // 2^n different combination
    // n for validation and build substring

    // SC - method1 O(n) method2 O(n^2)

    /*method1 66.8%*/
//	public List<List<String>> partition(String s) {
//		List<List<String>> results = new ArrayList<>();
//		if(s == null){
//			return results;
//		}
//		if(s.length() == 0){
//			results.add(new ArrayList<String>());
//			return results;
//		}
//		backtrack(results, new ArrayList<>(), s, 0);
//		return results;
//    }
//	private void backtrack(List<List<String>> results, List<String> tempList, String s, int start){
//		if(start == s.length()){
//			results.add(new ArrayList<>(tempList));
//		} else {
//			for(int i = start; i < s.length(); i++){
//				if(palindrome(s, start, i)){
//					tempList.add(s.substring(start, i + 1));
//					backtrack(results, tempList, s, i + 1);
//					tempList.remove(tempList.size() - 1);
//				}
//			}
//		}
//	}
//	private boolean palindrome(String s, int l, int r){
//		while(l < r){
//			if(s.charAt(l++) != s.charAt(r--)){
//				return false;
//			}
//		}
//		return true;
//	}
    /*method2 83%*/
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j <= i; j++) {   //注意这里执行的顺序
                if(s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j+1][i-1])) {
                    dp[j][i] = true;
                }
            }
        }
        helper(res, new ArrayList<>(), dp, s, 0);
        return res;
    }

    private void helper(List<List<String>> res, List<String> path, boolean[][] dp, String s, int pos) {
        if(pos == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i = pos; i < s.length(); i++) {
            if(dp[pos][i]) {
                path.add(s.substring(pos,i+1));
                helper(res, path, dp, s, i+1);
                path.remove(path.size()-1);
            }
        }
    }

    public List<List<String>> partition3(String s) {
        List<List<String>> res = new ArrayList<>();
        if(s == null || s.length() == 0) {
            return res;
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];

        helper(res, new ArrayList<>(), dp, 0, s);
        return res;
    }
    private void helper(List<List<String>> res, List<String> list, boolean[][] dp,
                        int p, String s) {
        if(p == s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = p; i < s.length(); i++) {
            if(s.charAt(p) == s.charAt(i) && (i - p <= 2 || dp[p+1][i-1])) {
                dp[p][i] = true;
                list.add(s.substring(p, i + 1));
                helper(res, list, dp, i + 1, s);
                list.remove(list.size() - 1);
            }
        }
    }
}
