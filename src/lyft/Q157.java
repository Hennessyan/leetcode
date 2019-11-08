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
//        char[] read4buf = new char[4];
//        int i = 0;
//        while(true) {
//            int index = read4(read4buf);
//            for(int j = 0; j < index && i < n; j++) {
//                buf[i++] = read4buf[j];
//            }
//            if(index < 4 || i == n) {
//                break;
//            }
//        }
//        return i;
//    }
//}
