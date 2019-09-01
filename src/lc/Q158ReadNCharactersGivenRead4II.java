package lc;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q158ReadNCharactersGivenRead4II {
    public class Reader4{
        public int read4(char[] input){
            return 0;
        }
    }
    public class Solution extends Reader4 {
        /**
         * @param buf Destination buffer
         * @param n   Number of characters to read
         * @return    The number of actual characters read
         */

        Deque<Character> deque;
        boolean isEOF;

        public int read(char[] buf, int n) {

            if (deque == null) {
                deque = new ArrayDeque();
                isEOF = false;
            }
            while (n > deque.size() && !isEOF) {
                char[] locBuf = new char[4];
                int size = read4(locBuf);
                addBuf(locBuf, size);
                if (size < 4) {
                    isEOF = true;
                }
            }
            return getBuf(buf, Math.min(n, deque.size()));

        }

        private int getBuf(char[] buf, int n) {
            for (int i = 0; i < n; i ++) {
                buf[i] = deque.pollFirst();
            }
            //System.out.println(n + " " + Arrays.toString(buf));
            return n;
        }

        private void addBuf(char[] buf, int size) {
            for (int i = 0; i < size; i ++) {
                deque.offerLast(buf[i]);
            }
        }
    }
}
