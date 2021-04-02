package amazon;
// Design HashMap
public class Q706 {
    // check with one, and use it: https://leetcode.com/problems/design-hashmap/solution/
    // In addition, in order to minimize the potential collisions, it is advisable to use a prime number as the base of modulo, e.g. 2069.
    class MyHashMap {

        private static final int R = 16;
        private Entry[] entries;

        /** Initialize your data structure here. */
        public MyHashMap() {
            entries = new Entry[R];
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            int index = getIndex(key);
            if(entries[index] == null) {
                entries[index] = new Entry(key, value);
                return;
            }
            Entry entry = find(entries[index], key);
            if(entry != null) {
                entry.val = value;
            }else {
                Entry tmp = new Entry(key, value);
                tmp.next = entries[index].next;
                entries[index].next = tmp;
            }
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int index = getIndex(key);
            Entry entry = find(entries[index], key);
            if(entry != null) {
                return entry.val;
            }else {
                return -1;
            }
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int index = getIndex(key);
            Entry dummy = new Entry(0,0);
            dummy.next = entries[index];
            Entry cur = dummy;
            while(cur.next != null && cur.next.key != key) {
                cur = cur.next;
            }
            if(cur.next != null) {
                cur.next = cur.next.next;
            }
            entries[index] = dummy.next;
        }

        private int getIndex(int key) {
            return key % R;
        }
        private Entry find(Entry entry, int key) {
            while(entry != null) {
                if(entry.key == key) {
                    return entry;
                }
                entry = entry.next;
            }
            return entry;
        }

        class Entry {
            int key, val;
            Entry next;
            public Entry(int key, int val) {
                this.key = key;
                this.val = val;
                this.next = null;
            }
        }
    }

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
}
