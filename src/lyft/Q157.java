package lyft;

//Read N Characters Given Read4
public class Q157 {
}

/**
 * The read4 API is defined in the parent class Reader4.
 * int read4(char[] buf);
 */

//public class Solution extends Reader4 {
//    /**
//     * @param buf Destination buffer
//     * @param n   Number of characters to read
//     * @return    The number of actual characters read
//     */
//    public int read(char[] buf, int n) {
//        int i = 0;
//        char[] buf4 = new char[4];
//        while(i < n) {
//            int m = read4(buf4);
//            for(int j = 0; j < m && i < n; j++) {
//                buf[i++] = buf4[j];
//            }
//            if(m < 4) break;
//        }
//        return i;
//    }
//}
