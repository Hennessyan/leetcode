package amazon;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Peeking Iterator
public class Q284 {

    class PeekingIterator implements Iterator<Integer> {
        private Iterator<Integer> iterator;
        private Integer peek;
        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator = iterator;
            peek = null;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            if(peek == null) {
                if(!iterator.hasNext()) {
                    throw new NoSuchElementException();
                }
                peek = iterator.next();
            }
            return peek;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            if(peek != null) {
                Integer next = peek;
                peek = null;
                return next;
            }
            if(!iterator.hasNext()) {
                throw new NoSuchElementException();
            }
            return iterator.next();
        }

        @Override
        public boolean hasNext() {
            return peek != null || iterator.hasNext();
        }
    }

    class PeekingIterator1 implements Iterator<Integer> {
        private Iterator<Integer> it;
        private Integer next;
        public PeekingIterator1(Iterator<Integer> iterator) {
            // initialize any member here.
            if(iterator.hasNext()) {
                next = iterator.next();
            }
            it = iterator;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return next;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            if(next == null) {
                throw new NoSuchElementException();
            }
            Integer tmp = next;
            next = null;
            if(it.hasNext()) {
                next = it.next();
            }
            return tmp;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }
    }

    class PeekingIterator2<E> implements Iterator<E> {

        private final Iterator<? extends E> it;
        private boolean hasPeek;    // used to avoid E can be null
        private E peek;
        public PeekingIterator2(Iterator<? extends E> iterator) {
            it = iterator;
        }

        public E next() {
            if(hasPeek) {
                E res = peek;
                peek = null;
                hasPeek = false;
                return res;
            }
            return it.next();
        }

        public E peek() {
            if(!hasPeek) {
                peek = it.next();
                hasPeek = true;
            }
            return peek;
        }

        public boolean hasNext() {
            return hasPeek || it.hasNext();
        }
    }
}
