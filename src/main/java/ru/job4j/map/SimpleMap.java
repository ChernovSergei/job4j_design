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
        if (table[index] != null) {
            return false;
        }
        table[index] = new MapEntry<>(key, value);
        count++;
        return true;
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
        MapEntry<K, V>[] tableOriginal = table;
        capacity *= 2;
        table = new MapEntry[capacity];
        for (int i = 0; i < tableOriginal.length; i++) {
            if (tableOriginal[i] != null) {
                int index = getIndex(tableOriginal[i].key);
                table[index] = tableOriginal[i];
                tableOriginal[i] = null;
            }
        }
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        if (ifAbsent(key, index)) {
            return null;
        }
        return table[index].value;
    }

    @Override
    public boolean remove(K key) {
        int index = getIndex(key);
        if (ifAbsent(key, index)) {
            return false;
        }
        table[index] = null;
        count--;
        return true;
    }

    public boolean ifAbsent(K key, int index) {
        if (table[index] == null) {
            return true;
        }
        if (key != null && table[index].key != null) {
            return key.hashCode() != table[index].key.hashCode() && key != table[index].key;
        }
        return table[index].key != null || key != null || table[index].value == null;
    }

    @Override
    public Iterator<K> iterator() {
        modCount = count;
        return new Iterator<K>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                if (modCount != count) {
                    throw new ConcurrentModificationException();
                }
                while (modCount != 0 && index < capacity && table[index] == null) {
                    index++;
                }
                return modCount != 0 && index < capacity;
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
