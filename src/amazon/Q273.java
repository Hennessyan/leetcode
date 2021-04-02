package amazon;

import java.util.HashMap;
import java.util.Map;

// Integer to English Words
public class Q273 {
    // O(N) O(1) : N -> number of digits
    private static final int BILLION = 1_000_000_000;
    private static final int MILLION = 1_000_000;
    private static final int THOUSAND = 1_000;
    Map<Integer, String> map;
    public String numberToWords(int num) {
        map = new HashMap<>();
        initialMap();
        if(num <= 20) {
            return map.get(num);
        }
        StringBuilder sb = new StringBuilder();
        if(num >= BILLION) {
            sb.append(convert(num / BILLION)).append(" ").append("Billion");
            num %= BILLION;
        }
        if(num >= MILLION) {
            sb.append(convert(num / MILLION)).append(" ").append("Million");
            num %= MILLION;
        }
        if(num >= THOUSAND) {
            sb.append(convert(num / THOUSAND)).append(" ").append("Thousand");
            num %= THOUSAND;
        }
        sb.append(convert(num));
        return sb.toString().trim();
    }
    private String convert(int num) {
        StringBuilder sb = new StringBuilder();
        int hundred = num / 100;
        if(hundred > 0) {
            sb.append(" ").append(map.get(hundred)).append(" ").append(map.get(100));
            num %= 100;
        }
        int ten = num / 10;
        if(ten > 0) {
            if(ten == 1) {
                sb.append(" ").append(map.get(num));
            } else {
                sb.append(" ").append(map.get(ten * 10));
            }
            num %= 10;
        }
        if(ten != 1 && num != 0) {
            sb.append(" ").append(map.get(num));
        }
        return sb.toString();
    }
    private void initialMap() {
        map.put(0, "Zero");
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");
        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");
        map.put(100, "Hundred");
    }
}
