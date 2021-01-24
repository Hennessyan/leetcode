package challenge.jan21;
// Create Sorted Array through Instructions
public class Q1649 {

    public static void main(String[] args) {
        Q1649 q = new Q1649();
        int[] arr = {1,5,6,2};
        System.out.println(q.createSortedArray(arr));   // 1
    }
    // Segment Tree: O(nlgm) O(m) -> m = 1e5
    private int[] arr;
    private int m;
    private int mode = (int) 1e9 + 7;
    public int createSortedArray(int[] instructions) {
        m = (int) 1e5 + 1;
        arr = new int[2 * m];

        long cost = 0;

        for(int i : instructions) {
            cost += Math.min(sum(0, i - 1), sum(i + 1, m - 1));
            update(i);
        }
        return (int) (cost % mode);
    }
    private long sum(int l, int r) {
        l += m;
        r += m;
        long sum = 0;
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
    private void update(int i) {
        i += m;
        arr[i] += 1;
        i /= 2;

        while(i > 1) {
            arr[i] += 1;
            i /= 2;
        }
    }
    // Binary Indexed Tree : O(nlgm) O(m)
    /*
    int[] bit;
    int m;
    public int createSortedArray1(int[] instructions) {
        m = (int) 1e5;
        int mode = (int) (1e9) + 7;
        bit = new int[m + 1];
        long cost = 0;

        for(int i = 0; i < instructions.length; i++) {
            long left = sum(instructions[i] - 1);
            long right = i - sum(instructions[i]);  // i -> total number before next update,
            cost += Math.min(left, right);          // sum(instructions[i]) -> total number lesser or equal to instructions[i] before update
            update(instructions[i]);                // i - sum(instructions[i]) -> total number greater than instructions[i]
        }
        return (int) (cost % mode);
    }
    private int sum(int i) {
        int sum = 0;
        while(i > 0) {
            sum += bit[i];
            i -= i & -i;
        }
        return sum;
    }
    private void update(int i) {
        while(i <= m) {
            bit[i] += 1;
            i += i & -i;
        }
    }
    */

    // merge sort : 当插入一个新的元素时,需要移动到右边的元素数目正好是比它大的个数. 我们需要采用stable sort来避免相同的两个元素交换位置.
    // The case of smaller ones is similar. We can record the number of elements that stay on left after sorting.
    // To avoid counting those with the same value, we need a totally unstable sort here (i.e., adjacent same values become reversed order).
    // 思路有意思,太繁琐.
    // O(nlgn) O(n)
    int[] smaller;
    int[] larger;
    int[][] temp; // record some temporal information

    public int createSortedArray2(int[] instructions) {
        int n = instructions.length;
        smaller = new int[n];
        larger = new int[n];
        temp = new int[n][];
        long cost = 0;
        long MOD = 1000000007;

        int[][] arrSmaller = new int[n][];
        int[][] arrLarger = new int[n][];
        for (int i = 0; i < n; i++) {
            arrSmaller[i] = new int[] { instructions[i], i };
            arrLarger[i] = new int[] { instructions[i], i };
        }

        sortSmaller(arrSmaller, 0, n - 1);
        sortLarger(arrLarger, 0, n - 1);

        for (int i = 0; i < n; i++) {
            cost += Math.min(smaller[i], larger[i]);
        }
        return (int) (cost % MOD);
    }

    private void sortSmaller(int[][] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        sortSmaller(arr, left, mid);
        sortSmaller(arr, mid + 1, right);
        mergeSmaller(arr, left, right, mid);
    }

    private void mergeSmaller(int[][] arr, int left, int right, int mid) {
        // merge [left, mid] and [mid+1, right]
        int i = left;
        int j = mid + 1;
        int k = left;
        // use temp[left...right] to temporarily store sorted array
        while (i <= mid && j <= right) {
            if (arr[i][0] < arr[j][0]) {
                temp[k++] = arr[i];
                i++;
            } else {
                temp[k++] = arr[j];
                smaller[arr[j][1]] += i - left;
                j++;
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i];
            i++;
        }
        while (j <= right) {
            temp[k++] = arr[j];
            smaller[arr[j][1]] += i - left;
            j++;
        }
        // restore from temp
        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
    }

    private void sortLarger(int[][] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        sortLarger(arr, left, mid);
        sortLarger(arr, mid + 1, right);
        mergeLarger(arr, left, right, mid);
    }

    private void mergeLarger(int[][] arr, int left, int right, int mid) {
        // merge [left, mid] and [mid+1, right]
        int i = left;
        int j = mid + 1;
        int k = left;
        // use temp[left...right] to temporarily store sorted array
        while (i <= mid && j <= right) {
            if (arr[i][0] <= arr[j][0]) {
                temp[k++] = arr[i];
                i++;
            } else {
                temp[k++] = arr[j];
                larger[arr[j][1]] += mid - i + 1;
                j++;
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i];
            i++;
        }
        while (j <= right) {
            temp[k++] = arr[j];
            larger[arr[j][1]] += mid - i + 1;
            j++;
        }
        // restore from temp
        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
    }
}
