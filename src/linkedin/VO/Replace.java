package linkedin.VO;

public class Replace {

    public static void main(String[] args) {
        System.out.println(replace("abcdaew", "cda", "xxx"));
        System.out.println(replace("abbbcbc", "bc", "xxx"));
        System.out.println(replace("aaaaa", "aa", "xxx"));
    }

    public static String replace(String origin, String pattern, String replace) {
        if(origin == null || pattern == null || replace == null) return "";
        int oLen = origin.length(), pLen = pattern.length(), i = 0;
        if(oLen == 0 && pLen == 0) return replace;
        StringBuilder sb = new StringBuilder();
        for(i = 0; i <= oLen - pLen; i++) {
            int j = 0;
            while(j < pLen && origin.charAt(i+j) == pattern.charAt(j)) {
                j++;
            }
            if(j == pLen) {
                sb.append(replace);
                i = i + j - 1;
            } else {
                sb.append(origin.charAt(i));
            }
        }
        while(i < oLen) {
            sb.append(origin.charAt(i++));
        }
        return sb.toString();
    }
}
