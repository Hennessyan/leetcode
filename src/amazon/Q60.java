package amazon;

import java.util.LinkedList;

// Permutation Sequence
public class Q60 {
    // O(n^2) O(n)
    public String getPermutation(int n, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        int total = 1;
        for(int i = 1; i <= n; i++) {
            list.add(i);
            if(i == n) {
                break;
            }
            total *= i;
        }
        k--;
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
