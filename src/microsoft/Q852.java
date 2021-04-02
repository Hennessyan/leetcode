package microsoft;
// Peak Index in a Mountain Array
public class Q852 {

    public int peakIndexInMountainArray(int[] A) {
        int lo = 0, hi = A.length - 1;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (A[mi] < A[mi + 1])
                lo = mi + 1;
            else
                hi = mi;
        }
        return lo;
    }

    public int peakIndexInMountainArray1(int[] A) {
        int i = 0;
        while (A[i] < A[i+1]) i++;
        return i;
    }
}
