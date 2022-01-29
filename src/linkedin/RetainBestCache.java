package linkedin;

import java.util.*;

public class RetainBestCache<K, V extends Rankable> {
    // Add any fields you need here
    /**
    * Constructor with a data source (assumed to be slow) and a cache size
    * @param ds the persistent layer of the the cache
    * @param capacity the number of entries that the cache can hold
    */
    int capacity;
    Map<K, V> map;  // cache
    TreeMap<Long, Set<K>> rankMap;  // Set or LinkedList to remove first K with small cost.
    DataSource<K, V> ds;            // rankMap records K is good enough to delete it from map (cache)
    public RetainBestCache(DataSource<K, V> ds, int capacity) {
    // Implementation here
        this.capacity = capacity;
        this.ds = ds;
        this.map = new HashMap<>();
        this.rankMap = new TreeMap<>();
    }

    /**
    * Gets some data. If possible, retrieves it from cache to be fast. If the data is not cached,
    * retrieves it from the data source. If the cache is full, attempt to cache the returned data,
    * evicting the V with lowest rank among the ones that it has available
    * If there is a tie, the cache may choose any V with lowest rank to evict.
    * @param key the key of the cache entry being queried
    * @return the Rankable value of the cache entry
    */
    public V get(K key) {
    // Implementation here
        if(map.containsKey(key)) {
            return map.get(key);
        }
        return cacheMiss(key);
    }

    private V cacheMiss(K key) {
        if(map.size() == capacity) {
            evict();
        }
        V value = ds.get(key);
        rankMap.computeIfAbsent(value.getRank(), x -> new HashSet<>()).add(key);
        map.put(key, value);
        return value;
    }

    private void evict() {
        Map.Entry<Long, Set<K>> entry = rankMap.firstEntry();
        Long ek = entry.getKey();
        Set<K> ev = entry.getValue();
        K oldKey = ev.iterator().next();
        ev.remove(oldKey);
        if(ev.isEmpty()) {
            rankMap.remove(ek);
        }
        map.remove(oldKey);
    }
}

/*
 * For reference, here are the Rankable and DataSource interfaces.
 * You do not need to implement them, and should not make assumptions
 * about their implementations.
 */

interface Rankable {
    /**
     *    * Returns the Rank of this object, using some algorithm and potentially
     *    * the internal state of the Rankable.
     *    
     */
    long getRank();
}

interface DataSource<K, V extends Rankable> {
    V get (K key);
}
