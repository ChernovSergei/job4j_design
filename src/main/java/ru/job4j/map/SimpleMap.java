package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = getIndex(key);
        boolean result = table[index] == null;
        if (result) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return result;
    }

    private int hash(int hashCode) {
        return (hashCode >>> 16) ^ hashCode;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int getIndex(K key) {
        return indexFor(hash(key == null ? 0 : key.hashCode()));
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] tmpTable = new MapEntry[capacity];
        for (MapEntry<K, V> el : table) {
            if (el != null) {
                tmpTable[getIndex(el.key)] = el;
            }
        }
        table = tmpTable;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        V result = null;
        if (ifPresent(key, index)) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        int index = getIndex(key);
        boolean result = ifPresent(key, index);
        if (result) {
            table[index] = null;
            count--;
            modCount--;
        }
        return result;
    }

    public boolean ifPresent(K key, int index) {
        return Objects.nonNull(table[index])
                && Objects.hashCode(table[index].key) == Objects.hashCode(key)
                && Objects.equals(table[index].key, key);
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedModCount = modCount;
            int index = 0;
            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };

    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
