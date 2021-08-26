package amazon;

import java.util.ArrayList;
import java.util.List;

// Encode and Decode Strings
public class Q271 {

    class Codec {
        // O(n) O(n)
        public String encode(List<String> strs) {
            StringBuffer out = new StringBuffer();
            for (String s : strs)
                out.append(s.replace("/", "//").replace("*", "/*")).append("*");
            return out.toString();
        }

        public List<String> decode(String s) {
            List<String> res = new ArrayList<>();
            StringBuilder str = new StringBuilder();

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '/') {
                    str.append(s.charAt(++i));
                } else if (s.charAt(i) == '*') {
                    res.add(str.toString());
                    //str.delete(0, str.length());	//清空StringBuilder
                    str.setLength(0); 				//清空StringBuilder
                } else {							//或者new一个新的StringBuilder
                    str.append(s.charAt(i));
                }
            }

            return res;
        }
		/*
		//method2
		// Encodes a list of strings to a single string.
	    public String encode(List<String> strs) {
	        StringBuilder sb = new StringBuilder();
	        for(String s : strs){
	            sb.append(s.length()).append('/').append(s);
	        }
	        return sb.toString();
	    }

	    // Decodes a single string to a list of strings.
	    public List<String> decode(String s) {
	        List<String> list = new ArrayList<>();
	        int index = 0;
	        while(index < s.length()){
	            int slash = s.indexOf('/', index);
	            int size = Integer.parseInt(s.substring(index, slash));
	            String tmp = s.substring(slash + 1, slash + 1 + size);
	            list.add(tmp);
	            index = slash + 1 + size;
	        }
	        return list;
	    }
	    */
        /* method3
        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            StringBuilder sb = new StringBuilder();
            for(String str : strs) {
                sb.append(i2s(str.length()));
                sb.append(str);
            }
            return sb.toString();
        }
        private String i2s(int val) {
            char[] bytes = new char[4];
            for(int i = 3; i >= 0; i--) {
                bytes[i] = (char) (val & 0xff);
                val >>= 8;
            }
            return String.valueOf(bytes);
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            List<String> res = new ArrayList<>();
            int l = 0;
            while(l < s.length()) {
                int r = s2i(s.substring(l, l+4));
                l += 4;
                res.add(s.substring(l, l+r));
                l += r;
            }
            return res;
        }
        private int s2i(String str) {
            int len = 0;
            for(char c : str.toCharArray()) {
                len = (len << 8) + (int) c;
            }
            return len;
        }
        */
    }

}
