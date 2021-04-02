package amazon;

import java.util.HashMap;
import java.util.Map;

// Roman to Integer
public class Q13 {

    public int romanToInt(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int res = 0;
        for(int i = s.length() - 1; i >= 0; i--) {
            switch(s.charAt(i)) {
                case 'I':
                    res += res >= 5 ? -1 : 1;
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'X':
                    res += res >= 50 ? -10 : 10;
                    break;
                case 'L':
                    res += 50;
                    break;
                case 'C':
                    res += res >= 500 ? -100: 100;
                    break;
                case 'D':
                    res += 500;
                    break;
                case 'M':
                    res += 1000;
                    break;
            }
        }
        return res;
    }

    static Map<String, Integer> values = new HashMap<>();

    static {
        values.put("M", 1000);
        values.put("D", 500);
        values.put("C", 100);
        values.put("L", 50);
        values.put("X", 10);
        values.put("V", 5);
        values.put("I", 1);
    }

    public int romanToInt1(String s) {

        String lastSymbol = s.substring(s.length() - 1);
        int lastValue = values.get(lastSymbol);
        int total = lastValue;

        for (int i = s.length() - 2; i >= 0; i--) {
            String currentSymbol = s.substring(i, i + 1);
            int currentValue = values.get(currentSymbol);
            if (currentValue < lastValue) {
                total -= currentValue;
            } else {
                total += currentValue;
            }
            lastValue = currentValue;
        }
        return total;
    }
}
