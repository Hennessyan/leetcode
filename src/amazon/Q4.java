package amazon;

//Median of Two Sorted Arrays

//Median of two sorted arrays of same size:
//http://www.geeksforgeeks.org/median-of-two-sorted-arrays/
public class Q4 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Q4 q = new Q4();
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        int[] nums3 = {1,2};
        int[] nums4 = {3,4};
        System.out.println(findMedianSortedArrays(nums1, nums2)); //return 2.0
        System.out.println(findMedianSortedArrays(nums3, nums4)); //return 2.5
    }
    //O(lg(min(m, n)))
    //O(1)
    /*https://www.programcreek.com/2012/12/leetcode-median-of-two-sorted-arrays-java/*/
    //这个方法中k就是我们所要找的index
    public static double findMedianSortedArrays(int A[], int B[]) {
        int m = A.length;
        int n = B.length;

        if ((m + n) % 2 != 0) // odd
            return (double) findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1);
        else { // even
            return (findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1)
                    + findKth(A, B, (m + n) / 2 - 1, 0, m - 1, 0, n - 1)) * 0.5;
        }
    }

    public static int findKth(int A[], int B[], int k,
                              int aStart, int aEnd, int bStart, int bEnd) {

        int aLen = aEnd - aStart + 1;
        int bLen = bEnd - bStart + 1;

        // Handle special cases
        if (aLen == 0)
            return B[bStart + k];
        if (bLen == 0)
            return A[aStart + k];
        if (k == 0)
            return A[aStart] < B[bStart] ? A[aStart] : B[bStart];
        //理解起来像是根据比例进行分配的
        int aMid = aLen * k / (aLen + bLen); // a's middle count
        int bMid = k - aMid - 1; // b's middle count

        // make aMid and bMid to be array index
        aMid = aMid + aStart;
        bMid = bMid + bStart;


        if (A[aMid] > B[bMid]) {
            k = k - (bMid - bStart + 1);
            aEnd = aMid;
            bStart = bMid + 1;
        } else {
            k = k - (aMid - aStart + 1);
            bEnd = bMid;
            aStart = aMid + 1;
        }

        return findKth(A, B, k, aStart, aEnd, bStart, bEnd);
    }
    /*https://discuss.leetcode.com/topic/28602/concise-java-solution-based-on-binary-search*/
    ////这个方法中k-1才是我们实际所要找的index
//	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int l = (nums1.length + nums2.length + 1) / 2;
//        int r = (nums1.length + nums2.length + 2) / 2;
//        return (findKth(nums1, nums2, 0, 0, l) + findKth(nums1, nums2, 0, 0, r)) * 0.5;
//    }
//    private int findKth(int[] nums1, int[] nums2, int s1, int s2, int k){
//        if(s1 > nums1.length - 1){
//            return nums2[s2 + k -1];
//        }
//        if(s2 > nums2.length - 1){
//            return nums1[s1 + k - 1];
//        }
//        if(k == 1){
//            return Math.min(nums1[s1], nums2[s2]);
//        }
//        int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
//        if(s1 + k/2 - 1 < nums1.length){
//            aMid = nums1[s1 + k/2 - 1];
//        }
//        if(s2 + k/2 - 1 < nums2.length){
//            bMid = nums2[s2 + k/2 - 1];
//        }
//        if(aMid < bMid){
//            return findKth(nums1, nums2, s1 + k/2, s2, k - k/2);
//        }else{
//            return findKth(nums1, nums2, s1, s2 + k/2, k - k/2);
//        }
//
//    }
}

