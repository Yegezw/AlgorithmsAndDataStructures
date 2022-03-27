package practice.other;

import java.util.TreeMap;

/**
 * 哈希表
 */
public class HashTable<K, V> {

    private final int upperTol = 10;
    private final int lowerTol = 2;
    private final int[] capacity = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593, 49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469, 12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
    private int capacityIndex = 0;

    private TreeMap<K, V>[] hashTable;
    private int M;
    private int size;

    public HashTable() {
        this.M = capacity[capacityIndex];
        hashTable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashTable[i] = new TreeMap<>();
        }
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /**
     * 扩容 / 缩容
     */
    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }

        int oldM = this.M;
        this.M = newM;

        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashTable[i];
            for (K key : map.keySet()) {
                newHashTable[hash(key)].put(key, map.get(key));
            }
        }

        hashTable = newHashTable;
    }

    /**
     * 添加
     */
    public void add(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];

        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size++;
            if (size >= upperTol * M && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    /**
     * 删除
     */
    public V remove(K key) {
        TreeMap<K, V> map = hashTable[hash(key)];
        V ret = null;

        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;
            if (size < lowerTol * M && capacityIndex - 1 >= 0) {
                capacityIndex--;
                resize(capacity[capacityIndex]);
            }
        }

        return ret;
    }

    /**
     * 修改
     */
    public void set(K key, V newValue) {
        TreeMap<K, V> map = hashTable[hash(key)];

        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        map.put(key, newValue);
    }

    /**
     * 查看
     */
    public V get(K key) {
        return hashTable[hash(key)].get(key);
    }

    public boolean contains(K key) {
        return hashTable[hash(key)].containsKey(key);
    }
}
