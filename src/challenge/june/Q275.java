package challenge.june;
// H-Index II
public class Q275 {

    public int hIndex1(int[] citations) {
        int i = 0, n = citations.length;
        while(i < n && citations[n - 1 - i] > i) {
            i++;
        }
        return i;
    }

    public int hIndex2(int[] citations) {
        int i = 0, n = citations.length;
        while(i < n && citations[i] < n - i) {
            i++;
        }
        return n - i;
    }

    public int hIndex(int[] citations) {
        int l = 0, n = citations.length, r = n - 1;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if(citations[m] < n - m) {
                l = m + 1;
            } else if(citations[m] > n - m) {
                r = m - 1;
            } else {
                return n - m;
            }
        }
        return n - l;
    }
}
