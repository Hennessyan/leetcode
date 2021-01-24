package challenge.september;
// Read N Characters Given Read4
public class Q157 {

    /*
    // O(n) O(1)
    class Solution extends Reader4 {
        public int read(char[] buf, int n) {
            char[] read4buf = new char[4];
            int i = 0;
            while(true) {
                int index = read4(read4buf);
                for(int j = 0; j < index && i < n; j++) {
                    buf[i++] = read4buf[j];
                }
                if(index < 4 || i == n) {
                    break;
                }
            }
            return i;
        }
    }
    */

//    public class Solution extends Reader4 {
//        public int read(char[] buf, int n) {
//            int copiedChars = 0, readChars = 4;
//            char[] buf4 = new char[4];
//
//            while (copiedChars < n && readChars == 4) {
//                readChars = read4(buf4);
//
//                for (int i = 0; i < readChars; ++i) {
//                    if (copiedChars == n)
//                        return copiedChars;
//                    buf[copiedChars] = buf4[i];
//                    ++copiedChars;
//                }
//            }
//            return copiedChars;
//        }
//    }
}
