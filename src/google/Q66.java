package google;
// Plus One
public class Q66 {

    public int[] plusOne(int[] digits) {
        if(digits == null || digits.length == 0) {
            return digits;
        }
        int len = digits.length;
        int i = len - 1;
        while(i >= 0) {
            if(digits[i] < 9) {
                digits[i]++;
                break;
            } else {
                digits[i--] = 0;
            }
        }
        if(i == -1) {
            int[] res = new int[len + 1];
            res[0] = 1;
            return res;
        }
        return digits;
    }
}
