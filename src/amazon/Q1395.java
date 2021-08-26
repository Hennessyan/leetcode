package amazon;
// Count Number of Teams
// Q1423
public class Q1395 {
    // brute force: O(n^3) - very slow
    public int numTeams0(int[] rating) {
        if(rating == null || rating.length < 3) {
            return 0;
        }
        int num = 0, len = rating.length;
        for(int i = 0; i < len - 2; i++) {
            for(int j = i + 1; j < len - 1; j++) {
                for(int k = j + 1; k < len; k++) {
                    if(rating[j] > rating[i] && rating[k] > rating[j]) {
                        num++;
                    }
                    if(rating[i] > rating[j] && rating[j] > rating[k]) {
                        num++;
                    }
                }
            }
        }
        return num;
    }
    // O(n^2)
    public int numTeams1(int[] rating) {
        if(rating == null || rating.length < 3) {
            return 0;
        }
        int num = 0, n = rating.length;
        for(int i = 1; i < n - 1; i++) {
            int smaller = 0, bigger = 0;
            for(int j = i - 1; j >= 0; j--) {
                if(rating[j] < rating[i]) {
                    smaller++;
                }
            }
            for(int j = i + 1; j < n; j++) {
                if(rating[j] > rating[i]) {
                    bigger++;
                }
            }
            // i - smaller => # of ele in left side and bigger than rating[i]
            // n - i - 1 - bigger => # of ele in right side and smaller than rating[i]
            num += smaller * bigger + (i - smaller) * (n - i - 1 - bigger);
        }
        return num;
    }
    // O(nlgn) O(n)
    public int numTeams(int[] rating) {
        int n = rating.length, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int num : rating) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        // assume min = 3, max = 6, len = max - min + 1 = 4
        // bit start from 1, 0-index is no use, hence len + 1 => max - min + 2
        BIT st = new BIT(max - min + 2); //smaller bit
        BIT bt = new BIT(max - min + 2); //bigger bit

        int offset = 1 - min;   // 3 + 1 - 3 => 1
        // smaller on the left side
        int[] smaller = new int[n];
        // smaller on the right side
        int[] bigger = new int[n];

        for(int i = 0; i < n; i++) {
            smaller[i] = st.sum(rating[i] + offset - 1);    // value - 1
            st.update(rating[i] + offset, 1);
        }
        for(int i = n - 1; i >= 0; i--) {
            bigger[i] = bt.sum(rating[i] + offset - 1);
            bt.update(rating[i] + offset, 1);
        }

        int total = 0;
        for(int i = 1; i < n - 1; i++) {
            total += smaller[i] * (n - 1 - i - bigger[i]) + (i - smaller[i]) * bigger[i];
        }
        return total;
    }
    class BIT {
        int[] arr;
        int len;
        public BIT(int len) {
            this.len = len;
            this.arr = new int[len];
        }
        public void update(int i, int val) {
            while(i < len) {
                arr[i] += val;
                i += i & -i;
            }
        }
        public int sum(int i) {
            int sum = 0;
            while(i > 0) {
                sum += arr[i];
                i -= i & -i;
            }
            return sum;
        }
    }
}
