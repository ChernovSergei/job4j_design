package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.lang.Math;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count + 1 > Math.abs((float) capacity * LOAD_FACTOR)) {
            expand();
        }
        int hash = hash(key == null ? 0 : key.hashCode());
        int index = indexFor(hash);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            return true;
        }
        return false;
    }

    private int hash(int hashCode) {
        return (hashCode >>> 16) ^ hashCode;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        int capacityOriginal = capacity;
        MapEntry<K, V>[] tableOriginal = table;
        capacity *= 2;
        count = 0;
        table = new MapEntry[capacity];
        for (int i = 0; i < capacityOriginal; i++) {
            if (tableOriginal[i] != null) {
                put(tableOriginal[i].key, tableOriginal[i].value);
                tableOriginal[i] = null;
            }
        }
    }

    @Override
    public V get(K key) {
        int hash = hash(key == null ? 0 : key.hashCode());
        int index = indexFor(hash);
        if (table[index] == null || table[index].key != key) {
            return null;
        }
        return table[index].value;
    }

    @Override
    public boolean remove(K key) {
        int hash = hash(key == null ? 0 : key.hashCode());
        int index = indexFor(hash);
        if (table[index] != null && table[index].key == key) {
            table[index] = null;
            count--;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            MapEntry<K, V> entry;
            int modCount = count;
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
                entry = table[index];
                index++;
                return entry.key;
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
