package challenge.june;

import java.util.LinkedList;
import java.util.List;

// Permutation Sequence
public class Q60 {

    public static void main(String[] args) {
        Q60 q = new Q60();
        System.out.println(q.getPermutation(3,3));  // 213
        System.out.println(q.getPermutation(4,9));  // 2314
    }
    // O(n^2) O(n)
    public String getPermutation(int n, int k) {
        List<Integer> list = new LinkedList<>();
        int total = 1;
        for(int i = 1; i <= n; i++) {
            list.add(i);
            if(i == n) {
                break;
            }
            total *= i;
        }
        --k;
        StringBuilder sb = new StringBuilder();
        while(true) {
            sb.append(list.remove(k / total));
            if(list.isEmpty()) {
                break;
            }
            k %= total;
            total /= list.size();
        }
        return sb.toString();
    }
}
