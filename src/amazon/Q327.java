package amazon;

import java.util.*;

import static java.util.Collections.*;

// Count of Range Sum
public class Q327 {
    // method1 : merge sort - O(nlgn) O(n)
    public int countRangeSum1(int[] nums, int lower, int upper) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        long[] sum = new long[n + 1];
        for(int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        return mergeSort(sum, 0, n, lower, upper);
    }
    private int mergeSort(long[] sum, int low, int high, int lower, int upper) {
        if(low >= high) {
            return 0;
        }
        int mid = (high - low + 1) / 2 + low;  // for even length arr case, we need to find correct index by using (r - l + 1) / 2 + l.
        int count = mergeSort(sum, low, mid - 1, lower, upper)
                + mergeSort(sum, mid, high, lower, upper);

        for(int i = low; i < mid; i++) {
            int startIndex = mid;
            while(startIndex <= high && sum[startIndex] - sum[i] < lower) {
                startIndex++;
            }
            int endIndex = startIndex;
            while(endIndex <= high && sum[endIndex] - sum[i] <= upper) {
                endIndex++;
            }
            count += endIndex - startIndex;
        }

        merge(sum, low, mid, high);
        return count;
    }

    private void merge(long[] sum, int low, int mid, int high) {
        int i1 = low, i2 = mid - 1, j1 = mid, index = 0;
        long[] tmp = new long[high - low + 1];
        while(i1 <= i2 && j1 <= high) {
            if(sum[i1] < sum[j1]) {
                tmp[index++] = sum[i1++];
            } else {
                tmp[index++] = sum[j1++];
            }
        }
        while(i1 <= i2) {
            tmp[index++] = sum[i1++];
        }
        while(j1 <= high) {
            tmp[index++] = sum[j1++];
        }

        System.arraycopy(tmp, 0, sum, low, high - low + 1);
        // index = 0;
        // while(low <= high) {
        //     sum[low++] = tmp[index++];
        // }
    }
    // method2 : BIT - O(nlgn) O(n)
    // 通过BIT计算在满足条件 (b - upper <= a <= b - lower) 范围内的数量.
    // BIT又是通过以记录所有情况 (L83-L107) 的INDEX的个数来进行统计总数的.
    int[] bit;
    int N;
    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        long[] sum = new long[n + 1];
        for(int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

        List<Long> list = new ArrayList<>();
        list.add(0l);
        // lower <= b - a <= upper
        // a >= b - upper
        // a <= b - lower`
        for(int i = 1; i <= n; i++) {
            list.add(sum[i] - lower);
            list.add(sum[i]);
            list.add(sum[i] - upper);
        }

        sort(list);
        int index = 0;
        Map<Long, Integer> map = new HashMap<>();
        for(int i = 0; i < list.size(); i++) {
            Long val = list.get(i);
            if(!map.containsKey(val)) {
                map.put(val, index++);
            }
        }

        N = map.size();
        bit = new int[N + 1];

        update(map.get(0l), 1);
        int count = 0;
        for(int i = 1; i <= n; i++) {
            count += query(map.get(sum[i] - lower)) - query(map.get(sum[i] - upper) - 1);
            update(map.get(sum[i]), 1);
        }
        return count;
    }

    private int query(int i) {
        int sum = 0;
        i += 1;
        while(i > 0) {
            sum += bit[i];
            i -= i & -i;
        }
        return sum;
    }
    private void update(int i, int val) {
        i += 1;
        while(i <= N) {
            bit[i] += val;
            i += i & -i;
        }
    }
}
