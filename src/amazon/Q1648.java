package amazon;

import java.util.Arrays;

// Sell Diminishing-Valued Colored Balls
public class Q1648 {

    //应该用二分法才是比较好的
    //https://leetcode-cn.com/problems/sell-diminishing-valued-colored-balls/solution/xiao-shou-jie-zhi-jian-shao-de-yan-se-qiu-by-zerot/
    //二分找到的是阙值，保证高于这个数的操作数少于orders
    //然后计算总利润，最后可能还剩余几个orders，直接根据阙值补上个数就行
    public int maxProfit(int[] inventory, int orders) {
        int mod = (int)(1e9 + 7);
        int l = 0,r = maxNum(inventory);
        while(l < r){
            int mid = l + (r - l) / 2;
            if(provideOrders(inventory, mid) <= orders){
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        long res = 0;
        for(int num : inventory){
            if(num >= l){
                orders -= (num - l + 1);
                long first = l, last = num, n = num - l + 1;
                res = (res + ((first + last) * n / 2) % mod) % mod;
            }
        }
        res = (res + (long)orders * (l - 1) % mod) % mod;
        return (int) res;
    }

    private long provideOrders(int[] inventory,int m){
        long orders = 0;
        for(int num : inventory){
            orders += Math.max(num - m + 1,0);
        }
        return orders;
    }

    private int maxNum(int[] inventory){
        int max = 0;
        for(int num : inventory){
            max = Math.max(max,num);
        }
        return max;
    }

    // https://zxi.mytechroad.com/blog/greedy/leetcode-1648-sell-diminishing-valued-colored-balls/
    // be careful the long type variable defined below:
    public int maxProfit1(int[] inv, int orders) {
        int mod = (int) (1e9 + 7);
        int n = inv.length, i = 0;
        Integer[] inventory = new Integer[n];
        for(int j = 0; j < n; j++) {
            inventory[j] = inv[j];
        }
        Arrays.sort(inventory, (a, b) -> b - a);
        long ans = 0;
        long cur = inventory[0];

        while(orders > 0) {
            while(i < n && inventory[i] == cur) {
                i++;
            }
            long next = i == n ? 0 : inventory[i];
            long cur_count = Math.min((long)orders, i * (cur - next));
            long t = cur - next;
            int r = 0;
            if(orders < i * (cur - next)) {
                t = orders / i;
                r = orders % i;
            }
            next = cur - t; // cur - (cur - next) => next !!!
            ans = (ans + i * (cur + next + 1) * (cur - next) / 2 + next * r) % mod;

            orders -= cur_count;
            cur = next;
        }


        return (int) ans;
    }
}
