package amazon;
// Detect Pattern of Length M Repeated K or More Times
public class Q1566 {

    public boolean containsPattern(int[] arr, int m, int k) {
        if(m * k == 0) {
            return true;
        }
        for(int i = 0; i <= arr.length - m * k; i++) {
            int j = i, k1 = k, m1 = 0;
            while(k1 > 0) {
                for(m1 = 0; m1 < m; m1++) {
                    if(arr[j + m1] != arr[i + m1]) {
                        break;
                    }
                }
                if(m1 == m) {
                    j += m;
                    k1--;
                } else {
                    break;
                }
            }
            if(k1 == 0) {
                return true;
            }
        }
        return false;
    }
    //keep same continuous pattern O(n) O(1)
    public boolean containsPattern2(int[] arr, int m, int k) {
        if(m * k == 0) {
            return true;
        }
        int count = 0;
        for(int i = 0, j = m; j < arr.length; i++, j++) {
            if(arr[i] != arr[j]) {
                count = 0;
            } else if(++count == (k - 1) * m) {
                return true;
            }
        }
        return false;
    }
}
