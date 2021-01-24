package google;
// Valid Mountain Array
public class Q941 {

    public static void main(String[] args) {
        Q941 q = new Q941();
        int[] A = {0,3,2,1};
        int[] B = {2, 1};
        System.out.println(q.validMountainArray(A));    // true
        System.out.println(q.validMountainArray(B));    // false
    }
    // O(n) O(1)
    public boolean validMountainArray(int[] A) {
        if(A == null || A.length < 3) {
            return false;
        }
        int i = 0, len = A.length - 1;
        while(i < len && A[i] < A[i + 1]) {
            i++;
        }
        if(i == 0 || i == len) {
            return false;
        }
        while(i < len && A[i] > A[i + 1]) {
            i++;
        }
        return i == len;
    }
}
