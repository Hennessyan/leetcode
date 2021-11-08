package amazon;

import java.util.HashMap;
import java.util.Map;

// Binary Subarrays With Sum
public class Q930 {

    public static void main(String[] args) {
        Q930 q = new Q930();
        int[] A = {1,0,1,0,1};
        int S = 2;
        System.out.println(q.numSubarraysWithSum(A, S));    //4
    }

    //Prefix Sum: O(n) O(n)
    public int numSubarraysWithSum(int[] A, int S) {
        int N = A.length;
        int[] P = new int[N + 1];
        for(int i = 0; i < N; i++) {
            P[i + 1] = P[i] + A[i];
        }
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for(int p : P) {
            count += map.getOrDefault(p - S, 0);
            map.put(p, map.getOrDefault(p, 0) + 1);
        }
        return count;
    }

    public int numSubarraysWithSum1(int[] nums, int goal) {
        int sum = 0, count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for(int num : nums) {
            sum += num;
            count += map.getOrDefault(sum - goal, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    //https://leetcode.com/problems/binary-subarrays-with-sum/solution/
    //method1: O(n) O(n)
    public int numSubarraysWithSum2(int[] A, int S) {
        int sum = 0, total = 0;
        for(int a : A) {
            sum += a;
        }
        int[] index = new int[sum + 2];
        int t = 0;
        index[t++] = -1;
        for(int i = 0; i < A.length; i++) {
            if(A[i] == 1) {
                index[t++] = i;
            }
        }
        index[t] = A.length;
        if(S == 0) {
            for(int i = 0; i < t; i++) {
                int w =  index[i + 1] - index[i] - 1;
                total += w * (w + 1) / 2;
            }
            return total;
        }
        for(int i = 1; i <= t - S; i++) {
            int j = i + S;
            int left = index[i] - index[i - 1] ;
            int right = index[j] - index[j - 1];
            total += left * right;
        }
        return total;
    }

    public int numSubarraysWithSum3(int[] A, int S) {
        int sumL = 0, sumH = 0;
        int iL = 0, iH = 0;
        int ans = 0;
        for(int j = 0; j < A.length; j++) {
            sumL += A[j];
            while(iL < j && sumL > S) {
                sumL -= A[iL++];
            }

            sumH += A[j];
            while(iH < j && (sumH > S || sumH == S && A[iH] == 0)) {
                sumH -= A[iH++];
            }

            if(sumL == S) {
                ans += iH - iL + 1;
            }
        }
        return ans;
    }
}
