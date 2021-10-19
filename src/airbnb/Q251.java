package airbnb;

import java.util.NoSuchElementException;

// Flatten 2D Vector
public class Q251 {

    class Vector2D {

        int[][] v;
        int r, c;
        public Vector2D(int[][] vec) {
            this.v = vec;
            this.r = 0;
            this.c = 0;
        }
        // O(V/N) / O(1)
        public int next() {
            if(!hasNext()) throw new NoSuchElementException();
            return v[r][c++];
        }
        // O(V/N)
        public boolean hasNext() {
            updateNext();
            return r != v.length;
        }

        // O((N+V)/N)
        private void updateNext() {
            while(r != v.length && c == v[r].length) {
                r++;
                c = 0;
            }
        }
    }
}
