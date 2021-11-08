package amazon;
// Integer to Roman
public class Q12 {

    // for this method, we can support bigger scope (>3999) by add more symbol if needed.
    private static int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nums.length & num > 0; i++) {
            while(num >= nums[i]) {
                num -= nums[i];
                sb.append(roman[i]);
            }
        }
        return sb.toString();
    }

    // hardcode (1 <= num <= 3999)
    public String intToRoman1(int num) {

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[num / 1000] + hundreds[num % 1000 / 100] + tens[num % 100 / 10] + ones[num % 10];
    }

}
