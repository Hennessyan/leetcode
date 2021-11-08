package amazon;

import java.util.Arrays;
import java.util.TreeMap;

// Reverse Pairs
public class Q493 {

    public int reversePairs0(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int n = nums.length;
        BIT bit = new BIT(n);
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            // find the count of # less than val (does not use + 1 => less val)
            ans += bit.query(search(sorted, 0, n, nums[i] / 2.0));
            bit.update(search(sorted, 0, n - 1, nums[i]) + 1, 1);
        }
        return ans;
    }

    // find least index that arr[i] >= val
    private int search(int[] arr, int l, int r, double val) {
        while (l < r) {
            int m = l + (r - l) / 2;
            if (arr[m] < val) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    class BIT {
        int[] arr;

        public BIT(int n) {
            arr = new int[n + 1];
        }

        public void update(int i, int diff) {
            while (i < arr.length) {
                arr[i] += diff;
                i += lsb(i);
            }
        }

        public int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += arr[i];
                i -= lsb(i);
            }
            return sum;
        }

        private int lsb(int i) {
            return i & -i;
        }
    }

    public int reversePairs1(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int n = nums.length;
        ST st = new ST(n);
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            // find the count of # less than val (does not use + 1 => less val)
            ans += st.query(0, search(sorted, 0, n, nums[i] / 2.0) - 1);
            st.update(search(sorted, 0, n - 1, nums[i]), 1);
        }
        return ans;
    }
    class ST {
        int[] arr;
        int n;
        public ST(int n) {
            arr = new int[n * 2];
            this.n = n;
        }
        public void update(int i, int diff) {
            i += n;
            while(i > 0) {
                arr[i] += diff;
                i /= 2;
            }
        }
        public int query(int l, int r) {
            l += n;
            r += n;
            int sum = 0;
            while(l <= r) {
                if((l & 1) == 1) {
                    sum += arr[l++];
                }
                if((r & 1) == 0) {
                    sum += arr[r--];
                }
                l /= 2;
                r /= 2;
            }
            return sum;

        }
    }

    int count;
    public int reversePairs(int[] nums) {
        count = 0;
        mergesort(nums, 0, nums.length - 1);
        return count;
    }
    private int[] mergesort(int[] nums, int l, int r) {
        if(l == r) {
            return new int[]{nums[l]};
        }
        int m = l + (r - l) / 2;
        int[] left = mergesort(nums, l, m);
        int[] right = mergesort(nums, m + 1, r);
        return merge(left, right);
    }
    private int[] merge(int[] a, int[] b) {
        int i = 0, j = 0;
        while(i < a.length && j < b.length) {
            if(a[i] > (long) 2 * b[j]) {
                count += a.length - i;
                j++;
            } else {
                i++;
            }
        }
        i = 0;
        j = 0;
        int k = 0;
        int[] tmp = new int[a.length + b.length];
        while(i < a.length && j < b.length) {
            if(a[i] <= b[j]) {
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = b[j++];
            }
        }
        while(i < a.length) {
            tmp[k++] = a[i++];
        }
        while(j < b.length) {
            tmp[k++] = b[j++];
        }
        return tmp;
    }
}
